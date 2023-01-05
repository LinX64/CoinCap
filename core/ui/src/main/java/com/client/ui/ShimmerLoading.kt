package com.client.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun shimmerLoading(
    duration: Int = 2000,
    color: Color = Color.LightGray,
): Brush {
    val gradient = listOf(
        color.copy(alpha = 0.8f),
        color.copy(alpha = 0.35f),
        color.copy(alpha = 0.8f),
    )

    val transition = rememberInfiniteTransition()
    val translateAnimation = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = duration,
                easing = FastOutLinearInEasing
            )
        )
    )

    return Brush.linearGradient(
        colors = gradient,
        start = Offset(250f, 250f),
        end = Offset(
            x = translateAnimation.value,
            y = translateAnimation.value
        )
    )
}

@Preview
@Composable
fun ShimmerLoadingPreview() {
    Spacer(
        modifier = Modifier
            .size(
                width = 96.dp,
                height = 24.dp
            )
            .background(
                brush = shimmerLoading(),
                shape = RoundedCornerShape(4.dp),
            )
    )
}

@Preview
@Composable
fun ShimmerLoadingLocalPreview() {
    Spacer(
        modifier = Modifier
            .size(
                width = 96.dp,
                height = 100.dp
            )
            .background(
                brush = shimmerLoading(),
                shape = RoundedCornerShape(4.dp),
            )
    )
}
