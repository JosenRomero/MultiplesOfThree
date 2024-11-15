package com.josenromero.multiplesofthree.ui.main.viewmodels

import androidx.lifecycle.ViewModel
import com.josenromero.multiplesofthree.domain.Audio
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AudioViewModel @Inject constructor(
    private val audio: Audio
): ViewModel() {

    fun play(resId: Int) {
        audio.play(resId)
    }

}