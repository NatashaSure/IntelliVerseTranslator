package com.example.intelliversetranslator

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import model.MyViewModel
import util.Language

@Composable
fun MainScreen(viewModel: MyViewModel = viewModel()) {
    val state = viewModel.uiState

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        OutlinedTextField(
            value = state.inputText,
            onValueChange = viewModel::onInputChange,
            label = { Text("Enter text") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            LanguageDropdown(state.languages, state.sourceLang, viewModel::onSourceLanguageChange)
            Spacer(modifier = Modifier.width(8.dp))
            LanguageDropdown(state.languages, state.targetLang, viewModel::onTargetLanguageChange)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = viewModel::onTranslate,
            modifier = Modifier.fillMaxWidth(),
            enabled = !state.isLoading
        ) {
            Text(if (state.isLoading) "Translating..." else "Translate")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = state.translatedText, modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun LanguageDropdown(
    languages: List<Language>,
    selected: Language,
    onLanguageSelected: (Language) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        Text(
            text = selected.name,
            modifier = Modifier
                .clickable { expanded = true }
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
                .padding(12.dp)
        )

        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            languages.forEach { lang ->
                DropdownMenuItem(onClick = {
                    onLanguageSelected(lang)
                    expanded = false
                }, text = { Text(lang.name) })
            }
        }
    }
}
