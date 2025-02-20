package com.josenromero.multiplesofthree.ui.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.josenromero.multiplesofthree.ui.main.navigation.AppNavigation
import com.josenromero.multiplesofthree.ui.main.viewmodels.PreferencesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val preferencesViewModel: PreferencesViewModel by viewModels()

        installSplashScreen().apply {
            preferencesViewModel.preferencesLoading.value
        }

        setContent {
            AppNavigation()
        }
    }
}

