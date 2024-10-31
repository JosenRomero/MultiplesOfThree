package com.josenromero.multiplesofthree.domain

import com.josenromero.multiplesofthree.utils.Constants
import kotlin.random.Random

class AddNumberToBoardGame {

    fun addNumber(boardGame: List<List<Int>>, initialNumber: Boolean = false): List<List<Int>>  {

        val position = getAvailablePosition(boardGame)
        val number = getNumber(initialNumber)
        val newBoard: MutableList<MutableList<Int>> = boardGame.map { it.toMutableList() }.toMutableList()

        if (position != null) {
            newBoard[position.first][position.second] = number
        }

        return newBoard

    }

    private fun getAvailableCells(boardGame: List<List<Int>>): List<Pair<Int, Int>> {

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

        return availableCells

    }

    fun fewCellsAvailable(boardGame: List<List<Int>>): Boolean {
        val availableCells = getAvailableCells(boardGame)

        if (availableCells.size <= Constants.AVAILABLE_CELLS) {
            return true
        }

        return false
    }

    private fun getAvailablePosition(boardGame: List<List<Int>>): Pair<Int, Int>? {
        val availableCells = getAvailableCells(boardGame)

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