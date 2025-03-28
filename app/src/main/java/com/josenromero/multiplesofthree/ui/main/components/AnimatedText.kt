package com.josenromero.multiplesofthree.ui.main.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.josenromero.multiplesofthree.R

@Composable
fun Score(
    value: Int,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.secondary,
                shape = RoundedCornerShape(50.dp)
            )
            .padding(vertical = 8.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.coin),
            contentDescription = "coin icon",
            modifier = Modifier
                .size(33.dp)
                .padding(end = 8.dp)
        )
        AnimatedScore(
            score = value
        )
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
                CustomText(
                    text = char.toString(),
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    softWrap = false
                )
            }

        }

    }

}