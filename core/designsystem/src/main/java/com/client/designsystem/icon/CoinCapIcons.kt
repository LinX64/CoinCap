package com.client.designsystem.icon

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.rounded.*
import androidx.compose.ui.graphics.vector.ImageVector

object CoinCapIcons {
    val AccountCircle = Icons.Outlined.AccountCircle
    val Add = Icons.Rounded.Add
    val ArrowBack = Icons.Rounded.ArrowBack
    val ArrowDropDown = Icons.Default.ArrowDropDown
    val ArrowDropUp = Icons.Default.ArrowDropUp
    val Check = Icons.Rounded.Check
    val Close = Icons.Rounded.Close
    val ExpandLess = Icons.Rounded.ExpandLess
    val Fullscreen = Icons.Rounded.Fullscreen
    val Grid3x3 = Icons.Rounded.Grid3x3
    val MoreVert = Icons.Default.MoreVert
    val Person = Icons.Rounded.Person
    val PlayArrow = Icons.Rounded.PlayArrow
    val Search = Icons.Rounded.Search
    val Settings = Icons.Rounded.Settings
    val ShortText = Icons.Rounded.ShortText
    val Tag = Icons.Rounded.Tag
    val ViewDay = Icons.Rounded.ViewDay
    val VolumeOff = Icons.Rounded.VolumeOff
    val VolumeUp = Icons.Rounded.VolumeUp
    val Home = Icons.Rounded.Home
    val Exchange = Icons.Rounded.CurrencyExchange
}

sealed class Icon {
    data class ImageVectorIcon(val imageVector: ImageVector) : Icon()
    data class DrawableResourceIcon(@DrawableRes val id: Int) : Icon()
}
