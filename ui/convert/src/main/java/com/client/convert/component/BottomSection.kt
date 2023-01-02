package com.client.convert.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BottomSection() {
    Spacer(modifier = Modifier.height(32.dp))

    Divider()

    Spacer(modifier = Modifier.height(16.dp))

    ResultText()

    ConvertButton()
}
