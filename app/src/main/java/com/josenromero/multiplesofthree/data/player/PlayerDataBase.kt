package com.josenromero.multiplesofthree.data.player

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.josenromero.multiplesofthree.utils.TypeConverter

@Database(
    entities = [PlayerEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(TypeConverter::class)
abstract class PlayerDataBase: RoomDatabase() {

    abstract fun playerDao(): PlayerDao

}