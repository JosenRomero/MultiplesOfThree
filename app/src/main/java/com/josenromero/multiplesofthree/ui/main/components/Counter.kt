package com.josenromero.multiplesofthree.ui.main.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CounterAnimation(
    modifier: Modifier = Modifier,
    number: Int,
    animLabel: String,
    fontSize: TextUnit = 40.sp,
    fontWeight: FontWeight = FontWeight.Bold,
    textAlign: TextAlign = TextAlign.Start
) {

    var value by remember { mutableStateOf(0) }

    val counter by animateIntAsState(
        targetValue = value,
        animationSpec = tween(
            durationMillis = 1000,
            easing = FastOutSlowInEasing
        ),
        label = animLabel
    )

    LaunchedEffect(number) {
        value = number
    }

    CustomText(
        text = "$counter",
        modifier = modifier,
        fontSize = fontSize,
        fontWeight = fontWeight,
        textAlign = textAlign
    )

}

@Composable
fun Counter(
    modifier: Modifier = Modifier,
    number: Int,
    text: String
) {

    Column(
        modifier = modifier
    ) {
        CounterAnimation(
            modifier = Modifier.fillMaxWidth(),
            number = number,
            animLabel = "counter animation",
            textAlign = TextAlign.Center
        )
        CustomText(
            text = text,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
    }

}