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
import androidx.compose.ui.unit.Dp
import com.josenromero.multiplesofthree.ui.main.viewmodels.Audios
import com.josenromero.multiplesofthree.utils.Constants
import com.josenromero.multiplesofthree.utils.getRandomColor
import com.josenromero.multiplesofthree.utils.getRandomParticle
import com.josenromero.multiplesofthree.utils.getRandomPosition
import kotlinx.coroutines.launch

@SuppressLint("UseOfNonLambdaOffsetOverload")
@Composable
fun AnimatedParticle(
    id: Int,
    initialPosition: Offset,
    particleSize: Dp = Constants.DEFAULT_PARTICLE_SIZE,
    posRange: IntRange = -75..75,
    particleDescription: String,
    isRandomColor: Boolean = false,
    audioPlay: ((name: String) -> Unit)? = null
) {

    val offsetX = remember { Animatable(initialPosition.x) }
    val offsetY = remember { Animatable(initialPosition.y) }
    val alpha = remember { Animatable(1f) }

    LaunchedEffect(id) {

        audioPlay?.invoke(Audios.AudioBadNumber.name)

        launch {
            offsetX.animateTo(
                targetValue = initialPosition.x + getRandomPosition(range = posRange),
                animationSpec = tween(500)
            )
        }
        launch {
            offsetY.animateTo(
                targetValue = initialPosition.y + getRandomPosition(range = posRange),
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
            contentDescription = particleDescription,
            modifier = Modifier.size(particleSize),
            tint = if (isRandomColor) getRandomColor() else MaterialTheme.colorScheme.onSecondary
        )
    }

}