package com.josenromero.multiplesofthree.data

data class GameState(
    val board: List<List<Int>> = emptyList(),
    var score: Int = 0,
    var hearts: Int = 3,
    var isGameOver: Boolean = false
)