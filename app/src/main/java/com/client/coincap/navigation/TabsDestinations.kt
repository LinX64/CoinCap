package com.client.coincap.navigation

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
        unselectedIcon = Icon.ImageVectorIcon(CoinCapIcons.Home),
        iconTextId = homeR.string.home,
        titleTextId = com.client.coincap.R.string.app_name
    ),
    BOOKMARKS(
        selectedIcon = Icon.ImageVectorIcon(CoinCapIcons.Exchange),
        unselectedIcon = Icon.ImageVectorIcon(CoinCapIcons.Exchange),
        iconTextId = convertR.string.convert,
        titleTextId = convertR.string.convert
    )
}
