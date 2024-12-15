package com.josenromero.multiplesofthree.data

data class Stage(
    val startNumber: Int = 1,
    val endNumber: Int = 50,
    val step: Step = Step.Step1,
    val listOfNumbers: List<Int> = emptyList()
)
