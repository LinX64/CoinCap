package com.client.coincap.navigation

import com.client.coincap.R
import com.client.designsystem.icon.CoinCapIcons
import com.client.designsystem.icon.Icon
import com.client.coincap.ui.convert.R as convertR
import com.client.coincap.ui.home.R as homeR

enum class TabsDestinations(
    val selectedIcon: Icon,
    val unselectedIcon: Icon,
    val iconTextId: Int,
    val titleTextId: Int
) {
    HOME(
        selectedIcon = Icon.ImageVectorIcon(CoinCapIcons.Home),
        unselectedIcon = Icon.ImageVectorIcon(CoinCapIcons.HomeUnselected),
        iconTextId = homeR.string.home,
        titleTextId = R.string.app_name
    ),
    EXCHANGE(
        selectedIcon = Icon.ImageVectorIcon(CoinCapIcons.Exchange),
        unselectedIcon = Icon.ImageVectorIcon(CoinCapIcons.ExchangeUnselected),
        iconTextId = convertR.string.convert,
        titleTextId = convertR.string.convert
    )
}
