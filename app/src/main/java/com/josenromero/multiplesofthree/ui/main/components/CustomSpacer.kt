package com.josenromero.multiplesofthree.ui.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomSpacer(
    modifier: Modifier = Modifier,
    height: Dp = 1.dp,
    bg: Color = MaterialTheme.colorScheme.secondary
) {

    Spacer(
        modifier = modifier
            .height(height)
            .fillMaxWidth()
            .background(color = bg)
    )

}