package com.josenromero.multiplesofthree.ui.main.views

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
import androidx.compose.ui.unit.sp
import com.josenromero.multiplesofthree.data.Coin
import com.josenromero.multiplesofthree.data.GameMode
import com.josenromero.multiplesofthree.data.GameState
import com.josenromero.multiplesofthree.data.Particle
import com.josenromero.multiplesofthree.data.Stage
import com.josenromero.multiplesofthree.data.Step
import com.josenromero.multiplesofthree.data.player.PlayerEntity
import com.josenromero.multiplesofthree.ui.main.components.Board
import com.josenromero.multiplesofthree.ui.main.components.AnimatedCoin
import com.josenromero.multiplesofthree.ui.main.components.AnimatedFadeIn
import com.josenromero.multiplesofthree.ui.main.components.AnimatedParticle
import com.josenromero.multiplesofthree.ui.main.components.CustomText
import com.josenromero.multiplesofthree.ui.main.components.GameOver
import com.josenromero.multiplesofthree.ui.main.components.HUD
import com.josenromero.multiplesofthree.ui.main.components.MedalCard
import com.josenromero.multiplesofthree.ui.main.components.MissionAnimated
import com.josenromero.multiplesofthree.ui.main.components.MissionText
import com.josenromero.multiplesofthree.ui.main.components.SimpleTopAppBar
import com.josenromero.multiplesofthree.ui.main.navigation.AppScreens
import com.josenromero.multiplesofthree.ui.theme.MultiplesOfThreeTheme
import com.josenromero.multiplesofthree.utils.Constants
import com.josenromero.multiplesofthree.utils.checkAchievement
import kotlinx.coroutines.delay

@Composable
fun PlayScreen(
    gameState:  GameState,
    gameMode: GameMode,
    player: PlayerEntity,
    coins: MutableList<Coin>,
    particles: MutableList<Particle>,
    isCleanBoard: Boolean,
    isPreCleanBoard: Boolean,
    stage: Stage,
    stageUpdate: () -> Unit,
    activeBoard: (value: Boolean) -> Unit,
    updatePlayer: (bestScore: Int?, achievements: List<String>?) -> Unit,
    onClick: (position: Pair<Int, Int>, coordinate: Offset) -> Unit,
    onNavigateToAScreen: (route: String) -> Unit,
    audioPlay: (name: String) -> Unit,
) {

    var isShowMission by remember { mutableStateOf(true) }
    var isShowMedals by remember { mutableStateOf(false) }
    var scoreCoordinates by remember { mutableStateOf(Offset.Zero) }
    val medals = remember { mutableStateListOf<String>() }

    LaunchedEffect(key1 = gameState.score) {
        if (checkScoreToStageUpdate(gameState.score)) {
            medals.removeAll(medals)
            val newAchievements = checkAchievement(achievements = player.achievements, score = gameState.score, gameMode = gameMode)
            medals.addAll(newAchievements)
            val achievements = player.achievements.plus(newAchievements)
            val bestScore: Int? = if (gameState.score > player.bestScore) gameState.score else null

            if (!checkWin(gameState.score, stage.step)) {
                stageUpdate()
                if (newAchievements.isNotEmpty()) {
                    isShowMedals = true
                    delay(7000) // waiting for the medalCard animation
                    isShowMedals = false
                }
                isShowMission = true
                delay(7000) // waiting for the mission animation
                activeBoard(true)
                updatePlayer(bestScore, achievements)
            } else {
                isShowMedals = true
                delay(7000) // waiting for the medalCard animation
                updatePlayer(gameState.score, achievements)
                onNavigateToAScreen(AppScreens.EndScreen.route)
            }
        }
    }

    Scaffold(
        topBar = {
            SimpleTopAppBar(
                title = {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CustomText(
                            text = stringResource(id = gameMode.textId), // the gameMode.textId is like R.string.home_screen_menu_easy_mode
                            modifier = Modifier
                                .background(
                                    color = MaterialTheme.colorScheme.secondary,
                                    shape = RoundedCornerShape(50.dp)
                                )
                                .padding(vertical = 8.dp, horizontal = 16.dp),
                            color = MaterialTheme.colorScheme.onSecondary,
                            fontSize = 16.sp
                        )
                    }
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
            BoxWithConstraints {

                val isSmallScreen = maxWidth < Constants.SMALL_SCREEN
                val cellSize = if (isSmallScreen) Constants.CELL_SIZE_SMALL else Constants.CELL_SIZE

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    HUD(
                        modifier = Modifier
                            .onGloballyPositioned { layoutCoordinates ->
                                val pos = layoutCoordinates.positionInRoot()
                                val width = layoutCoordinates.size.width / 2
                                val height = layoutCoordinates.size.height
                                scoreCoordinates = Offset(x = pos.x + width, y = pos.y - height)
                            },
                        gameMode = gameMode,
                        score = gameState.score,
                        hearts = gameState.hearts
                    )
                    if (isShowMission) {
                        MissionAnimated(
                            step = stage.step,
                            btnClose = {
                                isShowMission = false
                            }
                        )
                    }
                    // center Column
                    if (!isShowMission && !isShowMedals) {
                        AnimatedFadeIn {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Board(
                                    board = gameState.board,
                                    cellSize = cellSize,
                                    isCleanBoard = isCleanBoard,
                                    isPreCleanBoard = isPreCleanBoard,
                                    onClick = onClick,
                                    audioPlay = audioPlay
                                )
                                MissionText(
                                    text = stringResource(id = stage.step.textId) // the step.textId is like R.string.howToPlay_screen_text_mission_1
                                )
                            }
                        }
                    }
                    if (isShowMedals) {
                        MedalCard(
                            medals = medals,
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
                            },
                            audioPlay = audioPlay
                        )
                    }
                    coins.toList().forEach { coin ->
                        AnimatedCoin(
                            id = coin.id,
                            initialPosition = coin.coordinate,
                            finalPosition = scoreCoordinates,
                            audioPlay = audioPlay
                        )
                    }
                    particles.toList().forEach { particle ->
                        AnimatedParticle(
                            id = particle.id,
                            initialPosition = Offset(
                                x = particle.coordinate.x - (cellSize.value / 2),
                                y = particle.coordinate.y - (cellSize.value / 2)
                            ),
                            particleDescription = "explosion particle ${particle.id}",
                            audioPlay = audioPlay
                        )
                    }
                }
            }
        }
    }

}

fun checkScoreToStageUpdate(score: Int): Boolean {
    return score > 0 && (score % 10) == 0
}

fun checkWin(score: Int, step: Step): Boolean {
    return score >= 160 && step == Step.Step4
}

@Preview(uiMode = UI_MODE_NIGHT_NO, showSystemUi = true)
@Composable
fun PlayScreenPreview() {
    MultiplesOfThreeTheme {
        PlayScreen(
            gameState = GameState(board = listOf(listOf(-1, -1, 3), listOf(-1, -1, -1), listOf(-1, -1, -1))),
            gameMode = GameMode.EASY,
            player = PlayerEntity(bestScore = 0, achievements = emptyList()),
            coins = mutableListOf(),
            particles = mutableListOf(),
            isCleanBoard = false,
            isPreCleanBoard = false,
            stage = Stage(),
            stageUpdate = {},
            activeBoard = {},
            updatePlayer = { _, _ ->},
            onClick = {_, _ ->},
            onNavigateToAScreen = {},
            audioPlay = {}
        )
    }
}