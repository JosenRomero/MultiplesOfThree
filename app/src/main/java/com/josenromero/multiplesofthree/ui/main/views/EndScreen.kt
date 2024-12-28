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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.josenromero.multiplesofthree.R
import com.josenromero.multiplesofthree.ui.main.components.AnimatedTextScale
import com.josenromero.multiplesofthree.ui.main.components.CustomText
import com.josenromero.multiplesofthree.ui.main.navigation.AppScreens

@Composable
fun EndScreen(
    textIdForGameMode: Int,
    onNavigateToAScreen: (route: String) -> Unit
) {

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
                        .padding(top = 50.dp, bottom = 20.dp),
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
        }
    }

}