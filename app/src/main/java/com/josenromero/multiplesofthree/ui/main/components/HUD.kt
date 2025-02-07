package com.josenromero.multiplesofthree.ui.main.components

import android.annotation.SuppressLint
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.josenromero.multiplesofthree.R
import com.josenromero.multiplesofthree.data.GameMode
import com.josenromero.multiplesofthree.ui.theme.MultiplesOfThreeTheme
import com.josenromero.multiplesofthree.utils.getNumbersOfHearts

@Composable
fun HUD(
    gameMode: GameMode,
    bestScore: Int,
    hearts: Int
) {

    val numberOfHearts = getNumbersOfHearts(gameMode)

    AnimatedFadeAndExpandHorizontally {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CustomText(
                text = "${stringResource(id = R.string.text_best_score)} $bestScore",
                modifier = Modifier
                    .weight(1f)
                    .background(
                        color = MaterialTheme.colorScheme.secondary,
                        shape = RoundedCornerShape(50.dp)
                    )
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start
            )
            LazyRow(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.secondary,
                        shape = RoundedCornerShape(50.dp)
                    )
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                horizontalArrangement = Arrangement.End,
                reverseLayout = true
            ) {
                items(numberOfHearts) {heartId ->
                    Heart(hearts, heartId)
                }
            }
        }
    }

}

@SuppressLint("UseOfNonLambdaOffsetOverload")
@Composable
fun Heart(
    hearts: Int,
    heartId: Int
) {

    var isAnimated by remember { mutableStateOf(false) }

    val alpha by animateFloatAsState(
        targetValue = if (isAnimated) 0f else 1f,
        animationSpec = tween(1000),
        label = "alpha animation for heart: $heartId"
    )

    val offsetY = animateDpAsState(
        targetValue = if (isAnimated) 20.dp else 0.dp,
        animationSpec = tween(1000),
        label = "offsetY animation for heart: $heartId"
    )

    val removeHeart: Boolean = (heartId+1) > hearts // the first heartId is 0

    if (!isAnimated && removeHeart) {
        isAnimated = true
    }

    val iconId: Int = if (!removeHeart) R.drawable.heart else R.drawable.heart_broken

    Image(
        painter = painterResource(id = iconId),
        contentDescription = "heart icon",
        modifier = Modifier
            .offset(x = 0.dp, y = offsetY.value)
            .size(44.dp)
            .alpha(alpha)
    )

}

@Preview(uiMode = UI_MODE_NIGHT_NO, showSystemUi = true)
@Composable
fun HUDPreview() {
    MultiplesOfThreeTheme {
        HUD(
            gameMode = GameMode.NORMAL,
            bestScore = 100,
            hearts = 3
        )
    }
}