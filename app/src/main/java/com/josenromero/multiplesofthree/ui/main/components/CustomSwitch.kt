package com.josenromero.multiplesofthree.ui.main.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

@Composable
fun CustomSwitch(
    checked: Boolean,
    onCheckedChange: (value: Boolean) -> Unit,
    activeIcon: Int,
    inactiveIcon: Int,
    contentDescription: String
) {

    Switch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        thumbContent = {
            Icon(
                painter = painterResource(id = if (checked) activeIcon else inactiveIcon),
                contentDescription = "$contentDescription ${if (checked) "active" else "inactive"} icon",
                tint = Color.White,
                modifier = Modifier.size(SwitchDefaults.IconSize)
            )
        },
        colors = SwitchDefaults.colors(
            checkedThumbColor = MaterialTheme.colorScheme.primary,
            checkedTrackColor = MaterialTheme.colorScheme.background,
            checkedBorderColor = MaterialTheme.colorScheme.secondary,
            uncheckedThumbColor = MaterialTheme.colorScheme.secondary,
            uncheckedTrackColor = MaterialTheme.colorScheme.background,
            uncheckedBorderColor = MaterialTheme.colorScheme.secondary
        )
    )

}