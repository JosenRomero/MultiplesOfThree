package com.josenromero.multiplesofthree.data.player

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.josenromero.multiplesofthree.utils.Constants

@Entity(tableName = Constants.PLAYER_TABLE)
data class PlayerEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "bestScore") val bestScore: Number,
    @ColumnInfo(name = "achievements") val achievements: ArrayList<String>
)