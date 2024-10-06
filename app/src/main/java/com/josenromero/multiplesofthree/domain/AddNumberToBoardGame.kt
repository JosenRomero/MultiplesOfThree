package com.josenromero.multiplesofthree.domain

import com.josenromero.multiplesofthree.utils.Constants
import kotlin.random.Random

class AddNumberToBoardGame {

    fun addNumber(boardGame: MutableList<MutableList<Int>>, initialNumber: Boolean = false): MutableList<MutableList<Int>>  {

        val position = getAvailablePosition(boardGame)
        val number = getNumber(initialNumber)

        if (position != null) {
            boardGame[position.first][position.second] = number
        }

        return boardGame

    }

    private fun getAvailablePosition(boardGame: MutableList<MutableList<Int>>): Pair<Int, Int>? {
        val lineSize = boardGame.size
        val availableCells = mutableListOf<Pair<Int, Int>>()

        for (rowIndex in 0 until lineSize) {
            for (columIndex in 0 until lineSize) {
                val cell = boardGame[rowIndex][columIndex]
                if (cell == Constants.DEFAULT_VALUE) {
                    availableCells.add(Pair(rowIndex, columIndex))
                }
            }
        }

        if (availableCells.isEmpty()) {
            return null
        }

        val position = Random.nextInt(availableCells.size)
        return availableCells[position]

    }

    private fun getNumber(initialNumber: Boolean): Int {
        return if (initialNumber) Constants.FIRST_NUMBER else Random.nextInt(Constants.START_NUMBER, Constants.END_NUMBER)
    }

}