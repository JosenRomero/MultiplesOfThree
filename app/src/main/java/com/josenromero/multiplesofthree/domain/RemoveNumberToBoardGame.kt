package com.josenromero.multiplesofthree.domain

import com.josenromero.multiplesofthree.data.PreCleanBoard
import com.josenromero.multiplesofthree.utils.Constants
import javax.inject.Inject

class RemoveNumberToBoardGame @Inject constructor(
    private val checkAnswer: CheckAnswer
) {

    fun removeNumber(boardGame: List<List<Int>>, position: Pair<Int, Int>): List<List<Int>> {
        val newBoard: MutableList<MutableList<Int>> = boardGame.map { it.toMutableList() }.toMutableList()
        newBoard[position.first][position.second] = Constants.DEFAULT_VALUE
        return newBoard
    }

    fun checkingBoard(
        boardGame: List<List<Int>>,
        currentListOfNumbers: List<Int>,
    ): PreCleanBoard {

        val lineSize = boardGame.size
        val newBoard: MutableList<MutableList<Int>> = boardGame.map { it.toMutableList() }.toMutableList()
        val correctNumbers: MutableList<Int> = mutableListOf()

        for (rowIndex in 0 until lineSize) {
            for (columIndex in 0 until lineSize) {
                val cell = boardGame[rowIndex][columIndex]
                if (cell != Constants.DEFAULT_VALUE) { //checking if this cell has a number
                    // checking if the number is a correct number
                    val isCorrectNumber: Boolean = checkAnswer(cell, currentListOfNumbers)
                    if (!isCorrectNumber) {
                        newBoard[rowIndex][columIndex] = Constants.DEFAULT_VALUE
                    } else {
                        val correctNumber = newBoard[rowIndex][columIndex]
                        correctNumbers.add(correctNumber)
                    }
                }
            }
        }

        return PreCleanBoard(board = newBoard, correctNumbers = correctNumbers)
    }

}