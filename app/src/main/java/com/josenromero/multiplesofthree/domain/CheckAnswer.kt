package com.josenromero.multiplesofthree.domain

import com.josenromero.multiplesofthree.data.Step

class CheckAnswer {

    operator fun invoke(currentNumber: Int, currentList: List<Int>, currentStep: Step): Boolean {

        val result = when(currentStep) {
            Step.Step1 -> currentList.contains(currentNumber) // multiple of the target number
            Step.Step2 -> currentList.contains(currentNumber) && currentNumber % 2 == 0 // multiple of the target number and even number
            Step.Step3 -> currentList.contains(currentNumber) && currentNumber % 2 != 0 // multiple of the target number and odd number
            Step.Step4 -> false
        }

        return result

    }

}