package com.josenromero.multiplesofthree.data.player

import javax.inject.Inject

class PlayerRepository @Inject constructor(
    private val playerDao: PlayerDao
) {

    fun getPlayer(): PlayerEntity? {
        return playerDao.getPlayer()
    }

    fun addPlayer(player: PlayerEntity) {
        playerDao.addPlayer(player)
    }

    fun deletePlayer(player: PlayerEntity) {
        playerDao.deletePlayer(player)
    }

    fun updatePlayer(player: PlayerEntity) {
        playerDao.updatePlayer(player)
    }

}