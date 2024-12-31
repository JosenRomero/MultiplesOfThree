package com.josenromero.multiplesofthree.ui.main.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.josenromero.multiplesofthree.R

@Composable
fun AchievementItem(
    id: Int,
    imageId: Int,
    title: String,
    text: String,
    completed: Boolean
) {

    val bg1 = MaterialTheme.colorScheme.tertiary

    val bg2: Color = when (id) {
        in 1..4 -> Color(0xFF43A047)
        in 5..7 -> Color(0xFFFBC02D)
        else -> Color(0xFFD32F2F)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Box(
            modifier = Modifier
                .background(color = bg1)
        ){
            BgGradient(
                modifier = Modifier.height(100.dp),
                bg1 = bg1,
                bg2 = bg2
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier.padding(bottom = 20.dp),
                ) {
                    Image(
                        painter = painterResource(id = imageId),
                        contentDescription = "Achievement $text",
                        modifier = Modifier
                            .size(100.dp)
                    )
                }
                CustomText(
                    text = title,
                    modifier = Modifier
                        .padding(bottom = 20.dp),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
                CustomText(
                    text = text,
                    modifier = Modifier.padding(bottom = 20.dp)
                )
                if (completed) {
                    Icon(
                        imageVector = Icons.Filled.Lock,
                        contentDescription = "Achievement Lock Icon",
                        modifier = Modifier
                            .size(44.dp),
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
    
}

@Preview
@Composable
fun AchievementItemPreview() {
    AchievementItem(
        id = 1,
        imageId = R.drawable.play,
        title = stringResource(id = R.string.home_screen_menu_easy_mode),
        text = stringResource(id = R.string.achievements_screen_achievements_list_1),
        completed = true
    )
}