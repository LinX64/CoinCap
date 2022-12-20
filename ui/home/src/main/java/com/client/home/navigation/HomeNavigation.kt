package com.client.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.client.common.util.NavRoutes
import com.client.home.HomeRoute

const val homeNavigationRoute = NavRoutes.homeRoute

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(homeNavigationRoute, navOptions)
}

fun NavGraphBuilder.homeScreen(navController: NavHostController) {
    composable(route = homeNavigationRoute) {
        HomeRoute(navController = navController)
    }
}
