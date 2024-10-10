package com.josenromero.multiplesofthree.data.player

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [PlayerEntity::class],
    version = 1,
    exportSchema = false
)
abstract class PlayerDataBase: RoomDatabase() {

    abstract fun playerDao(): PlayerDao

}