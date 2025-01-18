package com.josenromero.multiplesofthree.ui.main.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.josenromero.multiplesofthree.R
import com.josenromero.multiplesofthree.ui.main.navigation.AppScreens
import com.josenromero.multiplesofthree.ui.theme.MultiplesOfThreeTheme

@Composable
fun GameOver(
    score: Int,
    bestScore: Int,
    onNavigateToAScreen: (route: String) -> Unit,
    updatePlayer: () -> Unit
) {

    var isShowContent by remember { mutableStateOf(true) }
    var newBestScore by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        if (score > bestScore) {
            newBestScore = true
            updatePlayer()
        }
    }

    if (isShowContent) {
        AnimatedTransitionDialog(onDismissRequest = { }) {
            GameOverContent(
                score = score,
                bestScore = bestScore,
                newBestScore = newBestScore,
                closeContent = { isShowContent = false },
                onNavigateToAScreen = onNavigateToAScreen
            )
        }
    }

}

@Composable
fun GameOverContent(
    score: Int,
    bestScore: Int,
    newBestScore: Boolean,
    closeContent: () -> Unit,
    onNavigateToAScreen: (route: String) -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(25.dp, 10.dp, 0.dp, 0.dp)
                )
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.text_game_over),
                color = MaterialTheme.colorScheme.surface
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(0.dp, 0.dp, 25.dp, 10.dp)
                )
                .padding(16.dp),
        ) {
            Text(
                text = "${stringResource(id = R.string.text_score)} $score",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 10.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
            Text(
                text = if (newBestScore) "${stringResource(id = R.string.text_new_best_score)} $score" else "${stringResource(id = R.string.text_best_score)} $bestScore",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 20.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                OutlinedButton(onClick = {
                    onNavigateToAScreen(AppScreens.HomeScreen.route)
                    closeContent()
                }) {
                    Text(text = stringResource(id = R.string.btn_menu))
                }
                Button(onClick = {
                    onNavigateToAScreen(AppScreens.PlayScreen.route)
                    closeContent()
                }) {
                    Text(
                        text = stringResource(id = R.string.btn_try_again),
                        color = MaterialTheme.colorScheme.surface
                    )
                }
            }
        }
    }

}

@Preview(uiMode = UI_MODE_NIGHT_NO)
@Composable
fun GameOverPreview() {
    MultiplesOfThreeTheme {
        GameOverContent(
            score = 301,
            bestScore = 300,
            newBestScore = false,
            closeContent = {},
            onNavigateToAScreen = {}
        )
    }
}