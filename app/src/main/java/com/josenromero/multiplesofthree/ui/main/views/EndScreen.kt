package com.josenromero.multiplesofthree.ui.main.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.josenromero.multiplesofthree.R
import com.josenromero.multiplesofthree.ui.main.components.AnimatedTextScale
import com.josenromero.multiplesofthree.ui.main.components.Confetti
import com.josenromero.multiplesofthree.ui.main.components.CustomText
import com.josenromero.multiplesofthree.ui.main.navigation.AppScreens
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun EndScreen(
    textIdForGameMode: Int,
    onNavigateToAScreen: (route: String) -> Unit
) {

    var coordinates by remember { mutableStateOf(Offset.Zero) }
    val isConfetti = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        launch {
            delay(1000)
            isConfetti.value = true
            delay(1000) // waiting for the confetti animation
            isConfetti.value = false
        }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AnimatedTextScale(
                    text = stringResource(id = R.string.end_screen_congratulations_text_1)
                )
                CustomText(
                    text = stringResource(id = textIdForGameMode) + stringResource(id = R.string.end_screen_congratulations_text_2), // for example "Easy Mode Completed"
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 50.dp, bottom = 20.dp)
                        .onGloballyPositioned { layoutCoordinates ->
                            val pos = layoutCoordinates.positionInRoot()
                            val middleX = layoutCoordinates.size.width / 2
                            val middleY = layoutCoordinates.size.height / 2
                            coordinates = Offset(x = pos.x + middleX, y = pos.y - middleY)
                        },
                    textAlign = TextAlign.Center
                )
                Button(
                    onClick = { onNavigateToAScreen(AppScreens.HomeScreen.route) }
                ) {
                    CustomText(
                        text = stringResource(id = R.string.end_screen_congratulations_btn_menu)
                    )
                }
            }
            if (isConfetti.value) {
                Confetti(initialPosition = coordinates)
            }
        }
    }

}