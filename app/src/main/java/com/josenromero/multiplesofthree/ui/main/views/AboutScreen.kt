package com.josenromero.multiplesofthree.ui.main.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.josenromero.multiplesofthree.R
import com.josenromero.multiplesofthree.ui.main.components.CustomTitle
import com.josenromero.multiplesofthree.ui.main.components.SimpleTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(
    onNavigateToBack: () -> Unit
) {

    Scaffold(
        topBar = {
            SimpleTopAppBar(
                title = {
                    CustomTitle(text = "About")
                },
                onNavigateToAScreen = { onNavigateToBack() }
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            /*
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo icon"
            )*/
            Text(
                text = "Multiples of three",
                modifier = Modifier.padding(vertical = 30.dp),
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Designed & Built by José Romero",
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 16.sp
            )
        }
    }

}