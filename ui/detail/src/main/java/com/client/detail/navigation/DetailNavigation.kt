package com.client.detail.navigation

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.client.common.util.NavRoutes
import com.client.detail.DetailRoute

const val detailRoute = NavRoutes.coinDetailsRoute

@VisibleForTesting
internal const val rateIdArgKey = "rateId"

internal class DetailArgs(savedStateHandle: SavedStateHandle) {
    var rateId: String = savedStateHandle.get<String>(rateIdArgKey).toString()
}

fun NavController.navigateToDetail(rateId: String, navOptions: NavOptions? = null) {
    this.navigate("$detailRoute/$rateId", navOptions)
}

fun NavGraphBuilder.detailScreen(navController: NavHostController) {
    composable(
        route = "$detailRoute/{$rateIdArgKey}",
        arguments = listOf(navArgument(rateIdArgKey) { type = NavType.StringType })
    ) {
        DetailRoute()
    }
}