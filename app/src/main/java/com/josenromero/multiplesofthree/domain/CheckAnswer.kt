package com.josenromero.multiplesofthree.domain

import com.josenromero.multiplesofthree.data.Step

class CheckAnswer {

    operator fun invoke(currentNumber: Int, currentList: List<Int>, currentStep: Step): Boolean {

        val result = when(currentStep) {
            Step.Step1 -> currentList.contains(currentNumber)
            Step.Step2 -> currentList.contains(currentNumber) && currentNumber % 2 == 0
            Step.Step3 -> currentList.contains(currentNumber) && currentNumber % 2 != 0
            Step.Step4 -> currentList.contains(currentNumber) && currentNumber % 5 == 0
        }

        return result

    }

}