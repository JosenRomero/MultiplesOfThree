package com.josenromero.multiplesofthree.ui.main.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.josenromero.multiplesofthree.R
import com.josenromero.multiplesofthree.ui.main.components.CustomSwitch
import com.josenromero.multiplesofthree.ui.main.components.CustomTitle
import com.josenromero.multiplesofthree.ui.main.components.SettingItem
import com.josenromero.multiplesofthree.ui.main.components.SimpleTopAppBar
import com.josenromero.multiplesofthree.ui.main.navigation.AppScreens

@Composable
fun SettingsScreen(
    onNavigateToBack: () -> Unit,
    onNavigateToAScreen: (route: String) -> Unit
) {

    val isMusic = remember { mutableStateOf(true) }
    val isSound = remember { mutableStateOf(true) }
    val isDark = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            SimpleTopAppBar(
                title = {
                    CustomTitle(text = "Settings")
                },
                onNavigateToAScreen = { onNavigateToBack() }
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                SettingItem(
                    text = "Music",
                    onClick = { isMusic.value = !isMusic.value }
                ) {
                    CustomSwitch(
                        checked = isMusic.value,
                        onCheckedChange = { value ->
                            isMusic.value = value
                        },
                        activeIcon = R.drawable.sound_up,
                        inactiveIcon = R.drawable.sound_off,
                        contentDescription = "music"
                    )
                }
                SettingItem(
                    text = "Sound",
                    onClick = { isSound.value = !isSound.value }
                ) {
                    CustomSwitch(
                        checked = isSound.value,
                        onCheckedChange = { value ->
                            isSound.value = value
                        },
                        activeIcon = R.drawable.sound_up,
                        inactiveIcon = R.drawable.sound_off,
                        contentDescription = "sound"
                    )
                }
                SettingItem(
                    text = "Dark Mode",
                    onClick = { isDark.value = !isDark.value }
                ) {
                    CustomSwitch(
                        checked = isDark.value,
                        onCheckedChange = { value ->
                            isDark.value = value
                        },
                        activeIcon = R.drawable.sun,
                        inactiveIcon = R.drawable.moon,
                        contentDescription = "dark mode"
                    )
                }
                SettingItem(
                    text = "Language",
                    onClick = { onNavigateToAScreen(AppScreens.LanguageScreen.route) }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_forward),
                        contentDescription = "arrow forward icon",
                        modifier = Modifier.size(32.dp),
                        tint = Color.White
                    )
                }
            }
        }
    }

}