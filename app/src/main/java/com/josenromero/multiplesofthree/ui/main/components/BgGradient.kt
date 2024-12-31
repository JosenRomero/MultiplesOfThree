package com.josenromero.multiplesofthree.ui.main.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path

@Composable
fun BgGradient(
    modifier: Modifier = Modifier,
    bg1: Color,
    bg2: Color
) {

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            val width = size.width
            val height = size.height

            drawPath(
                path = Path().apply {
                    moveTo(0f, 0f) // Top left corner
                    lineTo(width, 0f) // Top right corner
                    lineTo(width, height / 2f) // Bottom right corner
                    lineTo(0f, (height / 4f) * 3) // Bottom left point
                    close()
                },
                brush = Brush.horizontalGradient(
                    listOf(bg1, bg2)
                )
            )
        }
    }

}