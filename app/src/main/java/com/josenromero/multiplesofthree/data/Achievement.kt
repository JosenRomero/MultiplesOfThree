package com.josenromero.multiplesofthree.data

import androidx.annotation.StringRes

data class Achievement(
    val id: Int,
    val imageId: Int,
    @StringRes val titleId: Int,
    @StringRes val textId: Int,
    val scoreTarget: Int
)
