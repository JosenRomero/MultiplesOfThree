package com.josenromero.multiplesofthree.ui.main.navigation

sealed class AppScreens(val route: String) {
    object HomeScreen: AppScreens("HomeScreen")
    object PlayScreen: AppScreens("PlayScreen")
}