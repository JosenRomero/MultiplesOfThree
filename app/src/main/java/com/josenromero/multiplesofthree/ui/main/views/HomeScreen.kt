package com.josenromero.multiplesofthree.ui.main.views

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.josenromero.multiplesofthree.R
import com.josenromero.multiplesofthree.ui.main.components.CustomBottomAppBar
import com.josenromero.multiplesofthree.ui.main.components.CustomIconButton
import com.josenromero.multiplesofthree.ui.main.navigation.AppScreens
import com.josenromero.multiplesofthree.ui.theme.MultiplesOfThreeTheme

@Composable
fun HomeScreen(
    onNavigateToAScreen: (route: String) -> Unit
) {

    Scaffold(
        bottomBar = {
            CustomBottomAppBar {
                CustomIconButton(
                    onClick = { onNavigateToAScreen(AppScreens.HowToPlayScreen.route) },
                    icon = painterResource(id = R.drawable.mark),
                    contentDescription = "How To Play icon"
                )
                CustomIconButton(
                    onClick = { onNavigateToAScreen(AppScreens.SettingsScreen.route) },
                    icon = painterResource(id = R.drawable.settings),
                    contentDescription = "setting icon"
                )
                CustomIconButton(
                    onClick = { onNavigateToAScreen(AppScreens.AchievementsScreen.route) },
                    icon = painterResource(id = R.drawable.achievements),
                    contentDescription = "achievements icon"
                )
                CustomIconButton(
                    onClick = { onNavigateToAScreen(AppScreens.AboutScreen.route) },
                    icon = painterResource(id = R.drawable.about),
                    contentDescription = "about icon"
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Multiples of three",
                modifier = Modifier.padding(bottom = 40.dp),
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Button(
                onClick = { onNavigateToAScreen(AppScreens.PlayScreen.route) },
                modifier = Modifier.padding(8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.play),
                    contentDescription = "play icon",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    text = "Play",
                    modifier = Modifier.padding(start = 8.dp),
                    fontSize = 16.sp
                )
            }
        }
    }

}

@Preview(uiMode = UI_MODE_NIGHT_NO, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    MultiplesOfThreeTheme {
        HomeScreen(
            onNavigateToAScreen = {}
        )
    }
}