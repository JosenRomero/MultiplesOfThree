package com.josenromero.multiplesofthree.ui.main.components

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.josenromero.multiplesofthree.R
import com.unity3d.ads.IUnityAdsLoadListener
import com.unity3d.ads.IUnityAdsShowListener
import com.unity3d.ads.UnityAds
import com.unity3d.ads.UnityAdsShowOptions

@Composable
fun AdsContent(
    isShowAd: Boolean,
    closeAds: () -> Unit
) {

    val currentActivity = LocalContext.current as Activity
    val adUnitId = "Interstitial_Android"
    val adsAdLoaded = remember { mutableStateOf("") }
    val showBtnToClose = remember { mutableStateOf(false) }

    UnityAds.load(adUnitId, object : IUnityAdsLoadListener {
        override fun onUnityAdsAdLoaded(placementId: String?) {
            println("onUnityAdsAdLoaded")
            adsAdLoaded.value = placementId ?: ""
        }

        override fun onUnityAdsFailedToLoad(
            placementId: String?,
            error: UnityAds.UnityAdsLoadError?,
            message: String?
        ) {
            println("onUnityAdsFailedToLoad")
        }
    })

    val showListener = object : IUnityAdsShowListener {
        override fun onUnityAdsShowFailure(
            placementId: String?,
            error: UnityAds.UnityAdsShowError?,
            message: String?
        ) {
            println("onUnityAdsShowFailure")
            showBtnToClose.value = true
        }

        override fun onUnityAdsShowStart(placementId: String?) {
            println("onUnityAdsShowStart")
        }

        override fun onUnityAdsShowClick(placementId: String?) {
            println("onUnityAdsShowClick")
        }

        override fun onUnityAdsShowComplete(
            placementId: String?,
            state: UnityAds.UnityAdsShowCompletionState?
        ) {
            println("onUnityAdsShowComplete")
            showBtnToClose.value = true
        }

    }

    LaunchedEffect(isShowAd) {
        if (isShowAd) {
            if (adsAdLoaded.value != "") {
                UnityAds.show(currentActivity, adsAdLoaded.value, UnityAdsShowOptions(), showListener)
            } else {
                showBtnToClose.value = true
            }
        }
    }

    if (showBtnToClose.value) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedButton(
                onClick = { closeAds() }
            ) {
                CustomText(text = stringResource(id = R.string.btn_continue))
            }
        }
    }

}

