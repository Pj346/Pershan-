package com.pershan.app

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateInt
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
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Games
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Translate
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

private data class PershanHomeTool(
    val title: String,
    val subtitle: String,
    val icon: ImageVector,
    val accent: Color,
    val tag: String
)

@Composable
fun PershanSpaceHome(
    darkMode: Boolean,
    onThemeChange: () -> Unit,
    onTranslatorClick: () -> Unit
) {
    var visible by remember { mutableStateOf(false) }
    var search by remember { mutableStateOf("") }
    var selectedTool by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        delay(120)
        visible = true
    }

    val textColor =
        if (darkMode) Color.White
        else Color(0xFF111522)

    val secondaryText =
        if (darkMode) Color(0xFFAFB9D8)
        else Color(0xFF667085)

    val topColor =
        if (darkMode) Color(0xFF02040D)
        else Color(0xFFF5F7FF)

    val middleColor =
        if (darkMode) Color(0xFF080D25)
        else Color(0xFFECEFFF)

    val bottomColor =
        if (darkMode) Color(0xFF111A42)
        else Color(0xFFF8F9FF)

    val tools = remember {
        listOf(
            PershanHomeTool(
                "Lens",
                "Scan & translate",
                Icons.Default.CameraAlt,
                Color(0xFF6E7CFF),
                "VISION"
            ),
            PershanHomeTool(
                "Translator",
                "Text & image",
                Icons.Default.Translate,
                Color(0xFF4F9DFF),
                "LANGUAGE"
            ),
            PershanHomeTool(
                "Reader",
                "PDF & documents",
                Icons.Default.Book,
                Color(0xFF8B5CF6),
                "FILES"
            ),
            PershanHomeTool(
                "Voice",
                "Read aloud",
                Icons.Default.MenuBook,
                Color(0xFF45B7FF),
                "VOICE"
            ),
            PershanHomeTool(
                "Study",
                "Learn smarter",
                Icons.Default.School,
                Color(0xFFA56CFF),
                "LEARN"
            ),
            PershanHomeTool(
                "Game Mode",
                "Translate games",
                Icons.Default.Games,
                Color(0xFFFF6FAE),
                "GAMES"
            ),
            PershanHomeTool(
                "Space Mode",
                "Explore the universe",
                Icons.Default.Cloud,
                Color(0xFF6D8CFF),
                "SPACE"
            ),
            PershanHomeTool(
                "Settings",
                "Personalize Pershan",
                Icons.Default.Settings,
                Color(0xFF71809D),
                "SYSTEM"
            )
        )
    }

    val filteredTools =
        tools.filter {
            it.title.contains(search, ignoreCase = true) ||
                    it.subtitle.contains(search, ignoreCase = true) ||
                    it.tag.contains(search, ignoreCase = true)
        }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        topColor,
                        middleColor,
                        bottomColor
                    )
                )
            )
    ) {

        if (darkMode) {
            SpaceStars()
            NebulaBackground()
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = 18.dp,
                    vertical = 18.dp
                )
        ) {

            AnimatedVisibility(
                visible = visible,
                enter =
                    fadeIn(tween(700)) +
                            slideInVertically(
                                initialOffsetY = { -40 },
                                animationSpec =
                                    tween(
                                        700,
                                        easing =
                                            FastOutSlowInEasing
                                    )
                            )
            ) {

                PershanTopBar(
                    darkMode = darkMode,
                    textColor = textColor,
                    secondaryText = secondaryText,
                    onThemeChange = onThemeChange
                )
            }

            Spacer(Modifier.height(17.dp))

            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(tween(850))
            ) {

                PershanSearch(
                    value = search,
                    onValueChange = {
                        search = it
                    },
                    textColor = textColor,
                    secondaryText = secondaryText,
                    darkMode = darkMode
                )
            }

            Spacer(Modifier.height(17.dp))

            AnimatedVisibility(
                visible = visible,
                enter =
                    fadeIn(
                        tween(
                            900,
                            delayMillis = 100
                        )
                    ) +
                            scaleIn(
                                initialScale = 0.93f,
                                animationSpec =
                                    tween(
                                        850,
                                        delayMillis = 100,
                                        easing =
                                            FastOutSlowInEasing
                                    )
                            )
            ) {

                PershanTranslatorHero(
                    onClick = onTranslatorClick
                )
            }

            Spacer(Modifier.height(20.dp))

            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(tween(1000))
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement =
                        Arrangement.SpaceBetween,
                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    Column {

                        Text(
                            text = "Explore",
                            color = textColor,
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text =
                                "Everything you need, in one space.",
                            color = secondaryText,
                            fontSize = 11.sp
                        )
                    }

                    GlassCounter(
                        count = filteredTools.size,
                        darkMode = darkMode
                    )
                }
            }

            Spacer(Modifier.height(12.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
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
                    key = { it.title }
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

@Composable
private fun PershanTopBar(
    darkMode: Boolean,
    textColor: Color,
    secondaryText: Color,
    onThemeChange: () -> Unit
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

                Spacer(Modifier.width(8.dp))

                Icon(
                    imageVector =
                        Icons.Default.AutoAwesome,
                    contentDescription = null,
                    tint =
                        Color(0xFF9CA8FF),
                    modifier =
                        Modifier.size(19.dp)
                )
            }

            Text(
                text = "Your language universe",
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

@Composable
private fun PershanSearch(
    value: String,
    onValueChange: (String) -> Unit,
    textColor: Color,
    secondaryText: Color,
    darkMode: Boolean
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        leadingIcon = {

            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = secondaryText
            )
        },
        placeholder = {

            Text(
                text = "Search anything...",
                color = secondaryText
            )
        },
        colors =
            OutlinedTextFieldDefaults.colors(
                focusedBorderColor =
                    Color(0xFF8998FF),
                unfocusedBorderColor =
                    if (darkMode) {
                        Color.White.copy(alpha = 0.13f)
                    } else {
                        Color(0xFFB7C0DA)
                    },
                focusedTextColor = textColor,
                unfocusedTextColor = textColor,
                cursorColor =
                    Color(0xFF9AA8FF)
            ),
        shape = RoundedCornerShape(22.dp)
    )
}

@Composable
private fun GlassCounter(
    count: Int,
    darkMode: Boolean
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(14.dp))
            .background(
                Color.White.copy(
                    alpha =
                        if (darkMode) 0.08f
                        else 0.72f
                )
            )
            .border(
                1.dp,
                Color.White.copy(
                    alpha =
                        if (darkMode) 0.12f
                        else 0.5f
                ),
                RoundedCornerShape(14.dp)
            )
            .padding(
                horizontal = 11.dp,
                vertical = 7.dp
            )
    ) {

        Text(
            text = "$count tools",
            color =
                if (darkMode)
                    Color(0xFFB8C3E5)
                else
                    Color(0xFF667085),
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

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
                        if (darkMode) 0.07f
                        else 0.65f
                )
            )
            .border(
                1.dp,
                Color.White.copy(
                    alpha =
                        if (darkMode) 0.14f
                        else 0.55f
                ),
                CircleShape
            )
            .clickable {
                onClick()
            },
        contentAlignment =
            Alignment.Center
    ) {

        Text(
            text =
                if (darkMode) "☀"
                else "☾",
            color =
                if (darkMode)
                    Color(0xFFFFD76D)
                else
                    Color(0xFF4E5A9B),
            fontSize = 22.sp
        )
    }
}

@Composable
private fun PershanTranslatorHero(
    onClick: () -> Unit
) {
    val infinite =
        rememberInfiniteTransition(
            label = "translatorHero"
        )

    val glow by
        infinite.animateFloat(
            initialValue = 0.70f,
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
            .height(218.dp)
            .clip(
                RoundedCornerShape(34.dp)
            )
            .clickable {
                onClick()
            }
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF5363FA),
                        Color(0xFF7B55E9),
                        Color(0xFF287BEA)
                    )
                )
            )
            .border(
                1.dp,
                Color.White.copy(alpha = 0.27f),
                RoundedCornerShape(34.dp)
            )
    ) {

        Canvas(
            modifier =
                Modifier
                    .size(245.dp)
                    .align(Alignment.TopEnd)
        ) {

            val path = Path()

            path.moveTo(
                size.width * 0.05f,
                size.height * 0.58f
            )

            path.cubicTo(
                size.width * 0.30f,
                size.height * 0.08f,
                size.width * 0.80f,
                size.height * 0.12f,
                size.width * 0.97f,
                size.height * 0.57f
            )

            drawPath(
                path = path,
                color =
                    Color.White.copy(
                        alpha =
                            0.16f * glow
                    ),
                style =
                    androidx.compose.ui.graphics.drawscope.Stroke(
                        width = 2.dp.toPx()
                    )
            )
        }

        Box(
            modifier = Modifier
                .size(190.dp)
                .align(Alignment.TopEnd)
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            Color.White.copy(
                                alpha =
                                    0.16f * glow
                            ),
                            Color.Transparent
                        )
                    )
                )
        )

        Column(
            modifier =
                Modifier.padding(24.dp)
        ) {

            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(
                        Color.White.copy(alpha = 0.17f)
                    )
                    .border(
                        1.dp,
                        Color.White.copy(alpha = 0.22f),
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
                        Modifier.size(30.dp)
                )
            }

            Spacer(Modifier.height(14.dp))

            Text(
                text = "Translator",
                color = Color.White,
                fontSize = 30.sp,
                fontWeight =
                    FontWeight.ExtraBold
            )

            Text(
                text =
                    "Your world. One language.",
                color =
                    Color.White.copy(alpha = 0.82f),
                fontSize = 14.sp
            )

            Spacer(Modifier.height(14.dp))

            Box(
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(12.dp)
                    )
                    .background(
                        Color.White.copy(alpha = 0.14f)
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
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp
                )
            }
        }
    }
}

@Composable
private fun SpaceToolCard(
    tool: PershanHomeTool,
    darkMode: Boolean,
    selected: Boolean,
    onClick: () -> Unit
) {
    val infinite =
        rememberInfiniteTransition(
            label = "card_${tool.title}"
        )

    val pulse by
        infinite.animateFloat(
            initialValue = 0.88f,
            targetValue = 1f,
            animationSpec =
                infiniteRepeatable(
                    animation =
                        tween(
                            2200,
                            easing =
                                FastOutSlowInEasing
                        ),
                    repeatMode =
                        RepeatMode.Reverse
                ),
            label = "pulse"
        )

    val cardColor =
        if (darkMode) {
            if (selected)
                Color.White.copy(alpha = 0.13f)
            else
                Color.White.copy(alpha = 0.065f)
        } else {
            if (selected)
                Color.White.copy(alpha = 0.94f)
            else
                Color.White.copy(alpha = 0.70f)
        }

    val textColor =
        if (darkMode)
            Color.White
        else
            Color(0xFF121626)

    val secondary =
        if (darkMode)
            Color(0xFFAAB5D5)
        else
            Color(0xFF667085)

    Column(
        modifier = Modifier
            .height(157.dp)
            .clip(
                RoundedCornerShape(27.dp)
            )
            .clickable {
                onClick()
            }
            .background(cardColor)
            .border(
                width =
                    if (selected) 1.5.dp
                    else 1.dp,
                color =
                    if (selected) {
                        tool.accent.copy(
                            alpha =
                                0.72f * pulse
                        )
                    } else {
                        Color.White.copy(
                            alpha =
                                if (darkMode)
                                    0.12f
                                else
                                    0.52f
                        )
                    },
                shape =
                    RoundedCornerShape(27.dp)
            )
            .padding(17.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement =
                Arrangement.SpaceBetween,
            verticalAlignment =
                Alignment.Top
        ) {

            Box(
                modifier = Modifier
                    .size(50.dp)
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
                                    alpha = 0.09f
                                )
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
                    tint = tool.accent,
                    modifier =
                        Modifier.size(25.dp)
                )
            }

            if (selected) {

                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(
                            tool.accent
                        )
                )
            }
        }

        Spacer(Modifier.height(12.dp))

        Text(
            text = tool.title,
            color = textColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(2.dp))

        Text(
            text = tool.subtitle,
            color = secondary,
            fontSize = 11.sp
        )

        Spacer(Modifier.height(7.dp))

        Text(
            text = tool.tag,
            color =
                tool.accent.copy(alpha = 0.75f),
            fontSize = 8.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.3.sp
        )
    }
}

@Composable
private fun SpaceStars() {
    val infinite =
        rememberInfiniteTransition(
            label = "spaceStars"
        )

    val movement by
        infinite.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec =
                infiniteRepeatable(
                    animation =
                        tween(7000),
                    repeatMode =
                        RepeatMode.Reverse
                ),
            label = "movement"
        )

    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {

        val stars =
            listOf(
                Triple(0.06f, 0.10f, 1.2f),
                Triple(0.13f, 0.23f, 1.7f),
                Triple(0.21f, 0.08f, 1.0f),
                Triple(0.31f, 0.30f, 1.5f),
                Triple(0.40f, 0.12f, 1.1f),
                Triple(0.50f, 0.24f, 1.8f),
                Triple(0.61f, 0.08f, 1.1f),
                Triple(0.71f, 0.18f, 1.5f),
                Triple(0.83f, 0.09f, 1.2f),
                Triple(0.94f, 0.25f, 1.7f),
                Triple(0.08f, 0.47f, 1.0f),
                Triple(0.23f, 0.59f, 1.5f),
                Triple(0.39f, 0.48f, 1.1f),
                Triple(0.55f, 0.62f, 1.6f),
                Triple(0.73f, 0.50f, 1.2f),
                Triple(0.90f, 0.65f, 1.5f),
                Triple(0.05f, 0.76f, 1.2f),
                Triple(0.19f, 0.88f, 1.7f),
                Triple(0.36f, 0.73f, 1.0f),
                Triple(0.52f, 0.86f, 1.5f),
                Triple(0.69f, 0.75f, 1.1f),
                Triple(0.86f, 0.87f, 1.6f)
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
                0.22f +
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
                    Offset(x, y)
            )
        }
    }
}

@Composable
private fun NebulaBackground() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Box(
            modifier = Modifier
                .size(310.dp)
                .align(Alignment.TopEnd)
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            Color(0xFF665CFF)
                                .copy(alpha = 0.13f),
                            Color.Transparent
                        )
                    )
                )
        )

        Box(
            modifier = Modifier
                .size(330.dp)
                .align(Alignment.BottomStart)
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            Color(0xFF8B5CF6)
                                .copy(alpha = 0.10f),
                            Color.Transparent
                        )
                    )
                )
        )
    }
}
