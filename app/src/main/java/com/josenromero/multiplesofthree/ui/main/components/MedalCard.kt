package com.josenromero.multiplesofthree.ui.main.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.josenromero.multiplesofthree.data.Achievement
import com.josenromero.multiplesofthree.utils.Constants
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MedalCard(
    medals: List<String>
) {

    val visible = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        launch {
            delay(1000)
            visible.value = true
            delay(5000)
            visible.value = false
        }
    }

    AnimatedVisibility(
        visible = visible.value,
        enter = slideInVertically(
            initialOffsetY = { it },
            animationSpec = tween(1000)
        ) + expandVertically(
            expandFrom = Alignment.Bottom
        ) + fadeIn(
            initialAlpha = 0.3f
        ),
        exit = slideOutVertically(
            targetOffsetY = { -it },
            animationSpec = tween(1000)
        ) + shrinkVertically() + fadeOut()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .padding(bottom = 30.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.tertiary
                )
            ) {
                CustomText(
                    text = "New Achievements",
                    modifier = Modifier.padding(16.dp),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            LazyRow {
                items(medals) { item ->
                    val id = item.toInt()
                    val achievement: Achievement? = Constants.achievementsList.firstOrNull { achievement ->  achievement.id == id}
                    if (achievement != null) {
                        Image(
                            painter = painterResource(id = achievement.imageId),
                            contentDescription = "medal item",
                            modifier = Modifier.size(100.dp)
                        )
                    }
                }
            }
        }
    }

}