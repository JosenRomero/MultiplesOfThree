package com.josenromero.multiplesofthree.ui.main.views

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.josenromero.multiplesofthree.R
import com.josenromero.multiplesofthree.data.GameMode
import com.josenromero.multiplesofthree.data.player.PlayerEntity
import com.josenromero.multiplesofthree.ui.main.components.Counter
import com.josenromero.multiplesofthree.ui.main.components.CustomBottomAppBar
import com.josenromero.multiplesofthree.ui.main.components.CustomIconButton
import com.josenromero.multiplesofthree.ui.main.components.Menu
import com.josenromero.multiplesofthree.ui.main.navigation.AppScreens
import com.josenromero.multiplesofthree.ui.theme.MultiplesOfThreeTheme

@Composable
fun HomeScreen(
    preferencesLoading: Boolean,
    firstTime: Boolean,
    gameMode: GameMode,
    player: PlayerEntity,
    changeGameMode: (gameMode: GameMode) -> Unit,
    audioPlay: (name: String) -> Unit,
    backgroundMusicPlay: () -> Unit,
    onNavigateToAScreen: (route: String) -> Unit
) {

    LaunchedEffect(key1 = preferencesLoading) {
        if (!preferencesLoading) {

            backgroundMusicPlay()

            if (firstTime) {
                onNavigateToAScreen(AppScreens.HowToPlayScreen.route)
            }
        }
    }

    Scaffold(
        bottomBar = {
            if (!firstTime && !preferencesLoading) {
                CustomBottomAppBar {
                    CustomIconButton(
                        onClick = { onNavigateToAScreen(AppScreens.HowToPlayScreen.route) },
                        icon = painterResource(id = R.drawable.mark),
                        contentDescription = "How To Play icon"
                    )
                    CustomIconButton(
                        onClick = { onNavigateToAScreen(AppScreens.SettingsScreen.route) },
                        icon = painterResource(id = R.drawable.settings),
                        contentDescription = "setting icon"
                    )
                    CustomIconButton(
                        onClick = { onNavigateToAScreen(AppScreens.AchievementsScreen.route) },
                        icon = painterResource(id = R.drawable.achievements),
                        contentDescription = "achievements icon"
                    )
                    CustomIconButton(
                        onClick = { onNavigateToAScreen(AppScreens.AboutScreen.route) },
                        icon = painterResource(id = R.drawable.about),
                        contentDescription = "about icon"
                    )
                }
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.title_img),
                contentDescription = "title image",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentScale = ContentScale.FillWidth
            )
            if (!firstTime && !preferencesLoading) {
                Button(
                    onClick = { onNavigateToAScreen(AppScreens.PlayScreen.route) },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.play),
                        contentDescription = "play icon",
                        tint = MaterialTheme.colorScheme.surface
                    )
                    Text(
                        text = stringResource(id = R.string.home_screen_text_play),
                        modifier = Modifier.padding(start = 8.dp),
                        color = MaterialTheme.colorScheme.surface,
                        fontSize = 16.sp
                    )
                }
                Menu(
                    selected = gameMode,
                    changeGameMode = changeGameMode,
                    audioPlay = audioPlay
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 40.dp, horizontal = 8.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Counter(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp),
                        number = player.achievements.size,
                        text = stringResource(id = R.string.home_screen_text_1)
                    )
                    Counter(
                        modifier = Modifier
                            .weight(1f),
                        number = player.bestScore,
                        text = stringResource(id = R.string.text_best_score)
                    )
                }
            }
        }
    }

}

@Preview(uiMode = UI_MODE_NIGHT_NO, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    MultiplesOfThreeTheme {
        HomeScreen(
            preferencesLoading = false,
            firstTime = false,
            gameMode = GameMode.EASY,
            player = PlayerEntity(bestScore = 21),
            changeGameMode = {},
            audioPlay = {},
            backgroundMusicPlay = {},
            onNavigateToAScreen = {}
        )
    }
}