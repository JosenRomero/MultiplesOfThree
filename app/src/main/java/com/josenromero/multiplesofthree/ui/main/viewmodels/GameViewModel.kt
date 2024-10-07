package com.josenromero.multiplesofthree.ui.main.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josenromero.multiplesofthree.data.GameState
import com.josenromero.multiplesofthree.domain.AddNumberToBoardGame
import com.josenromero.multiplesofthree.domain.CreateBoardGame
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val boardGame: CreateBoardGame,
    private val addNumberToBoardGame: AddNumberToBoardGame
): ViewModel() {

    private val _gameState = MutableStateFlow(GameState())
    val gameState = _gameState.asStateFlow()

    fun initGame() {
        startNewGame()
        startTimer()
    }

    private fun startNewGame() {
        _gameState.update {
            it.copy(
                board = boardGame.createBoard(size = 3),
                isGameOver = false
            )
        }
    }

    private fun startTimer() {
        viewModelScope.launch {
            while (!_gameState.value.isGameOver) {
                delay(3000)
                addNumber()
            }
        }
    }

    private fun addNumber() {
        _gameState.update {
            it.copy(
                board = addNumberToBoardGame.addNumber(_gameState.value.board),
                isGameOver = false
            )
        }
    }

}