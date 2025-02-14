package com.josenromero.multiplesofthree.ui.main.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.josenromero.multiplesofthree.R
import com.josenromero.multiplesofthree.data.Achievement
import com.josenromero.multiplesofthree.ui.main.viewmodels.Audios
import com.josenromero.multiplesofthree.utils.Constants
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MedalCard(
    medals: List<String>,
    audioPlay: (name: String) -> Unit
) {

    var coordinates by remember { mutableStateOf(Offset.Zero) }
    val visible = remember { mutableStateOf(false) }
    val isConfetti = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        launch {
            delay(1000)
            visible.value = true
            delay(1000) // waiting for the medalCard animation
            isConfetti.value = true
            audioPlay(Audios.AudioNewAchievements.name)
            delay(4000)
            isConfetti.value = false
            visible.value = false
        }
    }

    AnimatedVisibility(
        visible = visible.value,
        enter = slideInVertically(
            initialOffsetY = { fullHeight ->  fullHeight },
            animationSpec = tween(1000)
        ),
        exit = slideOutVertically(
            targetOffsetY = { fullHeight ->  fullHeight },
            animationSpec = tween(1000)
        )
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
                    containerColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                CustomText(
                    text = stringResource(id = R.string.playScreen_text_new_achievements_unlocked),
                    modifier = Modifier.padding(16.dp),
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            LazyRow(
                modifier = Modifier.onGloballyPositioned { layoutCoordinates ->
                    val pos = layoutCoordinates.positionInRoot()
                    val middleX = layoutCoordinates.size.width / 2
                    val middleY = layoutCoordinates.size.height / 2
                    coordinates = Offset(x = pos.x + middleX, y = pos.y - middleY)
                }
            ) {
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
    if (isConfetti.value) {
        Confetti(initialPosition = coordinates)
    }

}