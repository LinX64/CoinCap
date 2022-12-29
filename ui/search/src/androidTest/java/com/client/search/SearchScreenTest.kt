package com.client.search

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.compose.rememberNavController
import com.client.ui.util.DummyData
import org.junit.Rule
import org.junit.Test

class SearchScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val rates = DummyData.rates()

    @Test
    fun whenIsInitialState_thenShowInitState() {
        composeTestRule.setContent {
            SearchScreen(
                searchUiState = SearchUiState.Initial,
                navController = rememberNavController(),
                onQueryChanged = {},
                onClear = {}
            )
        }
        composeTestRule.onNodeWithTag("search:init").assertExists()
    }

    @Test
    fun whenIsLoadingState_thenShowLoadingState() {
        composeTestRule.setContent {
            SearchScreen(
                searchUiState = SearchUiState.Loading,
                navController = rememberNavController(),
                onQueryChanged = {},
                onClear = {}
            )
        }
        composeTestRule.onNodeWithTag("search:loading").assertExists()
    }

    @Test
    fun whenIsEmptyState_thenShowEmptyState() {
        composeTestRule.setContent {
            SearchScreen(
                searchUiState = SearchUiState.Empty,
                navController = rememberNavController(),
                onQueryChanged = {},
                onClear = {}
            )
        }
        composeTestRule.onAllNodesWithTag("search:empty").assertCountEquals(3)
    }

    @Test
    fun whenIsSuccessState_thenShowSuccessState() {
        composeTestRule.setContent {
            SearchScreen(
                searchUiState = SearchUiState.Success(rates),
                navController = rememberNavController(),
                onQueryChanged = {},
                onClear = {}
            )
        }
        composeTestRule.onAllNodesWithTag("search:success").assertCountEquals(6)
    }
}
