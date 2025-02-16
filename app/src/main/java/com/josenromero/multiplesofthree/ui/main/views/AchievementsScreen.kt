package com.josenromero.multiplesofthree.ui.main.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.josenromero.multiplesofthree.R
import com.josenromero.multiplesofthree.ui.main.components.AchievementItem
import com.josenromero.multiplesofthree.ui.main.components.AchievementStatistics
import com.josenromero.multiplesofthree.ui.main.components.CustomTitle
import com.josenromero.multiplesofthree.ui.main.components.SimpleTopAppBar
import com.josenromero.multiplesofthree.utils.Constants

@Composable
fun AchievementsScreen(
    achievements: List<String>,
    onNavigateToBack: () -> Unit
) {

    Scaffold(
        topBar = {
            SimpleTopAppBar(
                title = {
                        CustomTitle(text = stringResource(id = R.string.achievements_screen_title))
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
            LazyVerticalGrid(
                columns = GridCells.Fixed(1),
                contentPadding = PaddingValues(16.dp)
            ) {
                item {
                    AchievementStatistics(
                        totalAchievements = Constants.achievementsList.size,
                        currentAchievements = achievements.size
                    )
                }
                items(Constants.achievementsList) {achievement ->
                    AchievementItem(
                        id = achievement.id,
                        imageId = achievement.imageId,
                        title = stringResource(id = achievement.titleId),
                        text = stringResource(id = achievement.textId),
                        completed = achievements.contains(achievement.id.toString())
                    )
                }
            }
        }
    }

}