package com.josenromero.multiplesofthree.ui.main.views

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josenromero.multiplesofthree.data.Coin
import com.josenromero.multiplesofthree.data.GameState
import com.josenromero.multiplesofthree.data.Stage
import com.josenromero.multiplesofthree.data.player.PlayerEntity
import com.josenromero.multiplesofthree.ui.main.components.Board
import com.josenromero.multiplesofthree.ui.main.components.AnimatedCoin
import com.josenromero.multiplesofthree.ui.main.components.GameOver
import com.josenromero.multiplesofthree.ui.main.components.HUD
import com.josenromero.multiplesofthree.ui.main.components.MissionAnimated
import com.josenromero.multiplesofthree.ui.main.components.MissionText
import com.josenromero.multiplesofthree.ui.main.components.Score
import com.josenromero.multiplesofthree.ui.main.components.SimpleTopAppBar
import com.josenromero.multiplesofthree.ui.main.navigation.AppScreens
import com.josenromero.multiplesofthree.ui.theme.MultiplesOfThreeTheme

@Composable
fun PlayScreen(
    gameState:  GameState,
    player: PlayerEntity,
    coins: MutableList<Coin>,
    stage: Stage,
    stageUpdate: () -> Unit,
    updatePlayer: (bestScore: Int?, achievements: List<String>?) -> Unit,
    onClick: (position: Pair<Int, Int>, coordinate: Offset) -> Unit,
    onNavigateToAScreen: (route: String) -> Unit,
    audioPlay: (name: String) -> Unit,
) {

    var isShowMission by remember { mutableStateOf(true) }
    var scoreCoordinates by remember { mutableStateOf(Offset.Zero) }

    LaunchedEffect(key1 = gameState.score) {
        if (checkScoreToStageUpdate(gameState.score)) {
            stageUpdate()
            isShowMission = true
        }
    }

    Scaffold(
        topBar = {
            SimpleTopAppBar(
                title = {
                    Score(
                        value = gameState.score,
                        modifier = Modifier.onGloballyPositioned { layoutCoordinates ->
                            scoreCoordinates = layoutCoordinates.positionInRoot()
                        }
                    )
                },
                onNavigateToAScreen = {
                    onNavigateToAScreen(AppScreens.HomeScreen.route)
                }
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
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HUD(bestScore = player.bestScore, hearts = gameState.hearts)
                if (!isShowMission) {
                    MissionText(
                        text = stringResource(id = stage.step.textId) // the step.textId is like R.string.howToPlay_screen_text_mission_1
                    )
                }
            }
            if (isShowMission) {
                MissionAnimated(
                    step = stage.step,
                    btnClose = {
                        isShowMission = false
                    }
                )
            } else {
                Board(
                    board = gameState.board,
                    onClick = onClick,
                    audioPlay = audioPlay
                )
            }
            if (gameState.isGameOver) {
                GameOver(
                    score = gameState.score,
                    bestScore = player.bestScore,
                    onNavigateToAScreen = onNavigateToAScreen,
                    updatePlayer = {
                        updatePlayer(gameState.score, null)
                    }
                )
            }
            coins.toList().forEach { coin ->
                AnimatedCoin(
                    id = coin.id,
                    initialPosition = coin.coordinate,
                    finalPosition = scoreCoordinates
                )
            }
        }
    }

}

fun checkScoreToStageUpdate(score: Int): Boolean {
    return score > 0 && (score % 10) == 0
}

@Preview(uiMode = UI_MODE_NIGHT_NO, showSystemUi = true)
@Composable
fun PlayScreenPreview() {
    MultiplesOfThreeTheme {
        PlayScreen(
            gameState = GameState(board = listOf(listOf(-1, -1, 3), listOf(-1, -1, -1), listOf(-1, -1, -1))),
            player = PlayerEntity(bestScore = 0, achievements = emptyList()),
            coins = mutableListOf(),
            stage = Stage(),
            stageUpdate = {},
            updatePlayer = { _, _ ->},
            onClick = {_, _ ->},
            onNavigateToAScreen = {},
            audioPlay = {}
        )
    }
}