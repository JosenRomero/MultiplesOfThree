package com.josenromero.multiplesofthree.domain

import com.josenromero.multiplesofthree.utils.Constants
import javax.inject.Inject

class CreateBoardGame @Inject constructor(
    private val addNumberToBoard: AddNumberToBoardGame
) {

    fun createBoard(size: Int): List<List<Int>> {

        var emptyMatrix = getEmptyMatrix(size)

        // Adds the first number
        emptyMatrix = addNumberToBoard.addNumber(emptyMatrix, true)

        return emptyMatrix

    }

    fun getEmptyMatrix(size: Int): List<List<Int>> {

        val emptyMatrix = List(size) { //rows
            List(size) {//columns
                Constants.DEFAULT_VALUE
            }
        }

        return emptyMatrix
    }

}