package com.josenromero.multiplesofthree.ui.main.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.josenromero.multiplesofthree.ui.main.viewmodels.Audios
import com.josenromero.multiplesofthree.ui.theme.MultiplesOfThreeTheme
import com.josenromero.multiplesofthree.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun Board(
    board: List<List<Int>>,
    onClick: (position: Pair<Int, Int>) -> Unit,
    audioPlay: (name: String) -> Unit
) {

    AnimatedFadeIn {
        LazyColumn(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.background),
            contentPadding = PaddingValues(5.dp)
        ) {
            itemsIndexed(items = board) {i, rowItems ->
                LazyRow {
                    itemsIndexed(rowItems) { j, item ->
                        TableCell(
                            item = item,
                            position = Pair(i, j),
                            onClick = onClick,
                            audioPlay = audioPlay
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TableCell(
    item: Int,
    position: Pair<Int, Int>,
    onClick: (position: Pair<Int, Int>) -> Unit,
    audioPlay: (name: String) -> Unit
) {

    val emptyCell = item == Constants.DEFAULT_VALUE
    var isAnimated by remember { mutableStateOf(false) }
    val rotationAngle by animateFloatAsState(
        targetValue = if (isAnimated) 100F else 0F,
        animationSpec = tween(500),
        label = "rotate animation"
    )
    val alpha by animateFloatAsState(
        targetValue = if (isAnimated) 0f else 1f,
        animationSpec = tween(500),
        label = "alpha animation"
    )

    Box(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
            .background(
                color = MaterialTheme.colorScheme.secondary,
                shape = RoundedCornerShape(25.dp)
            )
            .border(
                width = 5.dp,
                color = MaterialTheme.colorScheme.background
            ),
        contentAlignment = Alignment.Center
    ) {
        if (!emptyCell) {
            AnimatedFadeAndExpandVertically {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            color = MaterialTheme.colorScheme.tertiary,
                            shape = RoundedCornerShape(25.dp)
                        )
                        .rotate(rotationAngle)
                        .alpha(alpha)
                        .clickable {
                            CoroutineScope(Dispatchers.Default).launch {
                                audioPlay(Audios.AudioTap.name)
                                isAnimated = true
                                delay(700)
                                onClick(position)
                                isAnimated = false
                            }
                        },
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = item.toString(),
                        color = MaterialTheme.colorScheme.onSecondary,
                        fontSize = 33.sp
                    )
                }
            }
        }
    }

}

@Preview(uiMode = UI_MODE_NIGHT_NO, showSystemUi = true)
@Composable
fun BoardPreview() {
    MultiplesOfThreeTheme {
        Board(
            board = listOf(listOf(-1, -1, 3), listOf(-1, -1, -1), listOf(-1, -1, -1)),
            onClick = {},
            audioPlay = {}
        )
    }
}