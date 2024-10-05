package com.josenromero.multiplesofthree.ui.main.viewmodels

import androidx.lifecycle.ViewModel
import com.josenromero.multiplesofthree.data.GameState
import com.josenromero.multiplesofthree.domain.CreateBoardGame
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val boardGame: CreateBoardGame
): ViewModel() {

    private val _gameState = MutableStateFlow(GameState())
    val gameState = _gameState.asStateFlow()

    init {
        startNewGame()
    }

    private fun startNewGame() {
        _gameState.update {
            it.copy(
                board = boardGame.createBoard(size = 3)
            )
        }
    }

}