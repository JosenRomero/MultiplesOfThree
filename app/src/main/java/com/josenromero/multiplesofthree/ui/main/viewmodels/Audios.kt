package com.josenromero.multiplesofthree.ui.main.viewmodels

sealed class Audios(val name: String) {
    object AudioTap: Audios("AudioTap")
    object AudioGameOver: Audios("AudioGameOver")
    object AudioNewAchievements: Audios("AudioNewAchievements")
}