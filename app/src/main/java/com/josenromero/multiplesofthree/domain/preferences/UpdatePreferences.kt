package com.josenromero.multiplesofthree.domain.preferences

import com.josenromero.multiplesofthree.data.preferences.PreferencesEntity
import com.josenromero.multiplesofthree.data.preferences.PreferencesRepository
import javax.inject.Inject

class UpdatePreferences @Inject constructor(
    private val preferencesRepository: PreferencesRepository
) {

    suspend operator fun invoke(preferences: PreferencesEntity) {
        preferencesRepository.updatePreferences(preferences)
    }

}