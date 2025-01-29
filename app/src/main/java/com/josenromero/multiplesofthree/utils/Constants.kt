package com.josenromero.multiplesofthree.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.josenromero.multiplesofthree.R
import com.josenromero.multiplesofthree.data.Achievement
import com.josenromero.multiplesofthree.data.GameMode

object Constants {

    const val DEFAULT_VALUE = -1
    const val TARGET_NUMBER = 3
    const val AVAILABLE_CELLS = 4
    const val BOARD_SIZE = 3
    val CELL_SIZE = 100.dp
    const val NUMBER_OF_HEARTS = 3
    const val PLAYER_TABLE = "player_table"
    const val PREFERENCES_TABLE = "preferences_table"
    const val PLAYER_DATABASE = "player_database"

    val DEFAULT_PARTICLE_SIZE = 10.dp
    val EXPLOSION_PARTICLE_OFFSET = (CELL_SIZE.value / 2) + (DEFAULT_PARTICLE_SIZE.value)

    val achievementsList: List<Achievement> = listOf(
        Achievement(id = 1, imageId = R.drawable.first_medal, titleId = R.string.achievements_screen_achievements_first_medal, textId = R.string.achievements_screen_achievements_list_1, scoreTarget = 10, gameMode = GameMode.EASY),
        Achievement(id = 2, imageId = R.drawable.bronze_medal_1, titleId = R.string.home_screen_menu_easy_mode, textId = R.string.achievements_screen_achievements_list_2, scoreTarget = 40, gameMode = GameMode.EASY),
        Achievement(id = 3, imageId = R.drawable.silver_medal_1, titleId = R.string.home_screen_menu_easy_mode, textId = R.string.achievements_screen_achievements_list_3, scoreTarget = 80, gameMode = GameMode.EASY),
        Achievement(id = 4, imageId = R.drawable.gold_medal_1, titleId = R.string.home_screen_menu_easy_mode, textId = R.string.achievements_screen_achievements_list_4, scoreTarget = 120, gameMode = GameMode.EASY),
        Achievement(id = 5, imageId = R.drawable.bronze_medal_2, titleId = R.string.home_screen_menu_normal_mode, textId = R.string.achievements_screen_achievements_list_2, scoreTarget = 40, gameMode = GameMode.NORMAL),
        Achievement(id = 6, imageId = R.drawable.silver_medal_2, titleId = R.string.home_screen_menu_normal_mode, textId = R.string.achievements_screen_achievements_list_3, scoreTarget = 80, gameMode = GameMode.NORMAL),
        Achievement(id = 7, imageId = R.drawable.gold_medal_2, titleId = R.string.home_screen_menu_normal_mode, textId = R.string.achievements_screen_achievements_list_4, scoreTarget = 120, gameMode = GameMode.NORMAL),
        Achievement(id = 8, imageId = R.drawable.bronze_medal_3, titleId = R.string.home_screen_menu_hard_mode, textId = R.string.achievements_screen_achievements_list_3, scoreTarget = 80, gameMode = GameMode.HARD),
        Achievement(id = 9, imageId = R.drawable.silver_medal_3, titleId = R.string.home_screen_menu_hard_mode, textId = R.string.achievements_screen_achievements_list_4, scoreTarget = 120, gameMode = GameMode.HARD),
        Achievement(id = 10, imageId = R.drawable.gold_medal_3, titleId = R.string.home_screen_menu_hard_mode, textId = R.string.achievements_screen_achievements_list_5, scoreTarget = 160, gameMode = GameMode.HARD),
        Achievement(id = 11, imageId = R.drawable.last_medal, titleId = R.string.achievements_screen_achievements_last_medal, textId = R.string.achievements_screen_achievements_last_medal_subtitle, scoreTarget = 200, gameMode = GameMode.HARD)
    )

    val particlesList = listOf(
        R.drawable.particle_01,
        R.drawable.particle_02,
        R.drawable.particle_03
    )

    // language
    const val englishTag = "en"
    const val spanishTag = "es"
    const val japaneseTag = "ja"

    val colorsList = listOf(
        Color(0xFFB28DFF),
        Color(0xFFD5AAFF),
        Color(0xFFAFCBFF),
        Color(0xFF6EB5FF),
        Color(0xFFFCC2FF),
        Color(0xFFAFF8D8),
        Color(0xFFFFFFD1),
        Color(0xFFFFABAB)
    )

}