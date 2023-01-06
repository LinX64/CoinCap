package com.client.search

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.client.home.HomeLocalUiState
import com.client.home.HomeScreen
import com.client.home.HomeUiState
import com.client.ui.util.DummyData
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val rates = DummyData.rates()
    private val localRates = DummyData.localRates()

    @Test
    fun progressBar_whenIsLoadingShouldBeVisible() {
        with(composeTestRule) {
            setContent {
                BoxWithConstraints {
                    HomeScreen(
                        homeUiState = HomeUiState.Loading,
                        localUiState = HomeLocalUiState.Loading,
                        navController = rememberNavController()
                    )
                }
            }
            onNodeWithTag("ui:home:shimmer").assertExists()
        }
    }

    @Test
    fun progressBar_whenSuccessfulShouldNoBeVisible() {
        with(composeTestRule) {
            setContent {
                BoxWithConstraints {
                    HomeScreen(
                        homeUiState = HomeUiState.Success(emptyList()),
                        localUiState = HomeLocalUiState.Success(emptyList()),
                        navController = rememberNavController()
                    )

                }
            }
            onNodeWithTag("ui:home:shimmer").assertDoesNotExist()
        }
    }

    @Test
    fun when_CryptoCurrencyResultIsSuccessShouldShowList() {
        with(composeTestRule) {
            setContent {
                BoxWithConstraints {
                    HomeScreen(
                        homeUiState = HomeUiState.Success(rates = rates),
                        localUiState = HomeLocalUiState.Success(localRates = localRates),
                        navController = rememberNavController()
                    )
                }
            }
            onNodeWithTag("ui:home:list").assertExists()
        }
    }

    @Test
    fun when_localCurrencyResultIsSuccessShouldShowList() {
        with(composeTestRule) {
            setContent {
                BoxWithConstraints {
                    HomeScreen(
                        homeUiState = HomeUiState.Success(rates = rates),
                        localUiState = HomeLocalUiState.Success(localRates = localRates),
                        navController = rememberNavController()
                    )
                }
            }
            onAllNodesWithTag("ui:localCurrency").assertCountEquals(4)
        }
    }

    @Test
    fun when_CryptoCurrencyResultIsErrorShouldShowErrorView() {
        with(composeTestRule) {
            setContent {
                BoxWithConstraints {
                    HomeScreen(
                        homeUiState = HomeUiState.Error("Error"),
                        localUiState = HomeLocalUiState.Success(emptyList()),
                        navController = rememberNavController()
                    )
                }
            }
            onNodeWithTag("ui:error").assertExists()
        }
    }
}
