package com.josenromero.multiplesofthree.ui.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.josenromero.multiplesofthree.ui.main.viewmodels.GameViewModel
import com.josenromero.multiplesofthree.ui.main.views.HomeScreen
import com.josenromero.multiplesofthree.ui.main.views.PlayScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    val gameViewModel: GameViewModel = viewModel()
    val gameState by gameViewModel.gameState.collectAsState()

    NavHost(navController = navController, startDestination = AppScreens.HomeScreen.route) {
        composable(route = AppScreens.HomeScreen.route) {
            HomeScreen(
                onNavigateToAScreen = { route ->
                    navController.navigate(route)
                }
            )
        }
        composable(route = AppScreens.PlayScreen.route) {
            PlayScreen(
                board = gameState.board
            )
        }
    }

}