package com.josenromero.multiplesofthree.ui.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.josenromero.multiplesofthree.ui.main.navigation.AppNavigation
import com.josenromero.multiplesofthree.ui.main.viewmodels.PreferencesViewModel
import com.josenromero.multiplesofthree.utils.Constants
import com.unity3d.ads.IUnityAdsInitializationListener
import com.unity3d.ads.UnityAds
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), IUnityAdsInitializationListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val preferencesViewModel: PreferencesViewModel by viewModels()

        installSplashScreen().apply {
            preferencesViewModel.preferencesLoading.value
        }

        // Initialize the Unity Ads
        UnityAds.initialize(applicationContext, Constants.UNITY_GAME_ID, Constants.TEST_MODE, this)

        window.addFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        setContent {
            AppNavigation()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        window.clearFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }

    override fun onInitializationComplete() {
        println("onInitializationComplete")
    }

    override fun onInitializationFailed(
        error: UnityAds.UnityAdsInitializationError?,
        message: String?
    ) {
        println("Unity Ads initialization failed")
    }
}

