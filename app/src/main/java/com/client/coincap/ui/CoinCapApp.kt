package com.client.coincap.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.client.coincap.R
import com.client.coincap.navigation.NavHost
import com.client.coincap.navigation.TabsDestinations
import com.client.common.util.NavRoutes
import com.client.data.util.NetworkMonitor
import com.client.designsystem.component.NavigationBarItem
import com.client.designsystem.icon.Icon

@OptIn(ExperimentalMaterial3Api::class,)
@Composable
fun CoinCapApp(
    networkMonitor: NetworkMonitor,
    windowSizeClass: WindowSizeClass,
    appState: CoinCapState = rememberCoinCapState(
        networkMonitor = networkMonitor,
        windowSizeClass = windowSizeClass
    )
) {
    val isOffline by appState.isOffline.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }
    val currentDestination = appState.currentDestination
    val destination = appState.currentTopLevelDestination
    val isDestinationSearch = currentDestination?.route == NavRoutes.searchRoute
    val isDestinationDetail = currentDestination?.route == NavRoutes.coinDetailsRoute

    Scaffold(
        contentColor = MaterialTheme.colorScheme.onBackground,
        snackbarHost = { SnackbarHost(snackBarHostState) },
        bottomBar = {
            BottomBar(
                destinations = appState.topLevelDestinations,
                onNavigateToDestination = appState::navigateToSpecificDestination,
                currentDestination = currentDestination,
                modifier = Modifier.testTag("home:bottomBar")
            )
        },
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { padding ->
        Column(Modifier.fillMaxSize()) {
            when {
                destination != null && !isDestinationSearch -> {
                    TopAppBar(
                        title = destination.titleTextId,
                        navController = appState.navController,
                        destination = currentDestination
                    )
                }
                isDestinationDetail -> {
                    TopAppBar(
                        navController = appState.navController,
                        destination = currentDestination
                    )
                }
            }

            AppNavigation(
                appState = appState,
                padding = padding
            )
        }

        val notConnected = stringResource(R.string.no_internet_connection)
        LaunchedEffect(isOffline) {
            if (isOffline) {
                snackBarHostState.showSnackbar(
                    message = notConnected,
                    duration = SnackbarDuration.Long
                )
            }
        }
    }
}

@Composable
private fun BottomBar(
    destinations: List<TabsDestinations>,
    onNavigateToDestination: (TabsDestinations) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier
    ) {
        destinations.forEach { destination ->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)
            NavigationBarItem(
                selected = selected,
                onClick = { onNavigateToDestination(destination) },
                icon = {
                    val icon = if (selected) {
                        destination.selectedIcon
                    } else {
                        destination.unselectedIcon
                    }
                    when (icon) {
                        is Icon.ImageVectorIcon -> Icon(
                            imageVector = icon.imageVector,
                            contentDescription = null
                        )

                        is Icon.DrawableResourceIcon -> Icon(
                            painter = painterResource(id = icon.id),
                            contentDescription = null
                        )
                    }
                },
                label = { Text(stringResource(destination.iconTextId)) }
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AppNavigation(appState: CoinCapState, padding: PaddingValues) {
    NavHost(
        navController = appState.navController,
        modifier = Modifier
            .padding(padding)
            .consumeWindowInsets(padding)
    )
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TabsDestinations) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false
