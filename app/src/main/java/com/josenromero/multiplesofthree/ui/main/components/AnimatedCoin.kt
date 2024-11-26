package com.josenromero.multiplesofthree.ui.main.components

import android.annotation.SuppressLint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.josenromero.multiplesofthree.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("UseOfNonLambdaOffsetOverload")
@Composable
fun AnimatedCoin(
    id: Int,
    initialPosition: Offset,
    finalPosition: Offset,
    onAnimationEnd: () -> Unit
) {

    val offsetX = remember { Animatable(initialPosition.x) }
    val offsetY = remember { Animatable(initialPosition.y) }

    LaunchedEffect(id) {
        launch {
            offsetX.animateTo(
                targetValue = finalPosition.x,
                animationSpec = tween(700)
            )
        }
        launch {
            offsetY.animateTo(
                targetValue = finalPosition.y,
                animationSpec = tween(700)
            )
        }
        launch {
            delay(1400)
            onAnimationEnd()
        }
    }

    Box(
        modifier = Modifier
            .offset(x = offsetX.value.pxToDp(), y = offsetY.value.pxToDp())
    ) {
        Image(
            painter = painterResource(id = R.drawable.coin),
            contentDescription = "coin icon",
            modifier = Modifier.size(22.dp)
        )
    }

}

@Composable
fun Float.pxToDp(): Dp = (this / LocalDensity.current.density).dp