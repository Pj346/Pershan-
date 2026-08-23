package com.pershan.app

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
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
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import kotlinx.coroutines.delay

data class PershanHomeTool(
    val title: String,
    val subtitle: String,
    val icon: ImageVector
)

@Composable
fun PershanModernHome(
    darkMode: Boolean,
    onThemeChange: () -> Unit,
    onTranslatorClick: () -> Unit
) {

    var visible by remember {
        mutableStateOf(false)
    }

    var search by remember {
        mutableStateOf("")
    }

    LaunchedEffect(Unit) {
        delay(100)
        visible = true
    }

    val textColor =
        if (darkMode) Color.White
        else Color(0xFF111522)

    val secondary =
        if (darkMode) Color(0xFFAAB5D5)
        else Color(0xFF667085)

    val background = Brush.verticalGradient(
        listOf(
            if (darkMode) Color(0xFF040612) else Color(0xFFF5F7FF),
            if (darkMode) Color(0xFF0B1230) else Color(0xFFE8ECFF),
            if (darkMode) Color(0xFF111A3D) else Color(0xFFF7F8FF)
        )
    )

    val tools = listOf(
        PershanHomeTool(
            "Lens",
            "Scan text",
            Icons.Default.CameraAlt
        ),
        PershanHomeTool(
            "Reader",
            "PDF & files",
            Icons.Default.Book
        ),
        PershanHomeTool(
            "Voice",
            "Read aloud",
            Icons.Default.MenuBook
        ),
        PershanHomeTool(
            "Study",
            "Learn smarter",
            Icons.Default.School
        ),
        PershanHomeTool(
            "Game Mode",
            "Translate games",
            Icons.Default.Games
        ),
        PershanHomeTool(
            "Settings",
            "Personalize",
            Icons.Default.Settings
        )
    )

    val filtered =
        tools.filter {
            it.title.contains(search, true) ||
                    it.subtitle.contains(search, true)
        }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = 18.dp,
                    vertical = 20.dp
                )
        ) {

            AnimatedVisibility(
                visible = visible,
                enter =
                    fadeIn() +
                            slideInVertically(
                                initialOffsetY = {
                                    -30
                                }
                            )
            ) {

                Row(
                    modifier =
                        Modifier.fillMaxWidth(),
                    horizontalArrangement =
                        Arrangement.SpaceBetween,
                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    Column {

                        Row(
                            verticalAlignment =
                                Alignment.CenterVertically
                        ) {

                            Text(
                                text = "PERSHAN",
                                color = textColor,
                                fontSize = 31.sp,
                                fontWeight =
                                    FontWeight.ExtraBold,
                                letterSpacing = 3.sp
                            )

                            Spacer(
                                modifier =
                                    Modifier.width(8.dp)
                            )

                            Icon(
                                imageVector =
                                    Icons.Default.AutoAwesome,
                                contentDescription = null,
                                tint =
                                    Color(0xFF9AA8FF),
                                modifier =
                                    Modifier.size(20.dp)
                            )
                        }

                        Text(
                            text =
                                "Your language universe",
                            color = secondary,
                            fontSize = 12.sp
                        )
                    }

                    IconButton(
                        onClick = onThemeChange
                    ) {

                        Text(
                            text =
                                if (darkMode) "☀"
                                else "☾",
                            color = textColor,
                            fontSize = 25.sp
                        )
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            AnimatedVisibility(
                visible = visible,
                enter = fadeIn()
            ) {

                OutlinedTextField(
                    value = search,
                    onValueChange = {
                        search = it
                    },
                    modifier =
                        Modifier.fillMaxWidth(),
                    singleLine = true,
                    leadingIcon = {

                        Icon(
                            imageVector =
                                Icons.Default.Search,
                            contentDescription = null,
                            tint = secondary
                        )
                    },
                    placeholder = {

                        Text(
                            text =
                                "Search anything...",
                            color = secondary
                        )
                    },
                    colors =
                        OutlinedTextFieldDefaults.colors(
                            focusedBorderColor =
                                Color(0xFF8B9AFF),
                            unfocusedBorderColor =
                                Color.White.copy(
                                    alpha = 0.14f
                                ),
                            focusedTextColor =
                                textColor,
                            unfocusedTextColor =
                                textColor
                        ),
                    shape =
                        RoundedCornerShape(22.dp)
                )
            }

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            AnimatedVisibility(
                visible = visible,
                enter =
                    fadeIn() +
                            slideInVertically(
                                initialOffsetY = {
                                    60
                                }
                            )
            ) {

                GlassHeroCard(
                    onClick = onTranslatorClick
                )
            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            Text(
                text = "Explore",
                color = textColor,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.weight(1f),
                contentPadding =
                    PaddingValues(bottom = 20.dp),
                horizontalArrangement =
                    Arrangement.spacedBy(12.dp),
                verticalArrangement =
                    Arrangement.spacedBy(12.dp)
            ) {

                items(filtered) { tool ->

                    GlassToolCard(
                        tool = tool,
                        darkMode = darkMode
                    )
                }
            }
        }
    }
}

@Composable
private fun GlassHeroCard(
    onClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(205.dp)
            .clip(
                RoundedCornerShape(32.dp)
            )
            .clickable {
                onClick()
            }
            .background(
                Brush.linearGradient(
                    listOf(
                        Color(0xFF5968FF)
                            .copy(alpha = 0.92f),
                        Color(0xFF8B5CF6)
                            .copy(alpha = 0.88f),
                        Color(0xFF2879FF)
                            .copy(alpha = 0.88f)
                    )
                )
            )
            .border(
                1.dp,
                Color.White.copy(alpha = 0.24f),
                RoundedCornerShape(32.dp)
            )
            .padding(24.dp)
    ) {

        Box(
            modifier = Modifier
                .size(145.dp)
                .align(Alignment.TopEnd)
                .clip(CircleShape)
                .background(
                    Color.White.copy(
                        alpha = 0.07f
                    )
                )
        )

        Column {

            Box(
                modifier = Modifier
                    .size(54.dp)
                    .clip(CircleShape)
                    .background(
                        Color.White.copy(
                            alpha = 0.16f
                        )
                    ),
                contentAlignment =
                    Alignment.Center
            ) {

                Icon(
                    imageVector =
                        Icons.Default.Language,
                    contentDescription = null,
                    tint = Color.White,
                    modifier =
                        Modifier.size(29.dp)
                )
            }

            Spacer(
                modifier =
                    Modifier.height(15.dp)
            )

            Text(
                text = "Translator",
                color = Color.White,
                fontSize = 29.sp,
                fontWeight =
                    FontWeight.ExtraBold
            )

            Text(
                text =
                    "Translate without limits.",
                color =
                    Color.White.copy(
                        alpha = 0.82f
                    ),
                fontSize = 14.sp
            )

            Spacer(
                modifier =
                    Modifier.height(14.dp)
            )

            Text(
                text = "OPEN  →",
                color = Color.White,
                fontSize = 12.sp,
                fontWeight =
                    FontWeight.Bold,
                letterSpacing = 2.sp
            )
        }
    }
}

@Composable
private fun GlassToolCard(
    tool: PershanHomeTool,
    darkMode: Boolean
) {

    val cardColor =
        if (darkMode)
            Color.White.copy(alpha = 0.065f)
        else
            Color.White.copy(alpha = 0.76f)

    val textColor =
        if (darkMode) Color.White
        else Color(0xFF121626)

    val secondary =
        if (darkMode)
            Color(0xFFAAB5D5)
        else
            Color(0xFF667085)

    Column(
        modifier = Modifier
            .height(145.dp)
            .clip(
                RoundedCornerShape(26.dp)
            )
            .background(cardColor)
            .border(
                1.dp,
                Color.White.copy(
                    alpha =
                        if (darkMode) 0.12f
                        else 0.55f
                ),
                RoundedCornerShape(26.dp)
            )
            .padding(17.dp)
    ) {

        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(
                    RoundedCornerShape(16.dp)
                )
                .background(
                    Brush.linearGradient(
                        listOf(
                            Color(0xFF6877FF)
                                .copy(alpha = 0.28f),
                            Color(0xFF9C72FF)
                                .copy(alpha = 0.16f)
                        )
                    )
                ),
            contentAlignment =
                Alignment.Center
        ) {

            Icon(
                imageVector = tool.icon,
                contentDescription =
                    tool.title,
                tint = Color(0xFF9AA8FF),
                modifier =
                    Modifier.size(25.dp)
            )
        }

        Spacer(
            modifier =
                Modifier.height(13.dp)
        )

        Text(
            text = tool.title,
            color = textColor,
            fontSize = 16.sp,
            fontWeight =
                FontWeight.Bold
        )

        Text(
            text = tool.subtitle,
            color = secondary,
            fontSize = 11.sp
        )
    }
}
