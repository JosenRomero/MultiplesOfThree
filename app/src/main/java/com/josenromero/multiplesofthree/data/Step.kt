package com.josenromero.multiplesofthree.data

import androidx.annotation.StringRes
import com.josenromero.multiplesofthree.R

enum class Step(@StringRes val textId: Int) {
    Step1(textId = R.string.howToPlay_screen_text_mission_1),
    Step2(textId = R.string.howToPlay_screen_text_mission_2),
    Step3(textId = R.string.howToPlay_screen_text_mission_3),
    Step4(textId = R.string.howToPlay_screen_text_mission_4)
}