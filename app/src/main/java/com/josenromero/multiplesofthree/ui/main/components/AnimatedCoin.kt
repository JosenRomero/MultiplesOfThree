package com.josenromero.multiplesofthree.ui.main.components

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
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
    initialPosition: Offset,
    finalPosition: Offset,
    onAnimationEnd: () -> Unit
) {

    var moved by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        launch {
            moved = true
        }
    }

    val offsetX = animateDpAsState(
        targetValue = if (moved) finalPosition.x.pxToDp() else initialPosition.x.pxToDp() + Constants.CELL_SIZE / 2,
        animationSpec = tween(700),
        finishedListener = { onAnimationEnd() },
        label = ""
    )

    val offsetY = animateDpAsState(
        targetValue = if (moved) finalPosition.y.pxToDp() else initialPosition.y.pxToDp() - Constants.CELL_SIZE / 2,
        animationSpec = tween(700),
        label = ""
    )

    Box(
        modifier = Modifier
            .offset(x = offsetX.value, y = offsetY.value)
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