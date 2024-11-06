package com.josenromero.multiplesofthree.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.josenromero.multiplesofthree.ui.main.navigation.AppNavigation
import com.josenromero.multiplesofthree.ui.theme.MultiplesOfThreeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        setContent {
            MultiplesOfThreeTheme {
                AppNavigation()
            }
        }
    }
}

