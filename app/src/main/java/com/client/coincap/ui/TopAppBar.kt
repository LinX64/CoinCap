package com.client.coincap.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.client.coincap.R
import com.client.coincap.ui.theme.CoinCapTheme
import com.client.search.navigation.navigateToSearch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    navController: NavHostController
) {
    CenterAlignedTopAppBar(
        title = { Text(text = stringResource(R.string.app_name)) },
        navigationIcon = {
            //todo: handle back navigation
        },
        actions = {
            IconButton(onClick = { navController.navigateToSearch() }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = null,
                    tint = Color.Gray
                )
            }
        }
    )
}

@Preview
@Composable
fun TopAppBarPreview() {
    val navController = rememberNavController()
    CoinCapTheme {
        TopAppBar(navController)
    }
}