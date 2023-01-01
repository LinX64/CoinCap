package com.client.convert.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ConvertButton(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .padding(16.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        Button(
            onClick = { /*TODO*/ },
            modifier = modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Convert",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Preview
@Composable
fun ConvertButtonPreview() {
    ConvertButton()
}
