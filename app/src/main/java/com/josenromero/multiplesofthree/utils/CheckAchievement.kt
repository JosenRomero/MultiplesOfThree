package com.josenromero.multiplesofthree.utils

import com.josenromero.multiplesofthree.data.Achievement
import com.josenromero.multiplesofthree.data.GameMode

fun checkAchievement(achievements: List<String>, score: Int, gameMode: GameMode): List<String> {

    val currentAchievements: MutableList<String> = achievements.toMutableList()

    val newAchievement: Achievement? = Constants.achievementsList.find { achievement ->
        achievement.scoreTarget == score && achievement.gameMode == gameMode
    }

    if (newAchievement != null && !currentAchievements.contains(newAchievement.id.toString())) {
        currentAchievements.add(newAchievement.id.toString()) // save the achievement ID

        // if complete all game modes then save last medal
        if (currentAchievements.size == Constants.achievementsList.size - 1) {
            val lastMedal = Constants.achievementsList.last()
            currentAchievements.add(lastMedal.id.toString())
        }
    }

    return currentAchievements

}