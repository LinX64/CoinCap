package com.client.coincap.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import com.client.coincap.R
import com.client.common.util.NavRoutes
import com.client.search.navigation.navigateToSearch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    destination: NavDestination?,
    appState: CoinCapState
) {
    val isCurrentRouteHome = destination?.route == NavRoutes.homeRoute
    TopAppBar(
        title = { Text(text = stringResource(R.string.app_name)) },
        modifier = modifier.fillMaxWidth(),
        navigationIcon = {
            if (!isCurrentRouteHome) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        },
        actions = {
            if (isCurrentRouteHome) {
                IconButton(onClick = { navController.navigateToSearch() }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                }

                IconButton(onClick = { appState.setShowSettingsDialog(true) }) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = null
                    )
                }
            }
        }
    )
}
