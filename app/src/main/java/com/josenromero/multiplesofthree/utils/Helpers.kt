package com.josenromero.multiplesofthree.utils

fun getRandomPosition(range: IntRange): Float {
    return (range).random().toFloat()
}

fun getRandomParticle(): Int {
    return Constants.particlesList.random()
}