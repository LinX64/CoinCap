package com.client.convert.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.client.common.util.NavRoutes
import com.client.convert.ExchangeRoute

const val exchangeNavigationRoute = NavRoutes.exchangeRoute

fun NavController.navigateToExchange(navOptions: NavOptions? = null) {
    this.navigate(exchangeNavigationRoute, navOptions)
}

fun NavGraphBuilder.exchangeScreen() {
    composable(route = exchangeNavigationRoute) {
        ExchangeRoute()
    }
}
