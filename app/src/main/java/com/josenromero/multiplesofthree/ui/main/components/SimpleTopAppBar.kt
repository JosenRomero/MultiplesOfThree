package com.josenromero.multiplesofthree.ui.main.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.josenromero.multiplesofthree.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleTopAppBar(
    title: @Composable () -> Unit,
    onNavigateToAScreen: () -> Unit
) {

    TopAppBar(
        modifier = Modifier
            .padding(16.dp),
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        title = title,
        navigationIcon = {
            CustomIconButton(
                onClick = { onNavigateToAScreen() },
                icon = painterResource(id = R.drawable.close),
                contentDescription = "back icon"
            )
        }
    )

}