package com.josenromero.multiplesofthree.ui.main.views

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
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
                    text = stringResource(id = R.string.howToPlay_screen_subtitle_game_rules),
                    modifier = Modifier.padding(vertical = 16.dp),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
                GameRuleItem(
                    textId = R.string.howToPlay_screen_text_game_rules_1,
                    iconId = R.drawable.last_medal,
                    contentDescription = "las medal icon"
                )
                GameRuleItem(
                    textId = R.string.howToPlay_screen_text_game_rules_2,
                    iconId = R.drawable.heart,
                    contentDescription = "heart icon"
                )
                GameRuleItem(
                    textId = R.string.howToPlay_screen_text_game_rules_3,
                    iconId = R.drawable.heart_broken,
                    contentDescription = "heart broken icon"
                )
                GameRuleItem(
                    textId = R.string.howToPlay_screen_text_game_rules_4,
                    iconId = R.drawable.coin,
                    contentDescription = "coin icon"
                )
                CustomText(
                    text = stringResource(id = R.string.howToPlay_screen_subtitle_game_missions),
                    modifier = Modifier
                        .padding(top = 30.dp, bottom = 16.dp),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
                CustomText(text = stringResource(id = R.string.howToPlay_screen_text_mission_1))
                CustomText(text = stringResource(id = R.string.howToPlay_screen_text_mission_2))
                CustomText(text = stringResource(id = R.string.howToPlay_screen_text_mission_3))
                CustomText(text = stringResource(id = R.string.howToPlay_screen_text_mission_4))
            }
        }
    }

}

@Composable
fun GameRuleItem(
    @StringRes textId: Int,
    @DrawableRes iconId: Int,
    contentDescription: String
) {

    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = iconId),
            contentDescription = contentDescription,
            modifier = Modifier.size(44.dp)
        )
        CustomText(text = stringResource(id = textId))
    }

}