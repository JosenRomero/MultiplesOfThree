package com.josenromero.multiplesofthree.utils

import com.josenromero.multiplesofthree.data.Achievement
import com.josenromero.multiplesofthree.data.GameMode

fun checkAchievement(achievements: List<String>, score: Int, gameMode: GameMode): List<String> {

    val newAchievements: MutableList<String> = mutableListOf()

    val newAchievement: Achievement? = Constants.achievementsList.find { achievement ->
        achievement.scoreTarget == score && achievement.gameMode == gameMode
    }

    if (newAchievement != null && !achievements.contains(newAchievement.id.toString())) {
        newAchievements.add(newAchievement.id.toString()) // save the achievement ID

        // if complete all game modes then save last medal
        if ((achievements.size + newAchievements.size) == (Constants.achievementsList.size - 1)) {
            val lastMedal = Constants.achievementsList.last()
            newAchievements.add(lastMedal.id.toString())
        }
    }

    return newAchievements

}