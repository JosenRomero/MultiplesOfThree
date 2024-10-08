package com.josenromero.multiplesofthree.domain

import com.josenromero.multiplesofthree.utils.Constants

class RemoveNumberToBoardGame {

    fun removeNumber(boardGame: List<List<Int>>, position: Pair<Int, Int>): List<List<Int>> {
        val newBoard: MutableList<MutableList<Int>> = boardGame.map { it.toMutableList() }.toMutableList()
        newBoard[position.first][position.second] = Constants.DEFAULT_VALUE
        return newBoard
    }

}