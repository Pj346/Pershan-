package com.pershan.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PershanApp()
        }
    }
}

@Composable
fun PershanApp() {

    var screen by remember {
        mutableStateOf("home")
    }

    var darkMode by remember {
        mutableStateOf(true)
    }

    if (screen == "home") {

        PershanModernHome(
            darkMode = darkMode,

            onThemeChange = {
                darkMode = !darkMode
            },

            onTranslatorClick = {
                screen = "translator"
            }
        )

    } else {

        TranslatorScreen(
            onBack = {
                screen = "home"
            }
        )
    }
}

@Composable
fun TranslatorScreen(
    onBack: () -> Unit
) {

    var text by remember {
        mutableStateOf("")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFF050713),
                        Color(0xFF101A3A)
                    )
                )
            )
    ) {

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {

            item {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Button(
                        onClick = onBack,
                        shape = RoundedCornerShape(18.dp)
                    ) {
                        Text("←")
                    }

                    Spacer(
                        modifier = Modifier.width(12.dp)
                    )

                    Column {

                        Text(
                            text = "Translator",
                            color = Color.White,
                            fontSize = 27.sp
                        )

                        Text(
                            text = "Persian ↔ English",
                            color = Color(0xFFAAB5D5),
                            fontSize = 13.sp
                        )
                    }
                }
            }

            item {

                androidx.compose.material3.OutlinedTextField(
                    value = text,
                    onValueChange = {
                        text = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 6,
                    placeholder = {
                        Text("Type English text...")
                    }
                )
            }

            item {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .background(
                            Color(0xFF5865F2)
                                .copy(alpha = 0.18f),
                            RoundedCornerShape(26.dp)
                        )
                        .padding(20.dp)
                ) {

                    Column {

                        Icon(
                            imageVector = Icons.Default.Language,
                            contentDescription = null,
                            tint = Color(0xFF9AA8FF),
                            modifier = Modifier.size(30.dp)
                        )

                        Spacer(
                            modifier = Modifier.height(14.dp)
                        )

                        Text(
                            text =
                                if (text.isBlank())
                                    "Translation will appear here..."
                                else
                                    "Pershan translation engine",
                            color = Color.White,
                            fontSize = 17.sp
                        )
                    }
                }
            }
        }
    }
}
