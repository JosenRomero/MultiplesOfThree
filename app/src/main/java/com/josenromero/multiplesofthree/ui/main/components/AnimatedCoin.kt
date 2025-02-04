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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.josenromero.multiplesofthree.R
import com.josenromero.multiplesofthree.utils.Constants
import kotlinx.coroutines.launch

@SuppressLint("UseOfNonLambdaOffsetOverload")
@Composable
fun AnimatedCoin(
    id: Int,
    initialPosition: Offset,
    finalPosition: Offset
) {

    val coinSize = 22.dp
    val offsetX = remember { Animatable(initialPosition.x - (Constants.CELL_SIZE.value / 2) + (coinSize.value)) }
    val offsetY = remember { Animatable(initialPosition.y) }
    val alpha = remember { Animatable(1f) }

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
            alpha.animateTo(
                targetValue = 0f,
                animationSpec = tween(1400)
            )
        }
    }

    Box(
        modifier = Modifier
            .offset(x = offsetX.value.pxToDp(), y = offsetY.value.pxToDp())
            .alpha(alpha.value)
    ) {
        Image(
            painter = painterResource(id = R.drawable.coin),
            contentDescription = "coin icon",
            modifier = Modifier.size(coinSize)
        )
    }

}

@Composable
fun Float.pxToDp(): Dp = (this / LocalDensity.current.density).dp