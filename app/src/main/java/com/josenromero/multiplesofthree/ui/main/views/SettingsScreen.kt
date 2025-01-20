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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.josenromero.multiplesofthree.R
import com.josenromero.multiplesofthree.ui.main.components.CustomSwitch
import com.josenromero.multiplesofthree.ui.main.components.CustomTitle
import com.josenromero.multiplesofthree.ui.main.components.SettingItem
import com.josenromero.multiplesofthree.ui.main.components.SimpleTopAppBar
import com.josenromero.multiplesofthree.ui.main.navigation.AppScreens
import com.josenromero.multiplesofthree.ui.main.viewmodels.Audios
import com.josenromero.multiplesofthree.ui.main.viewmodels.PreferencesViewModel

@Composable
fun SettingsScreen(
    preferencesVM: PreferencesViewModel,
    audioPlay: (name: String) -> Unit ,
    backgroundMusic: (value: Boolean) -> Unit,
    onNavigateToBack: () -> Unit,
    onNavigateToAScreen: (route: String) -> Unit
) {

    Scaffold(
        topBar = {
            SimpleTopAppBar(
                title = {
                    CustomTitle(text = stringResource(id = R.string.settings_screen_title))
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
                    text = stringResource(id = R.string.settings_screen_text_music),
                    onClick = {
                        audioPlay(Audios.AudioTap.name)
                        val currentValue = preferencesVM.preferences.value.music
                        backgroundMusic(!currentValue)
                        preferencesVM.update(music = !currentValue)
                    }
                ) {
                    CustomSwitch(
                        checked = preferencesVM.preferences.value.music,
                        onCheckedChange = { value ->
                            audioPlay(Audios.AudioTap.name)
                            backgroundMusic(value)
                            preferencesVM.update(music = value)
                        },
                        activeIcon = R.drawable.sound_up,
                        inactiveIcon = R.drawable.sound_off,
                        contentDescription = "music"
                    )
                }
                SettingItem(
                    text = stringResource(id = R.string.settings_screen_text_sound),
                    onClick = {
                        audioPlay(Audios.AudioTap.name)
                        val currentValue = preferencesVM.preferences.value.sound
                        preferencesVM.update(sound = !currentValue)
                    }
                ) {
                    CustomSwitch(
                        checked = preferencesVM.preferences.value.sound,
                        onCheckedChange = { value ->
                            audioPlay(Audios.AudioTap.name)
                            preferencesVM.update(sound = value)
                        },
                        activeIcon = R.drawable.sound_up,
                        inactiveIcon = R.drawable.sound_off,
                        contentDescription = "sound"
                    )
                }
                SettingItem(
                    text = stringResource(id = R.string.settings_screen_text_dark_mode),
                    onClick = {
                        audioPlay(Audios.AudioTap.name)
                        val currentValue = preferencesVM.preferences.value.darkMode
                        preferencesVM.update(darkMode = !currentValue)
                    }
                ) {
                    CustomSwitch(
                        checked = preferencesVM.preferences.value.darkMode,
                        onCheckedChange = { value ->
                            audioPlay(Audios.AudioTap.name)
                            preferencesVM.update(darkMode = value)
                        },
                        activeIcon = R.drawable.sun,
                        inactiveIcon = R.drawable.moon,
                        contentDescription = "dark mode"
                    )
                }
                SettingItem(
                    text = stringResource(id = R.string.settings_screen_text_language),
                    onClick = { onNavigateToAScreen(AppScreens.LanguageScreen.route) }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_forward),
                        contentDescription = "arrow forward icon",
                        modifier = Modifier.size(32.dp),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
    }

}