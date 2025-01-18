package com.josenromero.multiplesofthree.ui.main.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.josenromero.multiplesofthree.R
import com.josenromero.multiplesofthree.data.GameMode
import com.josenromero.multiplesofthree.ui.main.viewmodels.Audios

@Composable
fun Menu(
    selected: GameMode,
    changeGameMode: (gameMode: GameMode) -> Unit,
    audioPlay: (name: String) -> Unit
) {

    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(top = 30.dp)
    ) {
        OutlinedButton(
            onClick = {
                audioPlay(Audios.AudioTap.name)
                expanded = !expanded
            }
        ) {
            Text(text = stringResource(id = R.string.home_screen_menu_title))
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            GameMode.values().forEach { gameMode ->
                DropdownMenuItem(
                    text = { Text(text = stringResource(id = gameMode.textId)) },
                    onClick = {
                        changeGameMode(gameMode)
                        audioPlay(Audios.AudioTap.name)
                        expanded = false
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "star icon",
                            modifier = Modifier.size(16.dp),
                            tint = if (selected == gameMode) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onPrimary
                        )
                    }
                )
            }
        }

    }

}