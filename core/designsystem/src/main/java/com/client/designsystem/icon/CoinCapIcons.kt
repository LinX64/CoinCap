package com.client.designsystem.icon

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CurrencyExchange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.rounded.CurrencyExchange
import androidx.compose.material.icons.rounded.Home
import androidx.compose.ui.graphics.vector.ImageVector

object CoinCapIcons {
    val Home = Icons.Rounded.Home
    val HomeUnselected = Icons.Outlined.Home
    val Exchange = Icons.Rounded.CurrencyExchange
    val ExchangeUnselected = Icons.Outlined.CurrencyExchange
    // TODO: Add more icons here
}

sealed class Icon {
    data class ImageVectorIcon(val imageVector: ImageVector) : Icon()
    data class DrawableResourceIcon(@DrawableRes val id: Int) : Icon()
}
