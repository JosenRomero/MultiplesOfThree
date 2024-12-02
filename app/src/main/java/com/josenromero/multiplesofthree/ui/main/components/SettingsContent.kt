package com.josenromero.multiplesofthree.ui.main.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.josenromero.multiplesofthree.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsContent(
    onDismiss: () -> Unit,
    closeBtn: () -> Unit
) {

    val modalBottomSheetState = rememberModalBottomSheetState()
    val isSound = remember { mutableStateOf(true) }
    val isMusic = remember { mutableStateOf(true) }
    val isDark = remember { mutableStateOf(false) }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
        containerColor = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 30.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconButton(
                    onClick = { closeBtn() },
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "back icon",
                        modifier = Modifier.size(44.dp),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
                CustomTitle(
                    text = "Settings",
                    modifier = Modifier.weight(1f)
                )
            }
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
                onClick = { /*TODO*/ }
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