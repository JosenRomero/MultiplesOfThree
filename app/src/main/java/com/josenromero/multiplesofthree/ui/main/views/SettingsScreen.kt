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
import com.josenromero.multiplesofthree.ui.main.viewmodels.PreferencesViewModel

@Composable
fun SettingsScreen(
    preferencesVM: PreferencesViewModel,
    backgroundMusic: (value: Boolean) -> Unit,
    onNavigateToBack: () -> Unit,
    onNavigateToAScreen: (route: String) -> Unit
) {

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
                    onClick = {
                        val currentValue = preferencesVM.preferences.value.music
                        backgroundMusic(!currentValue)
                        preferencesVM.update(music = !currentValue)
                    }
                ) {
                    CustomSwitch(
                        checked = preferencesVM.preferences.value.music,
                        onCheckedChange = { value ->
                            backgroundMusic(value)
                            preferencesVM.update(music = value)
                        },
                        activeIcon = R.drawable.sound_up,
                        inactiveIcon = R.drawable.sound_off,
                        contentDescription = "music"
                    )
                }
                SettingItem(
                    text = "Sound",
                    onClick = {
                        val currentValue = preferencesVM.preferences.value.sound
                        preferencesVM.update(sound = !currentValue)
                    }
                ) {
                    CustomSwitch(
                        checked = preferencesVM.preferences.value.sound,
                        onCheckedChange = { value ->
                            preferencesVM.update(sound = value)
                        },
                        activeIcon = R.drawable.sound_up,
                        inactiveIcon = R.drawable.sound_off,
                        contentDescription = "sound"
                    )
                }
                SettingItem(
                    text = "Dark Mode",
                    onClick = {
                        val currentValue = preferencesVM.preferences.value.darkMode
                        preferencesVM.update(darkMode = !currentValue)
                    }
                ) {
                    CustomSwitch(
                        checked = preferencesVM.preferences.value.darkMode,
                        onCheckedChange = { value ->
                            preferencesVM.update(darkMode = value)
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