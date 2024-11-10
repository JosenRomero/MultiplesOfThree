package com.josenromero.multiplesofthree.ui.main.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CustomAnimatedVisibility(
    delayTime: Long,
    enter: EnterTransition,
    exit: ExitTransition,
    content: @Composable AnimatedVisibilityScope.() -> Unit
) {

    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        launch {
            delay(delayTime)
            visible = true
        }
    }

    AnimatedVisibility(
        visible = visible,
        enter = enter,
        exit = exit,
        content = content
    )

}

@Composable
fun AnimatedScaleInTransition(
    visible: Boolean,
    content: @Composable AnimatedVisibilityScope.() -> Unit
) {

    AnimatedVisibility(
        visible = visible,
        enter = scaleIn(
            animationSpec = tween(700)
        ),
        exit = scaleOut(
            animationSpec = tween(700)
        ),
        content = content
    )

}

@Composable
fun AnimatedFadeIn(
    delayTime: Long = 0,
    content: @Composable AnimatedVisibilityScope.() -> Unit
) {

    CustomAnimatedVisibility(
        delayTime = delayTime,
        enter = fadeIn(
            animationSpec = tween(1000)
        ),
        exit = shrinkOut() + fadeOut(),
        content = content
    )

}

@Composable
fun AnimatedFadeAndExpandVertically(
    delayTime: Long = 0,
    content: @Composable AnimatedVisibilityScope.() -> Unit
) {

    CustomAnimatedVisibility(
        delayTime = delayTime,
        enter = fadeIn() + expandVertically(expandFrom = Alignment.Top),
        exit = shrinkOut() + fadeOut(),
        content = content
    )

}

@Composable
fun AnimatedFadeAndExpandHorizontally(
    delayTime: Long = 0,
    content: @Composable AnimatedVisibilityScope.() -> Unit
) {

    CustomAnimatedVisibility(
        delayTime = delayTime,
        enter = fadeIn() + expandHorizontally(expandFrom = Alignment.End),
        exit = shrinkOut() + fadeOut(),
        content = content
    )

}