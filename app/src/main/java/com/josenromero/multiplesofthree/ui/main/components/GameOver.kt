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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.josenromero.multiplesofthree.ui.main.navigation.AppScreens
import com.josenromero.multiplesofthree.ui.theme.MultiplesOfThreeTheme

@Composable
fun GameOver(
    score: Int,
    bestScore: Int,
    onNavigateToAScreen: (route: String) -> Unit
) {
    
    AnimatedTransitionDialog(onDismissRequest = { }) {
        GameOverContent(
            score = score,
            bestScore = bestScore,
            onNavigateToAScreen = onNavigateToAScreen
        )
    }

}

@Composable
fun GameOverContent(
    score: Int,
    bestScore: Int,
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
            Text(text = "Game Over!")
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.onPrimary,
                    shape = RoundedCornerShape(0.dp, 0.dp, 25.dp, 10.dp)
                )
                .padding(16.dp),
        ) {
            Text(
                text = "Your score is $score points",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 10.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
            Text(
                text = if (score > bestScore) "New Best Score: $score" else "Best Score: $bestScore",
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
                Button(onClick = { onNavigateToAScreen(AppScreens.HomeScreen.route) }) {
                    Text(text = "Menu")
                }
                Button(onClick = { onNavigateToAScreen(AppScreens.PlayScreen.route) }) {
                    Text(text = "Try Again")
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
            onNavigateToAScreen = {}
        )
    }
}