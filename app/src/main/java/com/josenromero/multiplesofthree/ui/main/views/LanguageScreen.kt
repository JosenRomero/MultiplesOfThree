package com.josenromero.multiplesofthree.ui.main.views

import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import androidx.core.os.LocaleListCompat
import com.josenromero.multiplesofthree.R
import com.josenromero.multiplesofthree.ui.main.components.CustomTitle
import com.josenromero.multiplesofthree.ui.main.components.SimpleTopAppBar
import com.josenromero.multiplesofthree.utils.Constants

@Composable
fun LanguageScreen(
    onNavigateToBack: () -> Unit
) {

    val language: String = getCurrentLanguage()

    Scaffold(
        topBar = {
            SimpleTopAppBar(
                title = {
                    CustomTitle(text = stringResource(id = R.string.select_language))
                },
                onNavigateToAScreen = { onNavigateToBack() }
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                LanguageItem(
                    textId = R.string.english,
                    currentLanguage = language,
                    targetLanguage = Constants.englishTag
                )
                LanguageItem(
                    textId = R.string.spanish,
                    currentLanguage = language,
                    targetLanguage = Constants.spanishTag
                )
                LanguageItem(
                    textId = R.string.japanese,
                    currentLanguage = language,
                    targetLanguage = Constants.japaneseTag
                )
            }
        }
    }

}

@Composable
fun LanguageItem(
    @StringRes textId: Int,
    selected: Boolean = false,
    currentLanguage: String,
    targetLanguage: String
) {
    NavigationDrawerItem(
        label = { Text(text = stringResource(id = textId)) },
        selected = selected,
        onClick = {
            changeLanguage(targetLanguage)
        },
        badge = {
            if (currentLanguage == targetLanguage) {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = "done icon",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    )
}

fun changeLanguage(localeTag: String) {
    AppCompatDelegate.setApplicationLocales(
        LocaleListCompat.forLanguageTags(localeTag)
    )
}

fun getCurrentLanguage(): String {
    return AppCompatDelegate.getApplicationLocales().get(0)?.language ?: getDefaultLanguage()
}

fun getDefaultLanguage(): String {
    val systemLanguage: String = Locale.current.language
    var language = Constants.englishTag
    if (systemLanguage == Constants.spanishTag || systemLanguage == Constants.japaneseTag) {
        language = systemLanguage
    }
    return language
}