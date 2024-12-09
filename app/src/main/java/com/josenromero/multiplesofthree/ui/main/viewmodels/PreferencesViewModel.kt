package com.josenromero.multiplesofthree.ui.main.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josenromero.multiplesofthree.data.preferences.PreferencesEntity
import com.josenromero.multiplesofthree.domain.preferences.AddPreferences
import com.josenromero.multiplesofthree.domain.preferences.GetPreferences
import com.josenromero.multiplesofthree.domain.preferences.UpdatePreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PreferencesViewModel @Inject constructor(
    private val getPreferences: GetPreferences,
    private val addPreferences: AddPreferences,
    private val updatePreferences: UpdatePreferences
): ViewModel() {

    private val _preferences: MutableStateFlow<PreferencesEntity> = MutableStateFlow(PreferencesEntity())
    val preferences = _preferences.asStateFlow()

    private val _preferencesLoading: MutableState<Boolean> = mutableStateOf(true)
    val preferencesLoading: State<Boolean> get() = _preferencesLoading

    init {
        checkPreferences()
    }

    private fun checkPreferences() {
        viewModelScope.launch(Dispatchers.IO) {
            val currentPreferences = getPreferences()

            withContext(Dispatchers.Main) {
                if (currentPreferences != null) {
                    _preferences.value = currentPreferences
                    _preferencesLoading.value = false
                } else {
                    initPreferences()
                }
            }
        }
    }

    private fun initPreferences() {
        viewModelScope.launch(Dispatchers.IO) {
            addPreferences(PreferencesEntity())

            withContext(Dispatchers.Main) {
                checkPreferences()
            }

        }
    }

    fun update(sound: Boolean? = null, music: Boolean? = null, darkMode: Boolean? = null, firstTime: Boolean? = null) {
        viewModelScope.launch(Dispatchers.IO) {
            updatePreferences(
                PreferencesEntity(
                    uid = _preferences.value.uid,
                    sound = sound ?: _preferences.value.sound,
                    music = music ?: _preferences.value.music,
                    darkMode = darkMode ?: _preferences.value.darkMode,
                    firstTime = firstTime ?: _preferences.value.firstTime
                )
            )
            checkPreferences()
        }
    }

}