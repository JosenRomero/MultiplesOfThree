package com.josenromero.multiplesofthree.utils

import androidx.compose.ui.unit.dp
import com.josenromero.multiplesofthree.R
import com.josenromero.multiplesofthree.data.Achievement

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

    // TODO: add all the imageId to achievementsList
    val achievementsList: List<Achievement> = listOf(
        Achievement(id = 1, imageId = R.drawable.play, titleId = R.string.home_screen_menu_easy_mode, textId = R.string.achievements_screen_achievements_list_1, scoreTarget = 10),
        Achievement(id = 2, imageId = R.drawable.play, titleId = R.string.home_screen_menu_easy_mode, textId = R.string.achievements_screen_achievements_list_2, scoreTarget = 40),
        Achievement(id = 3, imageId = R.drawable.play, titleId = R.string.home_screen_menu_easy_mode, textId = R.string.achievements_screen_achievements_list_3, scoreTarget = 80),
        Achievement(id = 4, imageId = R.drawable.play, titleId = R.string.home_screen_menu_easy_mode, textId = R.string.achievements_screen_achievements_list_4, scoreTarget = 120),
        Achievement(id = 5, imageId = R.drawable.play, titleId = R.string.home_screen_menu_normal_mode, textId = R.string.achievements_screen_achievements_list_2, scoreTarget = 40),
        Achievement(id = 6, imageId = R.drawable.play, titleId = R.string.home_screen_menu_normal_mode, textId = R.string.achievements_screen_achievements_list_3, scoreTarget = 80),
        Achievement(id = 7, imageId = R.drawable.play, titleId = R.string.home_screen_menu_normal_mode, textId = R.string.achievements_screen_achievements_list_4, scoreTarget = 120),
        Achievement(id = 8, imageId = R.drawable.play, titleId = R.string.home_screen_menu_hard_mode, textId = R.string.achievements_screen_achievements_list_3, scoreTarget = 80),
        Achievement(id = 9, imageId = R.drawable.play, titleId = R.string.home_screen_menu_hard_mode, textId = R.string.achievements_screen_achievements_list_4, scoreTarget = 120),
        Achievement(id = 10, imageId = R.drawable.play, titleId = R.string.home_screen_menu_hard_mode, textId = R.string.achievements_screen_achievements_list_5, scoreTarget = 160),
    )

    // language
    const val englishTag = "en"
    const val spanishTag = "es"
    const val japaneseTag = "ja"

}