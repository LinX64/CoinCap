package com.client.convert.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.client.common.util.NavRoutes
import com.client.convert.ConvertRoute

const val convertNavigationRoute = NavRoutes.convertRoute

fun NavController.navigateToConvert(navOptions: NavOptions? = null) {
    this.navigate(convertNavigationRoute, navOptions)
}

fun NavGraphBuilder.convertScreen() {
    composable(route = convertNavigationRoute) {
        ConvertRoute()
    }
}
