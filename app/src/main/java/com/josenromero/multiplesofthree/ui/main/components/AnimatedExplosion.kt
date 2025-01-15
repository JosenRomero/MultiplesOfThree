package com.josenromero.multiplesofthree.ui.main.components

import android.annotation.SuppressLint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josenromero.multiplesofthree.utils.Constants
import kotlinx.coroutines.launch

@SuppressLint("UseOfNonLambdaOffsetOverload")
@Composable
fun AnimatedExplosion(
    id: Int,
    initialPosition: Offset
) {

    val particleSize = 10.dp
    val offsetX = remember { Animatable(initialPosition.x - (Constants.CELL_SIZE.value / 2) + (particleSize.value)) }
    val offsetY = remember { Animatable(initialPosition.y + (Constants.CELL_SIZE.value / 2) + (particleSize.value)) }
    val alpha = remember { Animatable(1f) }

    LaunchedEffect(id) {
        launch {
            offsetX.animateTo(
                targetValue = initialPosition.x + getRandomPosition(range = -75..75),
                animationSpec = tween(500)
            )
        }
        launch {
            offsetY.animateTo(
                targetValue = initialPosition.y + getRandomPosition(range = -75..75),
                animationSpec = tween(500)
            )
        }
        launch {
            alpha.animateTo(
                targetValue = 0f,
                animationSpec = tween(700)
            )
        }
    }

    Box(
        modifier = Modifier
            .offset(x = offsetX.value.pxToDp(), y = offsetY.value.pxToDp())
            .alpha(alpha.value)
    ) {
        Icon(
            painter = painterResource(id = getRandomParticle()),
            contentDescription = "explosion img",
            modifier = Modifier.size(particleSize),
            tint = MaterialTheme.colorScheme.onSecondary
        )
    }

}

fun getRandomPosition(range: IntRange): Float {
    return (range).random().toFloat()
}

fun getRandomParticle(): Int {
    return Constants.particlesList.random()
}

@Preview(showSystemUi = true)
@Composable
fun ExplosionPreview() {
    AnimatedExplosion(id = 1, initialPosition = Offset(25f, 5f))
}