package com.josenromero.multiplesofthree.ui.main.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.josenromero.multiplesofthree.ui.main.viewmodels.AudioViewModel
import com.josenromero.multiplesofthree.ui.main.viewmodels.Audios
import com.josenromero.multiplesofthree.ui.main.viewmodels.GameViewModel
import com.josenromero.multiplesofthree.ui.main.viewmodels.PreferencesViewModel
import com.josenromero.multiplesofthree.ui.main.views.AboutScreen
import com.josenromero.multiplesofthree.ui.main.views.AchievementsScreen
import com.josenromero.multiplesofthree.ui.main.views.HomeScreen
import com.josenromero.multiplesofthree.ui.main.views.HowToPlayScreen
import com.josenromero.multiplesofthree.ui.main.views.LanguageScreen
import com.josenromero.multiplesofthree.ui.main.views.PlayScreen
import com.josenromero.multiplesofthree.ui.main.views.SettingsScreen
import com.josenromero.multiplesofthree.ui.theme.MultiplesOfThreeTheme

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    val preferencesViewModel: PreferencesViewModel = viewModel()
    val audioViewModel: AudioViewModel = viewModel()
    val gameViewModel: GameViewModel = viewModel()
    val gameState by gameViewModel.gameState.collectAsState()
    val player by gameViewModel.player.collectAsState()
    val coins by gameViewModel.coins.collectAsState()
    val stage by gameViewModel.stage.collectAsState()
    val preferences by preferencesViewModel.preferences.collectAsState()

    MultiplesOfThreeTheme(
        darkTheme = preferences.darkMode
    ) {
        NavHost(navController = navController, startDestination = AppScreens.HomeScreen.route) {
            composable(
                route = AppScreens.HomeScreen.route,
                enterTransition = {
                    when (initialState.destination.route) {
                        AppScreens.PlayScreen.route ->
                            slideIntoContainer(
                                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                                animationSpec = tween(700)
                            )
                        AppScreens.AboutScreen.route,
                        AppScreens.AchievementsScreen.route,
                        AppScreens.SettingsScreen.route ->
                            slideIntoContainer(
                                towards = AnimatedContentTransitionScope.SlideDirection.Down,
                                animationSpec = tween(700)
                            )
                        else -> null
                    }
                },
                exitTransition = {
                    when (targetState.destination.route) {
                        AppScreens.PlayScreen.route ->
                            slideOutOfContainer(
                                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                                animationSpec = tween(700)
                            )
                        AppScreens.AboutScreen.route,
                        AppScreens.AchievementsScreen.route,
                        AppScreens.SettingsScreen.route ->
                            slideOutOfContainer(
                                towards = AnimatedContentTransitionScope.SlideDirection.Up,
                                animationSpec = tween(700)
                            )
                        AppScreens.HowToPlayScreen.route ->
                            ExitTransition.None
                        else -> null
                    }
                }
            ) {
                HomeScreen(
                    preferencesLoading = preferencesViewModel.preferencesLoading.value,
                    firstTime = preferences.firstTime,
                    backgroundMusicPlay = {
                        audioViewModel.backgroundMusicPlay(isMusic = preferences.music)
                    },
                    onNavigateToAScreen = { route ->
                        audioViewModel.play(audio = Audios.AudioTap.name, isSound = preferences.sound)
                        if (route == AppScreens.PlayScreen.route) {
                            gameViewModel.initGame()
                        }
                        navController.navigate(route)
                    }
                )
            }
            composable(
                route = AppScreens.PlayScreen.route,
                enterTransition = {
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                },
                exitTransition = {
                    when (targetState.destination.route) {
                        AppScreens.PlayScreen.route ->
                            slideOutOfContainer(
                                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                                animationSpec = tween(700)
                            )
                        AppScreens.HomeScreen.route ->
                            slideOutOfContainer(
                                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                                animationSpec = tween(700)
                            )
                        else -> null
                    }
                }
            ) {
                PlayScreen(
                    gameState = gameState,
                    player = player,
                    coins = coins,
                    stage = stage,
                    stageUpdate = {
                        gameViewModel.beforeStageUpdate(currentStage = stage)
                    },
                    updatePlayer = { bestScore, achievements ->
                        gameViewModel.updatingPlayer(bestScore, achievements)
                    },
                    onClick = { position, coordinate ->
                        gameViewModel.selectedNumber(position, coordinate)
                    },
                    onNavigateToAScreen = { route ->
                        audioViewModel.play(audio = Audios.AudioTap.name, isSound = preferences.sound)
                        if (route == AppScreens.PlayScreen.route) {
                            gameViewModel.initGame()
                        }
                        navController.navigate(route)
                    },
                    audioPlay = { name ->
                        audioViewModel.play(audio = name, isSound = preferences.sound)
                    }
                )
            }
            composable(
                route = AppScreens.AboutScreen.route,
                enterTransition = {
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Up,
                        animationSpec = tween(700)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Down,
                        animationSpec = tween(700)
                    )
                }
            ) {
                AboutScreen(
                    onNavigateToBack = {
                        audioViewModel.play(audio = Audios.AudioTap.name, isSound = preferences.sound)
                        navController.popBackStack()
                    }
                )
            }
            composable(
                route = AppScreens.AchievementsScreen.route,
                enterTransition = {
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Up,
                        animationSpec = tween(700)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Down,
                        animationSpec = tween(700)
                    )
                }
            ) {
                AchievementsScreen(
                    onNavigateToBack = {
                        audioViewModel.play(audio = Audios.AudioTap.name, isSound = preferences.sound)
                        navController.popBackStack()
                    }
                )
            }
            composable(
                route = AppScreens.LanguageScreen.route,
                enterTransition = {
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(700)
                    )
                }
            ) {
                LanguageScreen(
                    onNavigateToBack = {
                        audioViewModel.play(audio = Audios.AudioTap.name, isSound = preferences.sound)
                        navController.popBackStack()
                    }
                )
            }
            composable(
                route = AppScreens.SettingsScreen.route,
                enterTransition = {
                    when (initialState.destination.route) {
                        AppScreens.HomeScreen.route ->
                            slideIntoContainer(
                                towards = AnimatedContentTransitionScope.SlideDirection.Up,
                                animationSpec = tween(700)
                            )
                        AppScreens.LanguageScreen.route ->
                            slideIntoContainer(
                                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                                animationSpec = tween(700)
                            )
                        else -> null
                    }
                },
                exitTransition = {
                    when (targetState.destination.route) {
                        AppScreens.HomeScreen.route ->
                            slideOutOfContainer(
                                towards = AnimatedContentTransitionScope.SlideDirection.Down,
                                animationSpec = tween(700)
                            )
                        AppScreens.LanguageScreen.route ->
                            slideOutOfContainer(
                                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                                animationSpec = tween(700)
                            )
                        else -> null
                    }
                }
            ) {
                SettingsScreen(
                    preferencesVM = preferencesViewModel,
                    backgroundMusic = { value ->
                        if (value) {
                            audioViewModel.backgroundMusicPlay(isMusic = true)
                        } else {
                            audioViewModel.backgroundMusicStop()
                        }
                    },
                    onNavigateToBack = {
                        audioViewModel.play(audio = Audios.AudioTap.name, isSound = preferences.sound)
                        navController.popBackStack()
                    },
                    onNavigateToAScreen = { route ->
                        audioViewModel.play(audio = Audios.AudioTap.name, isSound = preferences.sound)
                        navController.navigate(route)
                    }
                )
            }
            composable(
                route = AppScreens.HowToPlayScreen.route,
                enterTransition = {
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Up,
                        animationSpec = tween(700)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Down,
                        animationSpec = tween(700)
                    )
                }
            ) {
                HowToPlayScreen(
                    firstTime = preferences.firstTime,
                    howToPlayViewed = {
                        preferencesViewModel.update(firstTime = false)
                    },
                    onNavigateToBack = {
                        audioViewModel.play(audio = Audios.AudioTap.name, isSound = preferences.sound)
                        navController.popBackStack()
                    }
                )
            }
        }
    }

}