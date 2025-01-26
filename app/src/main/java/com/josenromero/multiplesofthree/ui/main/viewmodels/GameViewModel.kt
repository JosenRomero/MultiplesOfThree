package com.josenromero.multiplesofthree.ui.main.viewmodels

import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josenromero.multiplesofthree.data.Coin
import com.josenromero.multiplesofthree.data.GameState
import com.josenromero.multiplesofthree.data.Particle
import com.josenromero.multiplesofthree.data.Stage
import com.josenromero.multiplesofthree.data.player.PlayerEntity
import com.josenromero.multiplesofthree.domain.AddNumberToBoardGame
import com.josenromero.multiplesofthree.domain.CheckAnswer
import com.josenromero.multiplesofthree.domain.CreateBoardGame
import com.josenromero.multiplesofthree.domain.NextStage
import com.josenromero.multiplesofthree.domain.RemoveNumberToBoardGame
import com.josenromero.multiplesofthree.domain.player.AddPlayer
import com.josenromero.multiplesofthree.domain.player.GetPlayer
import com.josenromero.multiplesofthree.domain.player.UpdatePlayer
import com.josenromero.multiplesofthree.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
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
    private val updatePlayer: UpdatePlayer,
    private val checkAnswer: CheckAnswer,
    private val nextStage: NextStage
): ViewModel() {

    private val _gameState = MutableStateFlow(GameState())
    val gameState = _gameState.asStateFlow()

    private val _player: MutableStateFlow<PlayerEntity> = MutableStateFlow(PlayerEntity(bestScore = 0, achievements = arrayListOf()))
    val player = _player.asStateFlow()

    private val _coins: MutableStateFlow<MutableList<Coin>> = MutableStateFlow(mutableListOf())
    val coins = _coins.asStateFlow()

    private val _particles: MutableStateFlow<MutableList<Particle>> = MutableStateFlow(mutableListOf())
    val particles = _particles.asStateFlow()

    private val _stage: MutableStateFlow<Stage> = MutableStateFlow(Stage())
    val stage = _stage.asStateFlow()

    private var timerJob: Job? = null

    private var isActiveBoard: Boolean = false // If true, it allows adding numbers to the board.

    init {
        checkPlayer()
    }

    fun initGame() {
        viewModelScope.launch(Dispatchers.Main) {
            cleanTimer()
            _gameState.value.isGameOver = false
            removeAllCoins()
            removeAllParticles()
            stageUpdate(currentStage = Stage())
            startNewGame()
            delay(7000) // waiting for the mission animation
            startTimer()
            activeBoard(value = true)
        }
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

    fun beforeStageUpdate(currentStage: Stage) {
        viewModelScope.launch(Dispatchers.IO) {
            activeBoard(value = false)
            cleanBoard()
            stageUpdate(currentStage)
        }
    }

    private fun stageUpdate(currentStage: Stage) {
        _stage.value = nextStage(currentStage)
    }

    private fun startNewGame() {
        gameStateUpdate(
            board = boardGame.createBoard(size = Constants.BOARD_SIZE, stage = _stage.value),
            score = 0,
            hearts = Constants.NUMBER_OF_HEARTS
        )
    }

    private fun startTimer() {

        timerJob = viewModelScope.launch {
            while (!_gameState.value.isGameOver) {
                val isFewCellsAvailable = addNumberToBoardGame.fewCellsAvailable(_gameState.value.board)
                delay(3000)
                if (isFewCellsAvailable) {
                    cleanBoard()
                    if (_coins.value.size > 5) {
                        delay(1000)
                        removeAllCoins()
                    }
                    if (_particles.value.size >= 15) {
                        delay(1000)
                        removeAllParticles()
                    }
                } else addNumber()
            }
        }

    }

    fun activeBoard(value: Boolean) {
        isActiveBoard = value
    }

    private fun addNumber() {
        if (!_gameState.value.isGameOver && isActiveBoard) {
            gameStateUpdate(
                board = addNumberToBoardGame.addNumber(
                    boardGame = _gameState.value.board,
                    stage = _stage.value
                )
            )
        }
    }

    private fun removeNumber(position: Pair<Int, Int>, isMultiple: Boolean) {
        gameStateUpdate(
            board = removeNumberToBoardGame.removeNumber(_gameState.value.board, position),
            score = if (isMultiple) _gameState.value.score + 1 else null,
            hearts = if (!isMultiple) _gameState.value.hearts - 1 else null
        )
    }

    fun selectedNumber(position: Pair<Int, Int>, coordinate: Offset) {

        val currentNumber = _gameState.value.board[position.first][position.second]

        val isCorrectAnswer: Boolean = checkAnswer(currentNumber, _stage.value.listOfNumbers, _stage.value.step)

        val currentScore = _gameState.value.score

        removeNumber(position, isCorrectAnswer)

        if (isCorrectAnswer) {
            addOneCoin(id = currentScore + 1, coordinate = coordinate)
        } else {
            addParticles(coordinate = coordinate)
        }

    }

    private fun addOneCoin(id: Int, coordinate: Offset) {
        val newCoin = Coin(id = id, coordinate = coordinate)
        _coins.value.add(newCoin)
    }

    private fun removeAllCoins() {
        if (_coins.value.isNotEmpty()) {
            _coins.value.removeAll(_coins.value)
        }
    }

    private fun addParticles(coordinate: Offset) {
        repeat(times = 15) { i ->
            val id = "${_gameState.value.hearts}${i}".toInt()
            _particles.value.add(
                Particle(id = id, coordinate = coordinate)
            )
        }
    }

    private fun removeAllParticles() {
        if (_particles.value.isNotEmpty()) {
            _particles.value.removeAll(_particles.value)
        }
    }

    fun exitTheGame() {
        activeBoard(value = false)
    }

    private fun cleanTimer() {
        timerJob?.cancel()
    }

    private fun cleanBoard() {
        gameStateUpdate(
            board = boardGame.getEmptyMatrix(size = Constants.BOARD_SIZE)
        )
    }

    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }

}