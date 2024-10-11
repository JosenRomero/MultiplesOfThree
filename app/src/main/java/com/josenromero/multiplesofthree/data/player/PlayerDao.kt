package com.josenromero.multiplesofthree.data.player

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.josenromero.multiplesofthree.utils.Constants

// DAO - Data Access Object
@Dao
interface PlayerDao {

    @Query("SELECT * FROM ${Constants.PLAYER_TABLE}")
    fun getPlayer(): PlayerEntity?

    @Insert
    fun addPlayer(player: PlayerEntity)

    @Delete
    fun deletePlayer(player: PlayerEntity)

    @Update
    fun updatePlayer(player: PlayerEntity)

}