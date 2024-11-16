package com.josenromero.multiplesofthree.ui.main.viewmodels

import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import com.josenromero.multiplesofthree.R
import com.josenromero.multiplesofthree.domain.Audio
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AudioViewModel @Inject constructor(
    private val audio: Audio
): ViewModel() {

    private var audioTap: MediaPlayer? = null

    init {
        audioTap = audio.loadAudio(R.raw.tap)
    }

    fun play(audio: String) {
        when (audio) {
            Audios.AudioTap.name -> audioTap?.start()
        }
    }

}