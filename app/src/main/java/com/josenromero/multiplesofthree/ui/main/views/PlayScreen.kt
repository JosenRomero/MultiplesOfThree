package com.josenromero.multiplesofthree.ui.main.views

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josenromero.multiplesofthree.data.GameState
import com.josenromero.multiplesofthree.data.player.PlayerEntity
import com.josenromero.multiplesofthree.ui.main.components.Board
import com.josenromero.multiplesofthree.ui.main.components.GameOver
import com.josenromero.multiplesofthree.ui.main.components.HUD
import com.josenromero.multiplesofthree.ui.theme.MultiplesOfThreeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayScreen(
    gameState:  GameState,
    player: PlayerEntity,
    onClick: (position: Pair<Int, Int>) -> Unit,
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
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HUD(score = gameState.score, hearts = gameState.hearts)
                Board(board = gameState.board, onClick = onClick)
                if (gameState.isGameOver) {
                    GameOver(
                        score = gameState.score,
                        bestScore = player.bestScore,
                        onNavigateToAScreen = onNavigateToAScreen
                    )
                }
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
            onClick = {},
            onNavigateToAScreen = {}
        )
    }
}