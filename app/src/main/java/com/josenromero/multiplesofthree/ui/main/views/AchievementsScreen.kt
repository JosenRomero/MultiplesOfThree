package com.josenromero.multiplesofthree.ui.main.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.josenromero.multiplesofthree.ui.main.components.CustomTitle
import com.josenromero.multiplesofthree.ui.main.components.SimpleTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AchievementsScreen(
    onNavigateToBack: () -> Unit
) {

    Scaffold(
        topBar = {
            SimpleTopAppBar(
                title = {
                        CustomTitle(text = "Achievements")
                },
                onNavigateToAScreen = { onNavigateToBack() }
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {

        }
    }

}