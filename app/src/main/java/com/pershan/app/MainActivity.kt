                     package com.pershan.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Games
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
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

data class PershanTool(
    val title: String,
    val subtitle: String,
    val icon: ImageVector
)

@Composable
fun PershanApp() {

    var screen by remember {
        mutableStateOf("home")
    }

    var darkMode by remember {
        mutableStateOf(true)
    }

    when (screen) {

        "translator" -> {
            TranslatorScreen(
                onBack = {
                    screen = "home"
                }
            )
        }

        else -> {
            PershanHome(
                darkMode = darkMode,
                onThemeChange = {
                    darkMode = !darkMode
                },
                onTranslatorClick = {
                    screen = "translator"
                }
            )
        }
    }
}

@Composable
fun PershanHome(
    darkMode: Boolean,
    onThemeChange: () -> Unit,
    onTranslatorClick: () -> Unit
) {

    var searchText by remember {
        mutableStateOf("")
    }

    val background = if (darkMode) {
        Brush.verticalGradient(
            listOf(
                Color(0xFF050713),
                Color(0xFF0A1024),
                Color(0xFF111C3D)
            )
        )
    } else {
        Brush.verticalGradient(
            listOf(
                Color(0xFFF5F7FF),
                Color(0xFFE6EBFF)
            )
        )
    }

    val textColor =
        if (darkMode) Color.White
        else Color(0xFF101426)

    val secondaryText =
        if (darkMode) Color(0xFFAEB8D5)
        else Color(0xFF626A80)

    val tools = listOf(
        PershanTool(
            "Lens",
            "Scan & translate",
            Icons.Default.CameraAlt
        ),
        PershanTool(
            "Reader",
            "PDF & documents",
            Icons.Default.Book
        ),
        PershanTool(
            "Voice",
            "Read text aloud",
            Icons.Default.MenuBook
        ),
        PershanTool(
            "Study",
            "Learn & practice",
            Icons.Default.School
        ),
        PershanTool(
            "Game Mode",
            "Translate games",
            Icons.Default.Games
        ),
        PershanTool(
            "Settings",
            "Customize Pershan",
            Icons.Default.Settings
        )
    )

    val filteredTools = tools.filter { tool ->
        tool.title.contains(
            searchText,
            ignoreCase = true
        ) ||
                tool.subtitle.contains(
                    searchText,
                    ignoreCase = true
                )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
    ) {

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                horizontal = 18.dp,
                vertical = 22.dp
            ),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            item {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement =
                        Arrangement.SpaceBetween,
                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    Column {

                        Text(
                            text = "PERSHAN",
                            color = textColor,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.ExtraBold,
                            letterSpacing = 4.sp
                        )

                        Text(
                            text = "Your language universe",
                            color = secondaryText,
                            fontSize = 13.sp
                        )
                    }

                    IconButton(
                        onClick = onThemeChange
                    ) {

                        Icon(
                            imageVector =
                                if (darkMode)
                                    Icons.Default.LightMode
                                else
                                    Icons.Default.DarkMode,
                            contentDescription = "Theme",
                            tint = textColor
                        )
                    }
                }
            }

            item {

                OutlinedTextField(
                    value = searchText,
                    onValueChange = {
                        searchText = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    leadingIcon = {

                        Icon(
                            Icons.Default.Search,
                            contentDescription = null,
                            tint = secondaryText
                        )
                    },
                    placeholder = {

                        Text(
                            text = "Search Pershan...",
                            color = secondaryText
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor =
                            Color(0xFF7C8CFF),
                        unfocusedBorderColor =
                            Color(0xFF7C8CFF)
                                .copy(alpha = 0.25f),
                        focusedTextColor = textColor,
                        unfocusedTextColor = textColor
                    ),
                    shape = RoundedCornerShape(20.dp)
                )
            }

            item {

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(205.dp)
                        .clickable {
                            onTranslatorClick()
                        },
                    shape = RoundedCornerShape(32.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Transparent
                    )
                ) {

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.linearGradient(
                                    listOf(
                                        Color(0xFF5865F2),
                                        Color(0xFF7C4DFF),
                                        Color(0xFF3B82F6)
                                    )
                                )
                            )
                            .padding(24.dp)
                    ) {

                        Column {

                            Box(
                                modifier = Modifier
                                    .size(56.dp)
                                    .clip(CircleShape)
                                    .background(
                                        Color.White
                                            .copy(alpha = 0.16f)
                                    ),
                                contentAlignment =
                                    Alignment.Center
                            ) {

                                Icon(
                                    Icons.Default.Language,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier =
                                        Modifier.size(30.dp)
                                )
                            }

                            Spacer(
                                modifier =
                                    Modifier.height(16.dp)
                            )

                            Text(
                                text = "Translator",
                                color = Color.White,
                                fontSize = 27.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text =
                                    "Break the language barrier.",
                                color =
                                    Color.White.copy(
                                        alpha = 0.82f
                                    ),
                                fontSize = 14.sp
                            )

                            Spacer(
                                modifier =
                                    Modifier.height(13.dp)
                            )

                            Text(
                                text = "OPEN  →",
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight =
                                    FontWeight.Bold,
                                letterSpacing = 1.5.sp
                            )
                        }
                    }
                }
            }

            item {

                Text(
                    text = "Explore Pershan",
                    color = textColor,
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            items(filteredTools) { tool ->

                PershanToolCard(
                    tool = tool,
                    darkMode = darkMode,
                    textColor = textColor,
                    secondaryText = secondaryText
                )
            }

            item {

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = "PERSHAN",
                    modifier = Modifier.fillMaxWidth(),
                    color =
                        secondaryText.copy(
                            alpha = 0.65f
                        ),
                    fontSize = 11.sp,
                    letterSpacing = 2.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text =
                        "Offline First • Built for learning",
                    modifier = Modifier.fillMaxWidth(),
                    color =
                        secondaryText.copy(
                            alpha = 0.55f
                        ),
                    fontSize = 11.sp
                )
            }
        }
    }
}

@Composable
fun PershanToolCard(
    tool: PershanTool,
    darkMode: Boolean,
    textColor: Color,
    secondaryText: Color
) {

    val cardColor =
        if (darkMode)
            Color.White.copy(alpha = 0.055f)
        else
            Color.White.copy(alpha = 0.78f)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(88.dp)
            .border(
                width = 1.dp,
                color =
                    Color(0xFF8EA0FF)
                        .copy(alpha = 0.12f),
                shape = RoundedCornerShape(24.dp)
            ),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = cardColor
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 17.dp),
            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(52.dp)
                    .clip(
                        RoundedCornerShape(18.dp)
                    )
                    .background(
                        Brush.linearGradient(
                            listOf(
                                Color(0xFF5865F2)
                                    .copy(alpha = 0.25f),
                                Color(0xFF8B5CF6)
                                    .copy(alpha = 0.12f)
                            )
                        )
                    ),
                contentAlignment =
                    Alignment.Center
            ) {

                Icon(
                    imageVector = tool.icon,
                    contentDescription = tool.title,
                    tint = Color(0xFF9AA8FF),
                    modifier = Modifier.size(26.dp)
                )
            }

            Spacer(
                modifier = Modifier.width(15.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = tool.title,
                    color = textColor,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text = tool.subtitle,
                    color = secondaryText,
                    fontSize = 12.sp
                )
            }

            Text(
                text = "›",
                color = secondaryText,
                fontSize = 28.sp
            )
        }
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
                        Color(0xFF0D1530)
                    )
                )
            )
    ) {

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(20.dp),
            verticalArrangement =
                Arrangement.spacedBy(18.dp)
        ) {

            item {

                Row(
                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    Text(
                        text = "‹",
                        color = Color.White,
                        fontSize = 38.sp,
                        modifier = Modifier
                            .clickable {
                                onBack()
                            }
                            .padding(end = 12.dp)
                    )

                    Column {

                        Text(
                            text = "Translator",
                            color = Color.White,
                            fontSize = 27.sp,
                            fontWeight =
                                FontWeight.Bold
                        )

                        Text(
                            text = "Persian ↔ English",
                            color =
                                Color(0xFFAEB8D5),
                            fontSize = 13.sp
                        )
                    }
                }
            }

            item {

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(28.dp),
                    colors = CardDefaults.cardColors(
                        containerColor =
                            Color.White.copy(
                                alpha = 0.06f
                            )
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(18.dp)
                    ) {

                        Text(
                            text = "English",
                            color =
                                Color(0xFF9AA8FF),
                            fontWeight =
                                FontWeight.Bold
                        )

                        Spacer(
                            modifier =
                                Modifier.height(10.dp)
                        )

                        OutlinedTextField(
                            value = text,
                            onValueChange = {
                                text = it
                            },
                            modifier =
                                Modifier.fillMaxWidth(),
                            minLines = 5,
                            placeholder = {

                                Text(
                                    text =
                                        "Type something...",
                                    color =
                                        Color(0xFF78829F)
                                )
                            },
                            colors =
                                OutlinedTextFieldDefaults.colors(
                                    focusedTextColor =
                                        Color.White,
                                    unfocusedTextColor =
                                        Color.White,
                                    focusedBorderColor =
                                        Color(0xFF7C8CFF),
                                    unfocusedBorderColor =
                                        Color.White.copy(
                                            alpha = 0.15f
                                        )
                                ),
                            shape =
                                RoundedCornerShape(20.dp)
                        )
                    }
                }
            }

            item {

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(28.dp),
                    colors = CardDefaults.cardColors(
                        containerColor =
                            Color(0xFF5865F2)
                                .copy(alpha = 0.18f)
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {

                        Text(
                            text = "Persian",
                            color =
                                Color(0xFF9AA8FF),
                            fontWeight =
                                FontWeight.Bold
                        )

                        Spacer(
                            modifier =
                                Modifier.height(12.dp)
                        )

                        Text(
                            text =
                                if (text.isBlank())
                                    "Translation will appear here..."
                                else
                                    "Translation engine will be connected next.",
                            color =
                                Color.White.copy(
                                    alpha = 0.85f
                                ),
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
    }
}
