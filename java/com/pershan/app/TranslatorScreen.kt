package com.pershan.app

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TranslatorScreen() {

    var sourceText by remember {
        mutableStateOf("")
    }

    var translatedText by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            text = "🌍 Translator",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        OutlinedTextField(
            value = sourceText,
            onValueChange = {
                sourceText = it
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Enter text")
            }
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Button(
            onClick = {
                translatedText = "Translation engine coming next..."
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Translate")
        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Text(
            text = translatedText,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
