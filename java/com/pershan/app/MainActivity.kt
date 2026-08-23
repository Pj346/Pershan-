package com.pershan.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Games
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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

data class PershanFeature(
    val title: String,
    val description: String,
    val icon: ImageVector
)

@Composable
fun PershanApp() {

    var currentScreen by remember {
        mutableStateOf("home")
    }

    var darkMode by remember {
        mutableStateOf(true)
    }

    if (currentScreen == "translator") {

        TranslatorScreen()

    } else {

        PershanHome(
            darkMode = darkMode,
            onToggleTheme = {
                darkMode = !darkMode
            },
            onTranslatorClick = {
                currentScreen = "translator"
            }
        )
    }
}

@Composable
fun PershanHome(
    darkMode: Boolean,
    onToggleTheme: () -> Unit,
    onTranslatorClick: () -> Unit
) {

    val backgroundTop =
        if (darkMode) Color(0xFF050817)
        else Color(0xFFF5F7FF)

    val backgroundBottom =
        if (darkMode) Color(0xFF111B3A)
        else Color(0xFFE8ECFA)

    val textColor =
        if (darkMode) Color.White
        else Color(0xFF11131A)

    val secondaryText =
        if (darkMode) Color(0xFFB9C2DD)
        else Color(0xFF5D6475)

    val cardColor =
        if (darkMode) Color(0xFF151D35)
        else Color.White

    val features = listOf(

        PershanFeature(
            "Translator",
            "Translate text offline",
            Icons.Default.Language
        ),

        PershanFeature(
            "Lens",
            "OCR and image translation",
            Icons.Default.CameraAlt
        ),

        PershanFeature(
            "Reader",
            "PDF, Word and EPUB",
            Icons.Default.Book
        ),

        PershanFeature(
            "Voice",
            "Speech and reading tools",
            Icons.Default.MenuBook
        ),

        PershanFeature(
            "Study Mode",
            "Learning and practice",
            Icons.Default.School
        ),

        PershanFeature(
            "Game Mode",
            "Translate game text",
            Icons.Default.Games
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        backgroundTop,
                        backgroundBottom
                    )
                )
            )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column {

                    Text(
                        text = "PERSHAN",
                        color = textColor,
                        fontSize = 30.sp
                    )

                    Text(
                        text = "Your offline language ecosystem",
                        color = secondaryText,
                        fontSize = 14.sp
                    )
                }

                Button(
                    onClick = onToggleTheme
                ) {
                    Text(
                        if (darkMode) "☀️"
                        else "🌙"
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(28.dp),
                colors = CardDefaults.cardColors(
                    containerColor = cardColor
                )
            ) {

                Column(
                    modifier = Modifier.padding(22.dp)
                ) {

                    Text(
                        text = "🌌 Welcome to Pershan",
                        color = textColor,
                        fontSize = 22.sp
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text(
                        text = "Translate, read, learn and understand your world.",
                        color = secondaryText,
                        fontSize = 15.sp
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(22.dp)
            )

            Text(
                text = "Pershan Tools",
                color = textColor,
                fontSize = 21.sp
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(features) { feature ->

                    FeatureCard(
                        feature = feature,
                        cardColor = cardColor,
                        textColor = textColor,
                        secondaryText = secondaryText,
                        onClick = {

                            if (feature.title == "Translator") {
                                onTranslatorClick()
                            }
                        }
                    )
                }
            }

            Text(
                text = "Pershan • Offline First",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                color = secondaryText,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun FeatureCard(
    feature: PershanFeature,
    cardColor: Color,
    textColor: Color,
    secondaryText: Color,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(155.dp)
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = cardColor
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(18.dp),
            verticalArrangement = Arrangement.Center
        ) {

            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(
                        Color(0xFF5865F2).copy(alpha = 0.2f)
                    ),
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    imageVector = feature.icon,
                    contentDescription = feature.title,
                    tint = Color(0xFF8EA0FF)
                )
            }

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(
                text = feature.title,
                color = textColor,
                fontSize = 17.sp
            )

            Text(
                text = feature.description,
                color = secondaryText,
                fontSize = 12.sp
            )
        }
    }
}
