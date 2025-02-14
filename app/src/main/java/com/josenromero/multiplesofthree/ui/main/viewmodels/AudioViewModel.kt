package com.josenromero.multiplesofthree.ui.main.viewmodels

import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import com.josenromero.multiplesofthree.R
import com.josenromero.multiplesofthree.domain.Audio
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AudioViewModel @Inject constructor(
    audio: Audio
): ViewModel() {

    private var audioBackground: MediaPlayer? = null
    private var audioTap: MediaPlayer? = null
    private var audioGameOver: MediaPlayer? = null
    private var audioNewAchievements: MediaPlayer? = null

    init {
        audioBackground = audio.loadAudio(R.raw.piano_loops)
        audioTap = audio.loadAudio(R.raw.tap)
        audioGameOver = audio.loadAudio(R.raw.game_over)
        audioNewAchievements = audio.loadAudio(R.raw.new_achievements)
    }

    fun play(audio: String, isSound: Boolean) {
        if (isSound) {
            when (audio) {
                Audios.AudioTap.name -> audioTap?.start()
                Audios.AudioGameOver.name -> audioGameOver?.start()
                Audios.AudioNewAchievements.name -> audioNewAchievements?.start()
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

    override fun onCleared() {
        super.onCleared()
        audioBackground?.release()
        audioBackground = null
    }

}