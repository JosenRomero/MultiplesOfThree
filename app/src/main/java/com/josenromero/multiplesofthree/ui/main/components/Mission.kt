package com.josenromero.multiplesofthree.ui.main.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.josenromero.multiplesofthree.R
import com.josenromero.multiplesofthree.data.Step
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MissionAnimated(
    step: Step,
    btnClose: () -> Unit
) {

    val animateTrigger = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        launch {
            delay(1000)
            animateTrigger.value = true
            delay(5000)
            animateTrigger.value = false
            delay(2000)
            btnClose()
        }
    }

    AnimatedVisibility(
        visible = animateTrigger.value,
        enter = slideInHorizontally(
            initialOffsetX = { -it },
            animationSpec = tween(
                durationMillis = 700,
                easing = LinearEasing
            )
        ),
        exit = slideOutHorizontally(
            targetOffsetX = { it },
            animationSpec = tween(
                durationMillis = 700,
                easing = LinearEasing
            )
        )
    ) {
        MissionContent(
            step = step
        )
    }

}

@Composable
fun MissionContent(
    step: Step
) {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(16.dp)
        ) {
            CustomText(
                text = stringResource(id = step.textId),
                modifier = Modifier.fillMaxWidth(),
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.secondary,
                    shape = RoundedCornerShape(10.dp, 0.dp, 10.dp, 0.dp)
                )
                .padding(vertical = 8.dp)
        ) {
            CustomText(
                text = "${stringResource(id = R.string.playScreen_text_mission)} ${step.ordinal + 1}",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }

}