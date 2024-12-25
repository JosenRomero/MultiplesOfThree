package com.josenromero.multiplesofthree.data

import androidx.annotation.StringRes
import com.josenromero.multiplesofthree.R

enum class GameMode(@StringRes val textId: Int) {
    EASY(textId = R.string.home_screen_menu_easy_mode),
    NORMAL(textId = R.string.home_screen_menu_normal_mode),
    HARD(textId = R.string.home_screen_menu_hard_mode)
}