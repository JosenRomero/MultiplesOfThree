package com.josenromero.multiplesofthree.ui.main.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josenromero.multiplesofthree.data.GameState
import com.josenromero.multiplesofthree.data.player.PlayerEntity
import com.josenromero.multiplesofthree.domain.AddNumberToBoardGame
import com.josenromero.multiplesofthree.domain.CreateBoardGame
import com.josenromero.multiplesofthree.domain.RemoveNumberToBoardGame
import com.josenromero.multiplesofthree.domain.player.AddPlayer
import com.josenromero.multiplesofthree.domain.player.GetPlayer
import com.josenromero.multiplesofthree.domain.player.UpdatePlayer
import com.josenromero.multiplesofthree.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val boardGame: CreateBoardGame,
    private val addNumberToBoardGame: AddNumberToBoardGame,
    private val removeNumberToBoardGame: RemoveNumberToBoardGame,
    private val getPlayer: GetPlayer,
    private val addPlayer: AddPlayer,
    private val updatePlayer: UpdatePlayer
): ViewModel() {

    private val _gameState = MutableStateFlow(GameState())
    val gameState = _gameState.asStateFlow()

    private val _player: MutableStateFlow<PlayerEntity> = MutableStateFlow(PlayerEntity(bestScore = 0, achievements = arrayListOf()))
    val player = _player.asStateFlow()

    init {
        checkPlayer()
    }

    fun initGame() {
        _gameState.value.isGameOver = false
        startNewGame()
        startTimer()
    }

    private fun checkPlayer() {
        viewModelScope.launch(Dispatchers.IO) {
            val currentPlayer = getPlayer()

            withContext(Dispatchers.Main) {
                if (currentPlayer != null) {
                    _player.value = currentPlayer
                } else {
                    createPlayer()
                }
            }
        }
    }

    private fun createPlayer() {
        viewModelScope.launch(Dispatchers.IO) {
            addPlayer(PlayerEntity(bestScore = 0, achievements = arrayListOf()))

            withContext(Dispatchers.Main) {
                checkPlayer()
            }
        }
    }

    fun updatingPlayer(bestScore: Int?, achievements: List<String>?) {
        viewModelScope.launch(Dispatchers.IO) {
            updatePlayer(
                PlayerEntity(
                    uid = _player.value.uid,
                    bestScore = bestScore ?: _player.value.bestScore,
                    achievements = achievements ?: _player.value.achievements
                )
            )
            checkPlayer()
        }
    }

    private fun gameStateUpdate(board: List<List<Int>>, score: Int? = null, hearts: Int? = null) {
        if (!_gameState.value.isGameOver) {
            _gameState.update {
                it.copy(
                    board = board,
                    score = score ?: _gameState.value.score,
                    hearts = hearts ?: _gameState.value.hearts,
                    isGameOver = hearts == 0
                )
            }
        }
    }

    private fun startNewGame() {
        gameStateUpdate(
            board = boardGame.createBoard(size = Constants.BOARD_SIZE),
            score = 0,
            hearts = 3
        )
    }

    private fun startTimer() {
        viewModelScope.launch {
            while (!_gameState.value.isGameOver) {
                val isFewCellsAvailable = addNumberToBoardGame.fewCellsAvailable(_gameState.value.board)
                delay(3000)
                if (isFewCellsAvailable) cleanBoard() else addNumber()
            }
        }
    }

    private fun addNumber() {
        gameStateUpdate(
            board = addNumberToBoardGame.addNumber(_gameState.value.board)
        )
    }

    fun removeNumber(position: Pair<Int, Int>) {

        val currentNumber = _gameState.value.board[position.first][position.second]

        if (currentNumber != Constants.DEFAULT_VALUE) {

            val isMultiple: Boolean = currentNumber % Constants.FIRST_NUMBER == 0

            gameStateUpdate(
                board = removeNumberToBoardGame.removeNumber(_gameState.value.board, position),
                score = if (isMultiple) _gameState.value.score + 1 else null,
                hearts = if (!isMultiple) _gameState.value.hearts - 1 else null
            )
        }
    }

    private fun cleanBoard() {
        gameStateUpdate(
            board = boardGame.getEmptyMatrix(size = Constants.BOARD_SIZE)
        )
    }

}