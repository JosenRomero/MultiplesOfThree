package com.josenromero.multiplesofthree.data.preferences

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.josenromero.multiplesofthree.utils.Constants

@Dao
interface PreferencesDao {

    @Query("SELECT * FROM ${Constants.PREFERENCES_TABLE}")
    fun getPreferences(): PreferencesEntity?

    @Insert
    fun addPreferences(preferences: PreferencesEntity)

    @Delete
    fun deletePreferences(preferences: PreferencesEntity)

    @Update
    fun updatePreferences(preferencesEntity: PreferencesEntity)

}