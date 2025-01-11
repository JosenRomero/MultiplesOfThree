package com.josenromero.multiplesofthree.ui.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.josenromero.multiplesofthree.R

@Composable
fun AchievementStatistics(
    totalAchievements: Int,
    currentAchievements: Int
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 40.dp, horizontal = 8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.tertiary)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.achievements),
                    contentDescription = "Achievement icon",
                    modifier = Modifier
                        .size(50.dp)
                        .padding(end = 10.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                CounterAnimation(
                    number = currentAchievements,
                    animLabel = "current Achievements animation"
                )
                CustomText(
                    text = " / $totalAchievements",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            CustomProgressIndicator(number = currentAchievements)
        }
    }

}