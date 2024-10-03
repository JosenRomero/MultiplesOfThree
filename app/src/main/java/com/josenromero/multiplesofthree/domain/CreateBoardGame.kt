package com.josenromero.multiplesofthree.domain

import com.josenromero.multiplesofthree.utils.Constants
import kotlin.random.Random

class CreateBoardGame {

    fun createBoard(size: Int): List<List<Int>> {

        val emptyMatrix = MutableList(size) { //rows
            MutableList(size) {//columns
                Constants.DEFAULT_VALUE
            }
        }

        // Adds the first number
        val position1 = getPosition(emptyMatrix)
        emptyMatrix[position1.first][position1.second] = Constants.FIRST_NUMBER

        return emptyMatrix

    }

    private fun getPosition(matrix: List<List<Int>>): Pair<Int, Int> {
        val positionY = Random.nextInt(matrix.size)
        val positionX = Random.nextInt(matrix.size)

        val current = matrix[positionY][positionX]

        if(current == Constants.DEFAULT_VALUE) {
            return Pair(positionY, positionX)
        }

        return getPosition(matrix)

    }

}