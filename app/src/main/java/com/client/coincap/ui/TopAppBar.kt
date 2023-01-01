package com.client.coincap.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.client.common.util.NavRoutes
import com.client.designsystem.theme.CoinCapTheme
import com.client.search.navigation.navigateToSearch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    navController: NavHostController,
    destination: NavDestination?
) {
    val isCurrentRouteHome = destination?.route == NavRoutes.homeRoute
    TopAppBar(
        title = { Text(text = stringResource(id = title)) },
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
            }
        }
    )
}

@Preview
@Composable
fun TopAppBarPreview() {
    val navController = rememberNavController()
    CoinCapTheme {
        TopAppBar(
            title = android.R.string.untitled,
            navController = navController,
            destination = null
        )
    }
}
