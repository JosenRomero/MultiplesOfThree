package com.josenromero.multiplesofthree.ui.main.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun Counter(
    modifier: Modifier = Modifier,
    number: Int,
    text: String
) {

    var value by remember { mutableStateOf(0) }

    val counter by animateIntAsState(
        targetValue = value,
        animationSpec = tween(
            durationMillis = 1000,
            easing = FastOutSlowInEasing
        ),
        label = "counter: $text animation"
    )

    LaunchedEffect(Unit) {
        value = number
    }

    Column(
        modifier = modifier
    ) {
        CustomText(
            text = "$counter",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        CustomText(
            text = text,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
    }

}