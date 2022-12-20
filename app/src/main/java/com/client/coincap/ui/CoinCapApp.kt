package com.client.coincap.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.client.coincap.R
import com.client.coincap.navigation.CoinCapNavHost
import com.client.data.util.NetworkMonitor

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalLifecycleComposeApi::class
)
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

    Scaffold(
        contentColor = MaterialTheme.colorScheme.onBackground,
        topBar = { TopAppBar(appState.navController, currentDestination) },
        snackbarHost = { SnackbarHost(snackBarHostState) },
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
    ) { padding ->

        AppNavigation(
            appState = appState,
            padding = padding
        )

        val notConnected = stringResource(R.string.no_internet_connection)
        LaunchedEffect(isOffline) {
            if (isOffline) snackBarHostState.showSnackbar(
                message = notConnected,
                duration = SnackbarDuration.Long
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AppNavigation(appState: CoinCapState, padding: PaddingValues) {
    CoinCapNavHost(
        navController = appState.navController,
        modifier = Modifier
            .padding(padding)
            .consumedWindowInsets(padding),
    )
}


