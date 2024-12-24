package com.josenromero.multiplesofthree.ui.main.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun MissionText(
    text: String
) {

    AnimatedFadeIn {
        CustomText(
            text = text,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 40.dp),
            textAlign = TextAlign.Center
        )
    }

}