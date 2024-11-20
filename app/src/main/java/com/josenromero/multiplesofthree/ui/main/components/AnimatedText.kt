package com.josenromero.multiplesofthree.ui.main.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun Score(
    value: Int,
) {
    
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Text(
                text = "Score ",
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
            AnimatedScore(
                score = value
            )
        }
    }
    
}

@Composable
fun AnimatedScore(
    score: Int,
    modifier: Modifier = Modifier
) {

    var oldScore by remember { mutableStateOf(score) }

    SideEffect {
        oldScore = score
    }

    Row(
        modifier = modifier
    ){

        val scoreString = score.toString()
        val oldScoreString = oldScore.toString()

        for ( i in scoreString.indices) {

            val newChar = scoreString[i]
            val oldChar = oldScoreString.getOrNull(i)

            val char = if (oldChar == newChar) {
                oldScoreString[i]
            } else {
                scoreString[i]
            }

            AnimatedContent(
                targetState = char,
                transitionSpec = {
                    slideInVertically { it } togetherWith slideOutVertically { -it }
                },
                label = ""
            ) { char ->
                Text(
                    text = char.toString(),
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    softWrap = false
                )
            }

        }

    }

}