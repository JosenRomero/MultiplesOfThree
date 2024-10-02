package com.josenromero.multiplesofthree.ui.main.views

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.josenromero.multiplesofthree.ui.theme.MultiplesOfThreeTheme

@Composable
fun HomeScreen() {
    Text(text = "HomeScreen")
}

@Preview(uiMode = UI_MODE_NIGHT_NO, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    MultiplesOfThreeTheme {
        HomeScreen()
    }
}