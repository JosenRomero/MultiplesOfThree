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

            if (step == Step.Step4) {
                startNumber += 50
                endNumber += 50
                listOfNumbers = generateListOfNumbers(startNumber, endNumber)
            }

            step = nextStep(currentStage.step)

        } else {
            // in this case, currentStage have default values
            listOfNumbers = generateListOfNumbers(startNumber, endNumber)
        }

        return Stage(startNumber, endNumber, step, listOfNumbers)

    }

    private fun nextStep(currentStep: Step): Step {
        val steps: Array<Step> = Step.values()
        val currentIndex: Int = currentStep.ordinal
        val nextIndex: Int = (currentIndex + 1) % steps.size
        return steps[nextIndex]
    }

    private fun generateListOfNumbers(startNumber: Int, endNumber: Int): List<Int> {
        return IntRange(startNumber, endNumber).toList().filter { it % Constants.TARGET_NUMBER == 0 }
    }
}
