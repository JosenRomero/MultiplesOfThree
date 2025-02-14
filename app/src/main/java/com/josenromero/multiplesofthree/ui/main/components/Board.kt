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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.text.font.FontWeight
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
    isCleanBoard: Boolean,
    isPreCleanBoard: Boolean,
    onClick: (position: Pair<Int, Int>, coordinates: Offset) -> Unit,
    audioPlay: (name: String) -> Unit
) {

    LazyColumn(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(5.dp)
    ) {
        itemsIndexed(items = board) { i, rowItems ->
            LazyRow {
                itemsIndexed(rowItems) { j, item ->
                    TableCell(
                        item = item,
                        position = Pair(i, j),
                        isCleanBoard = isCleanBoard,
                        isPreCleanBoard = isPreCleanBoard,
                        onClick = onClick,
                        audioPlay = audioPlay
                    )
                }
            }
        }
    }

}

@Composable
fun TableCell(
    item: Int,
    position: Pair<Int, Int>,
    isCleanBoard: Boolean,
    isPreCleanBoard: Boolean,
    onClick: (position: Pair<Int, Int>, coordinates: Offset) -> Unit,
    audioPlay: (name: String) -> Unit
) {

    var coordinates by remember { mutableStateOf(Offset.Zero) }
    val emptyCell = item == Constants.DEFAULT_VALUE
    var isAnimated by remember { mutableStateOf(false) }
    val rotationAngle by animateFloatAsState(
        targetValue = if (isAnimated) 100F else 0F,
        animationSpec = tween(500),
        label = "rotate animation"
    )
    val alphaDuration = if (isCleanBoard) 1000 else 500
    val alpha by animateFloatAsState(
        targetValue = if (isAnimated || isCleanBoard) 0f else 1f,
        animationSpec = tween(alphaDuration),
        label = "alpha animation"
    )

    Box(
        modifier = Modifier
            .width(Constants.CELL_SIZE)
            .height(Constants.CELL_SIZE)
            .background(
                color = MaterialTheme.colorScheme.secondary,
                shape = RoundedCornerShape(25.dp)
            )
            .border(
                width = 5.dp,
                color = MaterialTheme.colorScheme.background
            )
            .onGloballyPositioned { layoutCoordinates ->
                val pos = layoutCoordinates.positionInRoot()
                val middle = layoutCoordinates.size.width / 2
                coordinates = Offset(x = pos.x + middle, y = pos.y - middle)
            },
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
                        .alpha(alpha)
                        .clickable {
                            CoroutineScope(Dispatchers.Default).launch {
                                if (!isAnimated) {
                                    audioPlay(Audios.AudioTap.name)
                                    isAnimated = true
                                    delay(700)
                                    onClick(position, coordinates)
                                    isAnimated = false
                                }
                            }
                        },
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CustomText(
                        text = item.toString(),
                        modifier = Modifier
                            .rotate(rotationAngle)
                            .alpha(alpha),
                        color = if (!isPreCleanBoard) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.error,
                        fontSize = 33.sp,
                        fontWeight = FontWeight.W900
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
            isCleanBoard = false,
            isPreCleanBoard = false,
            onClick = { _, _ -> },
            audioPlay = {}
        )
    }
}