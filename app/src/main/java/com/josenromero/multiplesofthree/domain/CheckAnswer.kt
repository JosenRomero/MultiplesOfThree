package com.josenromero.multiplesofthree.domain

class CheckAnswer {

    operator fun invoke(currentNumber: Int, currentList: List<Int>): Boolean {

        return currentList.contains(currentNumber)

    }

}