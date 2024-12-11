package com.josenromero.multiplesofthree.ui.main.views

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.josenromero.multiplesofthree.data.Coin
import com.josenromero.multiplesofthree.data.GameState
import com.josenromero.multiplesofthree.data.player.PlayerEntity
import com.josenromero.multiplesofthree.ui.main.components.Board
import com.josenromero.multiplesofthree.ui.main.components.AnimatedCoin
import com.josenromero.multiplesofthree.ui.main.components.GameOver
import com.josenromero.multiplesofthree.ui.main.components.HUD
import com.josenromero.multiplesofthree.ui.main.components.Score
import com.josenromero.multiplesofthree.ui.main.components.SimpleTopAppBar
import com.josenromero.multiplesofthree.ui.main.navigation.AppScreens
import com.josenromero.multiplesofthree.ui.theme.MultiplesOfThreeTheme

@Composable
fun PlayScreen(
    gameState:  GameState,
    player: PlayerEntity,
    coins: MutableList<Coin>,
    removeOneCoin: (coin: Coin) -> Unit,
    updatePlayer: (bestScore: Int?, achievements: List<String>?) -> Unit,
    onClick: (position: Pair<Int, Int>, coordinate: Offset) -> Unit,
    onNavigateToAScreen: (route: String) -> Unit,
    audioPlay: (name: String) -> Unit,
) {

    Scaffold(
        topBar = {
            SimpleTopAppBar(
                title = {
                    Score(value = gameState.score)
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
                Text(
                    text = "Find and tap all multiples of 3",
                    modifier = Modifier.padding(vertical = 40.dp),
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 14.sp
                )
                Board(
                    board = gameState.board,
                    onClick = onClick,
                    audioPlay = audioPlay
                )
                if (gameState.isGameOver) {

                    if (gameState.score > player.bestScore) {
                        updatePlayer(gameState.score, null)
                    }

                    GameOver(
                        score = gameState.score,
                        bestScore = player.bestScore,
                        onNavigateToAScreen = onNavigateToAScreen
                    )
                }
            }
            coins.toList().forEach { coin ->
                AnimatedCoin(
                    id = coin.id,
                    initialPosition = coin.coordinate,
                    finalPosition = Offset.Zero,
                    onAnimationEnd = {
                        removeOneCoin(coin)
                    }
                )
            }

        }
    }

}

@Preview(uiMode = UI_MODE_NIGHT_NO, showSystemUi = true)
@Composable
fun PlayScreenPreview() {
    MultiplesOfThreeTheme {
        PlayScreen(
            gameState = GameState(board = listOf(listOf(-1, -1, 3), listOf(-1, -1, -1), listOf(-1, -1, -1))),
            player = PlayerEntity(bestScore = 0, achievements = emptyList()),
            coins = mutableListOf(),
            removeOneCoin = {},
            updatePlayer = { _, _ ->},
            onClick = {_, _ ->},
            onNavigateToAScreen = {},
            audioPlay = {}
        )
    }
}