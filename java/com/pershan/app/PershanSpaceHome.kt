package com.pershan.app

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Canvas
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
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Games
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.School
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.math.sin

private data class SpaceTool(
    val title: String,
    val subtitle: String,
    val icon: ImageVector,
    val accent: Color
)

@Composable
fun PershanSpaceHome(
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

    var selectedTool by remember {
        mutableStateOf<String?>(null)
    }

    LaunchedEffect(Unit) {
        delay(120)
        visible = true
    }

    val textColor =
        if (darkMode) {
            Color.White
        } else {
            Color(0xFF111522)
        }

    val secondaryText =
        if (darkMode) {
            Color(0xFFAFB9D8)
        } else {
            Color(0xFF667085)
        }

    val backgroundTop =
        if (darkMode) {
            Color(0xFF030510)
        } else {
            Color(0xFFF5F7FF)
        }

    val backgroundBottom =
        if (darkMode) {
            Color(0xFF101A42)
        } else {
            Color(0xFFE8ECFF)
        }

    val tools = remember {
        listOf(
            SpaceTool(
                "Lens",
                "Scan & translate",
                Icons.Default.CameraAlt,
                Color(0xFF6D7CFF)
            ),
            SpaceTool(
                "Reader",
                "PDF & files",
                Icons.Default.Book,
                Color(0xFF8B5CF6)
            ),
            SpaceTool(
                "Voice",
                "Read aloud",
                Icons.Default.MenuBook,
                Color(0xFF4F9DFF)
            ),
            SpaceTool(
                "Study",
                "Learn smarter",
                Icons.Default.School,
                Color(0xFF9B6CFF)
            ),
            SpaceTool(
                "Game Mode",
                "Translate games",
                Icons.Default.Games,
                Color(0xFFFF6FAE)
            ),
            SpaceTool(
                "Settings",
                "Personalize",
                Icons.Default.Settings,
                Color(0xFF6C89A8)
            )
        )
    }

    val filteredTools =
        tools.filter {
            it.title.contains(search, ignoreCase = true) ||
                    it.subtitle.contains(search, ignoreCase = true)
        }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        backgroundTop,
                        if (darkMode) {
                            Color(0xFF080D25)
                        } else {
                            Color(0xFFEFF1FF)
                        },
                        backgroundBottom
                    )
                )
            )
    ) {

        // SPACE BACKGROUND
        if (darkMode) {
            SpaceStars()
            NebulaGlow()
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = 18.dp,
                    vertical = 18.dp
                )
        ) {

            // ---------------------------------------------------------
            // TOP BAR
            // ---------------------------------------------------------

            AnimatedVisibility(
                visible = visible,
                enter =
                    fadeIn(
                        animationSpec =
                            tween(700)
                    ) +
                            slideInVertically(
                                initialOffsetY = {
                                    -40
                                },
                                animationSpec =
                                    tween(
                                        700,
                                        easing =
                                            FastOutSlowInEasing
                                    )
                            )
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
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
                                fontSize = 30.sp,
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
                                    Color(0xFF9BA8FF),
                                modifier =
                                    Modifier.size(19.dp)
                            )
                        }

                        Text(
                            text =
                                "Your language universe",
                            color = secondaryText,
                            fontSize = 12.sp,
                            letterSpacing = 0.5.sp
                        )
                    }

                    GlassThemeButton(
                        darkMode = darkMode,
                        onClick = onThemeChange
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            // ---------------------------------------------------------
            // SEARCH
            // ---------------------------------------------------------

            AnimatedVisibility(
                visible = visible,
                enter =
                    fadeIn(
                        animationSpec =
                            tween(850)
                    )
            ) {

                Box(
                    modifier = Modifier.fillMaxWidth()
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
                                tint = secondaryText
                            )
                        },
                        placeholder = {

                            Text(
                                text =
                                    "Search anything...",
                                color =
                                    secondaryText
                            )
                        },
                        colors =
                            OutlinedTextFieldDefaults.colors(
                                focusedBorderColor =
                                    Color(0xFF8998FF),
                                unfocusedBorderColor =
                                    if (darkMode) {
                                        Color.White.copy(
                                            alpha = 0.13f
                                        )
                                    } else {
                                        Color(0xFFB7C0DA)
                                    },
                                focusedTextColor =
                                    textColor,
                                unfocusedTextColor =
                                    textColor,
                                cursorColor =
                                    Color(0xFF9AA8FF)
                            ),
                        shape =
                            RoundedCornerShape(22.dp)
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            // ---------------------------------------------------------
            // HERO
            // ---------------------------------------------------------

            AnimatedVisibility(
                visible = visible,
                enter =
                    fadeIn(
                        animationSpec =
                            tween(
                                900,
                                delayMillis = 150
                            )
                    ) +
                            scaleIn(
                                initialScale = 0.92f,
                                animationSpec =
                                    tween(
                                        850,
                                        delayMillis = 150,
                                        easing =
                                            FastOutSlowInEasing
                                    )
                            )
            ) {

                SpaceTranslatorCard(
                    onClick = onTranslatorClick
                )
            }

            Spacer(
                modifier = Modifier.height(22.dp)
            )

            // ---------------------------------------------------------
            // EXPLORE TITLE
            // ---------------------------------------------------------

            AnimatedVisibility(
                visible = visible,
                enter =
                    fadeIn(
                        animationSpec =
                            tween(1000)
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

                    Text(
                        text = "Explore",
                        color = textColor,
                        fontSize = 22.sp,
                        fontWeight =
                            FontWeight.Bold
                    )

                    Text(
                        text =
                            "${filteredTools.size} tools",
                        color = secondaryText,
                        fontSize = 11.sp
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            // ---------------------------------------------------------
            // TOOLS
            // ---------------------------------------------------------

            LazyVerticalGrid(
                columns =
                    GridCells.Fixed(2),
                modifier =
                    Modifier.weight(1f),
                contentPadding =
                    PaddingValues(
                        bottom = 22.dp
                    ),
                horizontalArrangement =
                    Arrangement.spacedBy(12.dp),
                verticalArrangement =
                    Arrangement.spacedBy(12.dp)
            ) {

                items(
                    items = filteredTools,
                    key = {
                        it.title
                    }
                ) { tool ->

                    SpaceToolCard(
                        tool = tool,
                        darkMode = darkMode,
                        selected =
                            selectedTool ==
                                    tool.title,
                        onClick = {
                            selectedTool =
                                if (
                                    selectedTool ==
                                    tool.title
                                ) {
                                    null
                                } else {
                                    tool.title
                                }
                        }
                    )
                }
            }
        }
    }
}

// =====================================================================
// SPACE STARS
// =====================================================================

@Composable
private fun SpaceStars() {

    val infinite =
        rememberInfiniteTransition(
            label = "stars"
        )

    val movement by
        infinite.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec =
                infiniteRepeatable(
                    animation =
                        tween(
                            durationMillis = 7000
                        ),
                    repeatMode =
                        RepeatMode.Reverse
                ),
            label = "starMovement"
        )

    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {

        val stars = listOf(
            Triple(0.08f, 0.13f, 1.5f),
            Triple(0.21f, 0.27f, 2f),
            Triple(0.37f, 0.08f, 1f),
            Triple(0.51f, 0.19f, 1.7f),
            Triple(0.67f, 0.10f, 1.2f),
            Triple(0.82f, 0.23f, 1.8f),
            Triple(0.93f, 0.12f, 1f),
            Triple(0.14f, 0.42f, 1.1f),
            Triple(0.31f, 0.51f, 1.8f),
            Triple(0.58f, 0.43f, 1.2f),
            Triple(0.76f, 0.55f, 1.7f),
            Triple(0.91f, 0.46f, 1.1f),
            Triple(0.05f, 0.71f, 1.2f),
            Triple(0.25f, 0.82f, 1.6f),
            Triple(0.44f, 0.69f, 1.1f),
            Triple(0.64f, 0.78f, 1.8f),
            Triple(0.87f, 0.72f, 1.3f),
            Triple(0.96f, 0.88f, 1f)
        )

        stars.forEachIndexed { index, star ->

            val x =
                star.first * size.width

            val y =
                (
                    star.second +
                            sin(
                                movement * 3f +
                                        index
                            ) * 0.008f
                    ) * size.height

            val alpha =
                0.25f +
                        (
                            sin(
                                movement * 6f +
                                        index
                            ) + 1f
                        ) * 0.22f

            drawCircle(
                color =
                    Color.White.copy(
                        alpha = alpha
                    ),
                radius = star.third,
                center =
                    Offset(
                        x,
                        y
                    )
            )
        }
    }
}

// =====================================================================
// NEBULA GLOW
// =====================================================================

@Composable
private fun NebulaGlow() {

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Box(
            modifier = Modifier
                .size(260.dp)
                .align(Alignment.TopEnd)
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            Color(0xFF635BFF)
                                .copy(alpha = 0.12f),
                            Color.Transparent
                        )
                    )
                )
        )

        Box(
            modifier = Modifier
                .size(300.dp)
                .align(Alignment.BottomStart)
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            Color(0xFF8B5CF6)
                                .copy(alpha = 0.09f),
                            Color.Transparent
                        )
                    )
                )
        )
    }
}

// =====================================================================
// THEME BUTTON
// =====================================================================

@Composable
private fun GlassThemeButton(
    darkMode: Boolean,
    onClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .size(48.dp)
            .clip(CircleShape)
            .background(
                Color.White.copy(
                    alpha =
                        if (darkMode) {
                            0.07f
                        } else {
                            0.65f
                        }
                )
            )
            .border(
                width = 1.dp,
                color =
                    Color.White.copy(
                        alpha =
                            if (darkMode) {
                                0.14f
                            } else {
                                0.55f
                            }
                    ),
                shape = CircleShape
            )
            .clickable {
                onClick()
            },
        contentAlignment =
            Alignment.Center
    ) {

        Text(
            text =
                if (darkMode) {
                    "☀"
                } else {
                    "☾"
                },
            color =
                if (darkMode) {
                    Color(0xFFFFD76D)
                } else {
                    Color(0xFF4E5A9B)
                },
            fontSize = 22.sp
        )
    }
}

// =====================================================================
// TRANSLATOR HERO
// =====================================================================

@Composable
private fun SpaceTranslatorCard(
    onClick: () -> Unit
) {

    val infinite =
        rememberInfiniteTransition(
            label = "hero"
        )

    val glow by
        infinite.animateFloat(
            initialValue = 0.72f,
            targetValue = 1f,
            animationSpec =
                infiniteRepeatable(
                    animation =
                        tween(
                            1800,
                            easing =
                                FastOutSlowInEasing
                        ),
                    repeatMode =
                        RepeatMode.Reverse
                ),
            label = "heroGlow"
        )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(215.dp)
            .clip(
                RoundedCornerShape(34.dp)
            )
            .clickable {
                onClick()
            }
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF515FF7),
                        Color(0xFF7955E9),
                        Color(0xFF2479E9)
                    )
                )
            )
            .border(
                width = 1.dp,
                color =
                    Color.White.copy(
                        alpha = 0.27f
                    ),
                shape =
                    RoundedCornerShape(34.dp)
            )
    ) {

        // Decorative orbit
        Canvas(
            modifier = Modifier
                .size(230.dp)
                .align(Alignment.TopEnd)
        ) {

            val path = Path()

            path.moveTo(
                size.width * 0.12f,
                size.height * 0.55f
            )

            path.cubicTo(
                size.width * 0.35f,
                size.height * 0.10f,
                size.width * 0.82f,
                size.height * 0.15f,
                size.width * 0.95f,
                size.height * 0.55f
            )

            drawPath(
                path = path,
                color =
                    Color.White.copy(
                        alpha =
                            0.15f * glow
                    ),
                style =
                    androidx.compose.ui.graphics.drawscope.Stroke(
                        width = 2.dp.toPx()
                    )
            )
        }

        Box(
            modifier = Modifier
                .size(160.dp)
                .align(Alignment.TopEnd)
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            Color.White.copy(
                                alpha =
                                    0.15f * glow
                            ),
                            Color.Transparent
                        )
                    )
                )
        )

        Column(
            modifier =
                Modifier
                    .padding(24.dp)
        ) {

            Box(
                modifier = Modifier
                    .size(55.dp)
                    .clip(CircleShape)
                    .background(
                        Color.White.copy(
                            alpha = 0.17f
                        )
                    )
                    .border(
                        1.dp,
                        Color.White.copy(
                            alpha = 0.2f
                        ),
                        CircleShape
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

            Spacer(
                modifier =
                    Modifier.height(2.dp)
            )

            Text(
                text =
                    "Your world. One language.",
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

            Box(
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(12.dp)
                    )
                    .background(
                        Color.White.copy(
                            alpha = 0.14f
                        )
                    )
                    .padding(
                        horizontal = 14.dp,
                        vertical = 8.dp
                    )
            ) {

                Text(
                    text = "OPEN  →",
                    color = Color.White,
                    fontSize = 11.sp,
                    fontWeight =
                        FontWeight.Bold,
                    letterSpacing = 2.sp
                )
            }
        }
    }
}

// =====================================================================
// GLASS TOOL CARD
// =====================================================================

@Composable
private fun SpaceToolCard(
    tool: SpaceTool,
    darkMode: Boolean,
    selected: Boolean,
    onClick: () -> Unit
) {

    val cardColor =
        if (darkMode) {
            if (selected) {
                Color.White.copy(
                    alpha = 0.13f
                )
            } else {
                Color.White.copy(
                    alpha = 0.065f
                )
            }
        } else {
            if (selected) {
                Color.White.copy(
                    alpha = 0.92f
                )
            } else {
                Color.White.copy(
                    alpha = 0.70f
                )
            }
        }

    val textColor =
        if (darkMode) {
            Color.White
        } else {
            Color(0xFF121626)
        }

    val secondary =
        if (darkMode) {
            Color(0xFFAAB5D5)
        } else {
            Color(0xFF667085)
        }

    Column(
        modifier = Modifier
            .height(151.dp)
            .clip(
                RoundedCornerShape(27.dp)
            )
            .clickable {
                onClick()
            }
            .background(cardColor)
            .border(
                width =
                    if (selected) {
                        1.5.dp
                    } else {
                        1.dp
                    },
                color =
                    if (selected) {
                        tool.accent.copy(
                            alpha = 0.70f
                        )
                    } else {
                        Color.White.copy(
                            alpha =
                                if (darkMode) {
                                    0.12f
                                } else {
                                    0.52f
                                }
                        )
                    },
                shape =
                    RoundedCornerShape(27.dp)
            )
            .padding(17.dp)
    ) {

        Row(
            modifier =
                Modifier.fillMaxWidth(),
            horizontalArrangement =
                Arrangement.SpaceBetween
        ) {

            Box(
                modifier = Modifier
                    .size(49.dp)
                    .clip(
                        RoundedCornerShape(16.dp)
                    )
                    .background(
                        Brush.linearGradient(
                            colors = listOf(
                                tool.accent.copy(
                                    alpha = 0.30f
                                ),
                                tool.accent.copy(
                                    alpha = 0.10f
                                )
                            )
                        )
                    ),
                contentAlignment =
                    Alignment.Center
            ) {

                Icon(
                    imageVector =
                        tool.icon,
                    contentDescription =
                        tool.title,
                    tint = tool.accent,
                    modifier =
                        Modifier.size(25.dp)
                )
            }

            if (selected) {

                Box(
                    modifier = Modifier
                        .size(7.dp)
                        .clip(CircleShape)
                        .background(
                            tool.accent
                        )
                )
            }
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

        Spacer(
            modifier =
                Modifier.height(2.dp)
        )

        Text(
            text = tool.subtitle,
            color = secondary,
            fontSize = 11.sp
        )
    }
}
