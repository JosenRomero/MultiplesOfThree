package com.josenromero.multiplesofthree.ui.main.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.josenromero.multiplesofthree.R

@Composable
fun Score(
    value: Int,
    modifier: Modifier = Modifier
) {
    
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = modifier
        ) {
            Text(
                text = stringResource(id = R.string.text_score),
                modifier = Modifier.padding(end = 8.dp),
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