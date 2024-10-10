package com.josenromero.multiplesofthree.domain.player

import com.josenromero.multiplesofthree.data.player.PlayerEntity
import com.josenromero.multiplesofthree.data.player.PlayerRepository
import javax.inject.Inject

class DeletePlayer @Inject constructor(
    private val playerRepository: PlayerRepository
) {

    suspend operator fun invoke(player: PlayerEntity) {
        playerRepository.deletePlayer(player)
    }

}