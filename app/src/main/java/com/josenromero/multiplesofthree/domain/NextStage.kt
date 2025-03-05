package com.josenromero.multiplesofthree.domain

import com.josenromero.multiplesofthree.data.Stage
import com.josenromero.multiplesofthree.data.Step
import com.josenromero.multiplesofthree.utils.Constants


class NextStage {

    operator fun invoke(currentStage: Stage): Stage {
        var startNumber = currentStage.startNumber
        var endNumber = currentStage.endNumber
        var step = currentStage.step
        var listOfNumbers = currentStage.listOfNumbers

        if (listOfNumbers.isNotEmpty()) {

            step = nextStep(currentStep = step)

            if (currentStage.step == Step.Step4) {
                startNumber += 50
                endNumber += 50
            }

        }

        listOfNumbers = generateListOfNumbers(startNumber, endNumber, step)

        return Stage(startNumber, endNumber, step, listOfNumbers)

    }

    private fun nextStep(currentStep: Step): Step {
        val steps: Array<Step> = Step.values()
        val currentIndex: Int = currentStep.ordinal
        val nextIndex: Int = (currentIndex + 1) % steps.size
        return steps[nextIndex]
    }

    private fun generateListOfNumbers(startNumber: Int, endNumber: Int, currentStep: Step): List<Int> {

        val listOfNumbers = IntRange(startNumber, endNumber).toList()

        return when (currentStep) {
            Step.Step1 -> listOfNumbers.filter { it % Constants.TARGET_NUMBER == 0 }
            Step.Step2 -> listOfNumbers.filter { it % Constants.TARGET_NUMBER == 0 && it % 2 == 0 }
            Step.Step3 -> listOfNumbers.filter { it % Constants.TARGET_NUMBER == 0 && it % 2 != 0 }
            Step.Step4 -> listOfNumbers.filter { it % Constants.TARGET_NUMBER != 0 }
        }
    }
}
