package com.example.intelliversetranslator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.remote.TranslationService
import kotlinx.coroutines.launch
import util.Language

data class MyState(
    val inputText: String = "",
    val translatedText: String = "",
    val sourceLang: Language = Language.defaultSource,
    val targetLang: Language = Language.defaultTarget,
    val isLoading: Boolean = false,
    val languages: List<Language> = Language.getAll()
)
class MyViewModel : ViewModel() {

    var uiState by mutableStateOf(MyState())
        private set

    fun onInputChange(newText: String) {
        uiState = uiState.copy(inputText = newText)
    }

    fun onSourceLanguageChange(lang: Language) {
        uiState = uiState.copy(sourceLang = lang)
    }

    fun onTargetLanguageChange(lang: Language) {
        uiState = uiState.copy(targetLang = lang)
    }

    fun onTranslate() {
        if (uiState.inputText.isBlank()) return

        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)
            val result = TranslationService.translate(
                uiState.inputText,
                uiState.sourceLang.code,
                uiState.targetLang.code
            )
            uiState = uiState.copy(translatedText = result, isLoading = false)
        }
    }
}

