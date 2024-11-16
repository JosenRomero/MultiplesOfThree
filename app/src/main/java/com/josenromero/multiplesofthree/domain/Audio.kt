package com.josenromero.multiplesofthree.domain

import android.content.Context
import android.media.MediaPlayer
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class Audio @Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun loadAudio(resId: Int): MediaPlayer {
        return MediaPlayer.create(context, resId)
    }

}