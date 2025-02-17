package com.josenromero.multiplesofthree.ui.main.navigation

sealed class AppScreens(val route: String) {
    object HomeScreen: AppScreens("HomeScreen")
    object PlayScreen: AppScreens("PlayScreen")
    object AboutScreen: AppScreens("AboutScreen")
    object AchievementsScreen: AppScreens("AchievementsScreen")
    object LanguageScreen: AppScreens("LanguageScreen")
    object SettingsScreen: AppScreens("SettingsScreen")
    object HowToPlayScreen: AppScreens("HowToPlayScreen")
    object EndScreen: AppScreens("EndScreen")
}