package com.josenromero.multiplesofthree.domain

import com.josenromero.multiplesofthree.utils.Constants
import javax.inject.Inject

class CreateBoardGame @Inject constructor(
    private val addNumberToBoard: AddNumberToBoardGame
) {

    fun createBoard(size: Int): List<List<Int>> {

        var emptyMatrix = List(size) { //rows
            List(size) {//columns
                Constants.DEFAULT_VALUE
            }
        }

        // Adds the first number
        emptyMatrix = addNumberToBoard.addNumber(emptyMatrix, true)

        return emptyMatrix

    }

}