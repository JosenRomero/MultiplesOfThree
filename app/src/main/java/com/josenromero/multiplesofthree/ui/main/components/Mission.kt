package com.josenromero.multiplesofthree.ui.main.components

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

    MissionContent(
        step = step,
        animateTrigger = animateTrigger.value
    )

}

@Composable
fun MissionContent(
    step: Step,
    animateTrigger: Boolean
) {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedSlideInHorizontally(
            visible = animateTrigger,
            initialOffsetX = { -it },
            targetOffsetX = { it }
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
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
        AnimatedSlideInHorizontally(
            visible = animateTrigger,
            initialOffsetX = { it },
            targetOffsetX = { -it }
        ) {
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

}