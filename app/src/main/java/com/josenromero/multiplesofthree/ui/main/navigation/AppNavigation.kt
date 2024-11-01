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
    val player by gameViewModel.player.collectAsState()

    NavHost(navController = navController, startDestination = AppScreens.HomeScreen.route) {
        composable(route = AppScreens.HomeScreen.route) {
            HomeScreen(
                onNavigateToAScreen = { route ->
                    gameViewModel.initGame()
                    navController.navigate(route)
                }
            )
        }
        composable(route = AppScreens.PlayScreen.route) {
            PlayScreen(
                gameState = gameState,
                player = player,
                onClick = { position ->
                    gameViewModel.removeNumber(position)
                },
                onNavigateToAScreen = { route ->
                    if (route == AppScreens.PlayScreen.route) {
                        gameViewModel.initGame()
                    }
                    navController.navigate(route)
                }
            )
        }
    }

}