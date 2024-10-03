package com.josenromero.multiplesofthree.ui.main.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josenromero.multiplesofthree.ui.theme.MultiplesOfThreeTheme
import com.josenromero.multiplesofthree.utils.Constants

@Composable
fun Board(
    board: List<List<Int>>
) {

    LazyColumn() {
        items(items = board) {row ->

            LazyRow {
                items(row) {item ->
                    TableCell(item)
                }
            }

        }
    }
}

@Composable
fun TableCell(item: Int) {

    val content = if (item == Constants.DEFAULT_VALUE) "" else item.toString()

    Box(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
            .background(color = Color.Cyan)
            .border(3.dp, Color.Gray)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(content)
    }

}

@Preview(uiMode = UI_MODE_NIGHT_NO, showSystemUi = true)
@Composable
fun BoardPreview() {
    MultiplesOfThreeTheme {
        Board(
            board = listOf(listOf(-1, -1, 3), listOf(-1, -1, -1), listOf(-1, -1, -1))
        )
    }
}