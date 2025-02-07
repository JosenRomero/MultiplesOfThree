package com.josenromero.multiplesofthree.utils

import com.josenromero.multiplesofthree.data.GameMode

fun getNumbersOfHearts(gameMode: GameMode): Int {

    return when (gameMode) {
        GameMode.EASY -> 4
        GameMode.NORMAL -> 3
        GameMode.HARD -> 1
    }

}