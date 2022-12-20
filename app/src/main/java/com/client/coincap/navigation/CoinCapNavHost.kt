package com.client.coincap.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.client.detail.navigation.detailScreen
import com.client.home.navigation.homeNavigationRoute
import com.client.home.navigation.homeScreen
import com.client.search.navigation.searchScreen

@Composable
fun CoinCapNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = homeNavigationRoute
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        homeScreen(navController)
        searchScreen(navController)
        detailScreen()
    }
}
