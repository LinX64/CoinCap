package com.client.coincap.ui

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.util.trace
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.client.coincap.navigation.TabsDestinations
import com.client.convert.navigation.exchangeNavigationRoute
import com.client.convert.navigation.navigateToExchange
import com.client.data.util.NetworkMonitor
import com.client.home.navigation.homeNavigationRoute
import com.client.home.navigation.navigateToHome
import com.client.ui.TrackDisposableJank
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@Composable
fun rememberCoinCapState(
    windowSizeClass: WindowSizeClass,
    networkMonitor: NetworkMonitor,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController()
): CoinCapState {
    NavigationTrackingSideEffect(navController)
    return remember(navController, coroutineScope, windowSizeClass, networkMonitor) {
        CoinCapState(navController, coroutineScope, windowSizeClass, networkMonitor)
    }
}

@Stable
class CoinCapState(
    val navController: NavHostController,
    coroutineScope: CoroutineScope,
    val windowSizeClass: WindowSizeClass,
    networkMonitor: NetworkMonitor
) {

    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val topLevelDestinations: List<TabsDestinations> = TabsDestinations.values().asList()

    val currentTopLevelDestination: TabsDestinations?
        @Composable get() = when (currentDestination?.route) {
            homeNavigationRoute -> TabsDestinations.HOME
            exchangeNavigationRoute -> TabsDestinations.EXCHANGE
            else -> null
        }

    val isOffline = networkMonitor.isOnline
        .map(Boolean::not)
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false
        )

    fun navigateToSpecificDestination(topLevelDestination: TabsDestinations) {
        trace("Navigation: ${topLevelDestination.name}") {
            val topLevelNavOptions = navOptions {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }

            when (topLevelDestination) {
                TabsDestinations.HOME -> navController.navigateToHome(topLevelNavOptions)
                TabsDestinations.EXCHANGE -> navController.navigateToExchange(topLevelNavOptions)
            }
        }
    }
}

@Composable
private fun NavigationTrackingSideEffect(navController: NavHostController) {
    TrackDisposableJank(navController) { metricsHolder ->
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            metricsHolder.state?.putState("Navigation", destination.route.toString())
        }

        navController.addOnDestinationChangedListener(listener)

        onDispose {
            navController.removeOnDestinationChangedListener(listener)
        }
    }
}
