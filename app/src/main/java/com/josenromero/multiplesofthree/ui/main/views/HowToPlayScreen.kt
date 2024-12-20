package com.josenromero.multiplesofthree.ui.main.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.josenromero.multiplesofthree.R
import com.josenromero.multiplesofthree.ui.main.components.CustomText
import com.josenromero.multiplesofthree.ui.main.components.CustomTitle
import com.josenromero.multiplesofthree.ui.main.components.SimpleTopAppBar

@Composable
fun HowToPlayScreen(
    firstTime: Boolean,
    howToPlayViewed: () -> Unit,
    onNavigateToBack: () -> Unit
) {

    LaunchedEffect(key1 = Unit) {
        if (firstTime) {
            howToPlayViewed()
        }
    }

    Scaffold(
        topBar = {
            SimpleTopAppBar(
                title = {
                    CustomTitle(text = stringResource(id = R.string.howToPlay_screen_title))
                },
                onNavigateToAScreen = { onNavigateToBack() }
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                CustomText(
                    text = stringResource(id = R.string.howToPlay_screen_subtitle_game_missions),
                    modifier = Modifier.padding(vertical = 16.dp),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
                CustomText(text = stringResource(id = R.string.howToPlay_screen_text_mission_1))
                CustomText(text = stringResource(id = R.string.howToPlay_screen_text_mission_2))
                CustomText(text = stringResource(id = R.string.howToPlay_screen_text_mission_3))
                CustomText(text = stringResource(id = R.string.howToPlay_screen_text_mission_4))

                CustomText(
                    text = stringResource(id = R.string.howToPlay_screen_subtitle_game_rules),
                    modifier = Modifier.padding(vertical = 16.dp),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
                CustomText(text = stringResource(id = R.string.howToPlay_screen_text_game_rules_1))
                CustomText(text = stringResource(id = R.string.howToPlay_screen_text_game_rules_2))
                CustomText(text = stringResource(id = R.string.howToPlay_screen_text_game_rules_3))

            }
        }
    }

}