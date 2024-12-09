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

    private var audioBackground: MediaPlayer? = null
    private var audioTap: MediaPlayer? = null

    init {
        audioBackground = audio.loadAudio(R.raw.piano_loops)
        audioTap = audio.loadAudio(R.raw.tap)
    }

    fun play(audio: String, isSound: Boolean) {
        if (isSound) {
            when (audio) {
                Audios.AudioTap.name -> audioTap?.start()
            }
        }
    }

    fun backgroundMusicPlay(isMusic: Boolean) {
        if (isMusic && audioBackground?.isPlaying == false) {
            audioBackground?.start()
            audioBackground?.isLooping = true
        }
    }

    fun backgroundMusicStop() {
        if (audioBackground?.isPlaying == true) {
           audioBackground?.pause()
        }
    }

}