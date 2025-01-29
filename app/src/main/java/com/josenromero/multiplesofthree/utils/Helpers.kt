package com.josenromero.multiplesofthree.utils

import androidx.compose.ui.graphics.Color

fun getRandomPosition(range: IntRange): Float {
    return (range).random().toFloat()
}

fun getRandomParticle(): Int {
    return Constants.particlesList.random()
}

fun getRandomColor(): Color {
    return Constants.colorsList.random()
}