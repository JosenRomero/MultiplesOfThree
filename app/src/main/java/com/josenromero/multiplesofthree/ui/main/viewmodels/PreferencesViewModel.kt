package com.josenromero.multiplesofthree.ui.main.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josenromero.multiplesofthree.data.preferences.PreferencesEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PreferencesViewModel @Inject constructor(): ViewModel() {

    private val _preferences: MutableStateFlow<PreferencesEntity> = MutableStateFlow(PreferencesEntity())
    val preferences = _preferences.asStateFlow()

    private val _preferencesLoading: MutableState<Boolean> = mutableStateOf(true)
    val preferencesLoading: State<Boolean> get() = _preferencesLoading

    init {
        viewModelScope.launch(Dispatchers.IO) {
            delay(10000)
            _preferencesLoading.value = false
        }
    }

}