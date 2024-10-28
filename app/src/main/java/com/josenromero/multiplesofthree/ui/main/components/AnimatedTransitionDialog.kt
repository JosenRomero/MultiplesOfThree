package com.josenromero.multiplesofthree.ui.main.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.window.Dialog
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun AnimatedTransitionDialog(
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit
) {

    val animateTrigger = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        launch {
            delay(300)
            animateTrigger.value = true
        }
    }
    
    Dialog(onDismissRequest = onDismissRequest) {
        AnimatedScaleInTransition(visible = animateTrigger.value) {
            content()
        }
    }

}
