package com.josenromero.multiplesofthree.ui.main.views

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.josenromero.multiplesofthree.ui.main.components.Board
import com.josenromero.multiplesofthree.ui.theme.MultiplesOfThreeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayScreen(
    board: List<List<Int>>
) {

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "PlayScreen")
            Board(board = board)
        }
    }

}

@Preview(uiMode = UI_MODE_NIGHT_NO, showSystemUi = true)
@Composable
fun PlayScreenPreview() {
    MultiplesOfThreeTheme {
        PlayScreen(
            board = listOf(listOf(-1, -1, 3), listOf(-1, -1, -1), listOf(-1, -1, -1))
        )
    }
}