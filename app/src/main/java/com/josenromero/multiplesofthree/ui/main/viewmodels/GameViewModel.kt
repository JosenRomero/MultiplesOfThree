package com.josenromero.multiplesofthree.ui.main.viewmodels

import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josenromero.multiplesofthree.data.Coin
import com.josenromero.multiplesofthree.data.GameMode
import com.josenromero.multiplesofthree.data.GameState
import com.josenromero.multiplesofthree.data.Particle
import com.josenromero.multiplesofthree.data.PreCleanBoard
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
import com.josenromero.multiplesofthree.utils.getNumbersOfHearts
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
    private var initGameJob: Job? = null
    private var checkingBoardJob: Job? = null

    private var isActiveBoard: Boolean = false // If true, it allows adding numbers to the board.

    private val _isCleanBoard: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isCleanBoard = _isCleanBoard.asStateFlow()

    private val _isPreCleanBoard: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isPreCleanBoard = _isPreCleanBoard.asStateFlow()

    private val _gameMode: MutableStateFlow<GameMode> = MutableStateFlow(GameMode.EASY)

    init {
        checkPlayer()
    }

    fun initGame(gameMode: GameMode) {
        initGameJob = viewModelScope.launch {
            _gameMode.value = gameMode
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
        activeBoard(value = false)
        cleanBoard()
        stageUpdate(currentStage)
    }

    private fun stageUpdate(currentStage: Stage) {
        _stage.value = nextStage(currentStage)
    }

    private fun startNewGame() {
        gameStateUpdate(
            board = boardGame.createBoard(size = Constants.BOARD_SIZE, stage = _stage.value),
            score = 0,
            hearts = getNumbersOfHearts(gameMode = _gameMode.value)
        )
    }

    private fun startTimer() {

        cleanTimer()

        timerJob = viewModelScope.launch {
            while (!_gameState.value.isGameOver) {
                val isFewCellsAvailable = addNumberToBoardGame.fewCellsAvailable(_gameState.value.board)
                delay(3000)
                if (isFewCellsAvailable) {
                    checkingBoard()
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

        val isCorrectAnswer: Boolean = checkAnswer(currentNumber, _stage.value.listOfNumbers)

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

    private fun removeHearts(board: List<List<Int>>, value: Int) {
        gameStateUpdate(
            board = board,
            hearts = if (value <= 0) 0 else value
        )
    }

    fun exitTheGame() {
        activeBoard(value = false)
        cancelJobs()
        _isPreCleanBoard.value = false
        _isCleanBoard.value = false
    }

    private fun cleanTimer() {
        timerJob?.cancel()
        timerJob = null
    }

    private fun cancelJobs() {
        initGameJob?.cancel()
        initGameJob = null
        checkingBoardJob?.cancel()
        checkingBoardJob = null
        cleanTimer()
    }

    private fun checkingBoard() {
        checkingBoardJob = viewModelScope.launch {

            _isCleanBoard.value = true
            delay(1000) // waiting for the cleanBoard animation

            // checking board
            val preCleanBoard: PreCleanBoard = removeNumberToBoardGame.checkingBoard(_gameState.value.board, _stage.value.listOfNumbers)

            if (preCleanBoard.correctNumbers.isNotEmpty() && !_gameState.value.isGameOver) { // There are correct numbers in the board and they were not selected so hearts will be subtracted
                _isPreCleanBoard.value = true
                removeHearts(board = preCleanBoard.board, value = _gameState.value.hearts - preCleanBoard.correctNumbers.size)
                delay(500) // waiting for the animation about preCleanBoard
            }

            cleanBoard()

            if (!_gameState.value.isGameOver) {
                _isPreCleanBoard.value = false
                _isCleanBoard.value = false
            }

        }
    }

    private fun cleanBoard() {
        gameStateUpdate(
            board = boardGame.getEmptyMatrix(size = Constants.BOARD_SIZE)
        )
    }

    override fun onCleared() {
        super.onCleared()
        cancelJobs()
    }

}