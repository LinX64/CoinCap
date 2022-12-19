package com.client.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LoadingView() {
    LinearProgressIndicator(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("ui:loading"),
        trackColor = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.5f)
    )
}

@Preview
@Composable
fun LinearPreview() {
    LoadingView()
}