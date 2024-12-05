package com.josenromero.multiplesofthree.data.preferences

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.josenromero.multiplesofthree.utils.Constants

@Entity(tableName = Constants.PREFERENCES_TABLE)
data class PreferencesEntity(
    @ColumnInfo val sound: Boolean = true,
    @ColumnInfo val music: Boolean = false,
    @ColumnInfo val darkMode: Boolean = true,
    @ColumnInfo val language: String = "Es",
    @ColumnInfo val firstTime: Boolean = true,
)