package com.josenromero.multiplesofthree.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.josenromero.multiplesofthree.ui.main.navigation.AppNavigation
import com.josenromero.multiplesofthree.ui.theme.MultiplesOfThreeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MultiplesOfThreeTheme {
                AppNavigation()
            }
        }
    }
}

