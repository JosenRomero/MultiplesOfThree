package com.josenromero.multiplesofthree.utils

import com.josenromero.multiplesofthree.data.Achievement

fun checkAchievement(achievements: List<String>, score: Int): List<String> {

    val currentAchievements: MutableList<String> = achievements.toMutableList()

    val newAchievement: Achievement? = Constants.achievementsList.find { achievement ->
        achievement.scoreTarget == score
    }

    if (newAchievement != null) {
        currentAchievements.add(newAchievement.id.toString()) // save the achievement ID
    }

    return currentAchievements

}