package com.josenromero.multiplesofthree.ui.main.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josenromero.multiplesofthree.utils.Constants

@Composable
fun CustomProgressIndicator(
    number: Int
) {

    var value by remember { mutableStateOf(0.0f) }

    val animationProgress by animateFloatAsState(
        targetValue = value,
        animationSpec = tween(
            durationMillis = 1000,
            easing = FastOutSlowInEasing
        ),
        label = "animation progress"
    )

    LaunchedEffect(Unit) {
        value = ((100 / Constants.achievementsList.size) * number).toFloat() / 100
    }

    LinearProgressIndicator(
        progress = { animationProgress },
        modifier = Modifier
            .fillMaxWidth()
            .height(10.dp),
        strokeCap = StrokeCap.Round,
        gapSize = (-15).dp, // size of the gap between the progress indicator and the track
        drawStopIndicator = {}
    )

}

@Preview
@Composable
fun CustomProgressIndicatorPreview() {
    CustomProgressIndicator(number = 2)
}