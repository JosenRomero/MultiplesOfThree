package com.josenromero.multiplesofthree.data.preferences

import javax.inject.Inject

class PreferencesRepository @Inject constructor(
    private val preferencesDao: PreferencesDao
) {

    fun getPreferences(): PreferencesEntity? {
        return preferencesDao.getPreferences()
    }

    fun addPreferences(preferences: PreferencesEntity) {
        preferencesDao.addPreferences(preferences)
    }

    fun deletePreferences(preferences: PreferencesEntity) {
        preferencesDao.deletePreferences(preferences)
    }

    fun updatePreferences(preferences: PreferencesEntity) {
        preferencesDao.updatePreferences(preferences)
    }

}