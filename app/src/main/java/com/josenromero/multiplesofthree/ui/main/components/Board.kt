package com.josenromero.multiplesofthree.ui.main.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.josenromero.multiplesofthree.ui.theme.MultiplesOfThreeTheme
import com.josenromero.multiplesofthree.utils.Constants

@Composable
fun Board(
    board: List<List<Int>>
) {

    LazyColumn(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.secondary),
        contentPadding = PaddingValues(5.dp)
    ) {
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

    val emptyCell = item == Constants.DEFAULT_VALUE

    Box(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
            .background(
                color = if (emptyCell) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.onSecondary
            )
            .border(5.dp, MaterialTheme.colorScheme.secondary)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = if (emptyCell) "" else item.toString(),
            color = MaterialTheme.colorScheme.onTertiary,
            fontSize = 16.sp
        )
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