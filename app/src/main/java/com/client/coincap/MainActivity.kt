package com.client.coincap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import com.client.coincap.ui.CoinCapApp
import com.client.coincap.ui.theme.CoinCapTheme
import com.client.data.util.NetworkMonitor
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var networkMonitor: NetworkMonitor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CoinCapTheme(androidTheme = true,) {
                CoinCapApp(
                    networkMonitor = networkMonitor,
                    windowSizeClass = calculateWindowSizeClass(this)
                )
            }
        }
    }
}
