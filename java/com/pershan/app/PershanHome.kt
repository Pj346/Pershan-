
package com.pershan.app

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.togetherWith
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
import androidx.compose.foundation.layout.offset
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
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Games
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt
import kotlin.random.Random

data class PershanHomeTool(
    val title: String,
    val subtitle: String,
    val icon: ImageVector
)

private data class Star(
    val x: Float,
    val y: Float,
    val radius: Float,
    val alpha: Float,
    val speed: Float
)

@Composable
fun PershanModernHome(
    darkMode: Boolean,
    onThemeChange: () -> Unit,
    onTranslatorClick: () -> Unit
) {
    var spaceMode by remember { mutableStateOf(true) }
    var neonAiMode by remember { mutableStateOf(false) }
    var hologramMenu by remember { mutableStateOf(false) }
    var settingsOpen by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }

    var accentIndex by remember { mutableStateOf(0) }
    var glowIntensity by remember { mutableFloatStateOf(0.65f) }
    var transparency by remember { mutableFloatStateOf(0.82f) }

    var introVisible by remember { mutableStateOf(false) }

    val accentColors = listOf(
        Color(0xFF6C7BFF),
        Color(0xFFB56CFF),
        Color(0xFF3FA9FF),
        Color(0xFF39D98A),
        Color(0xFFFF5FA2),
        Color(0xFFFFB84D)
    )

    val accent = accentColors[accentIndex]

    LaunchedEffect(Unit) {
        introVisible = true
    }

    val backgroundBrush = if (spaceMode) {
        Brush.verticalGradient(
            listOf(
                Color(0xFF02030A),
                Color(0xFF070B1E),
                Color(0xFF0C1536)
            )
        )
    } else {
        if (darkMode) {
            Brush.verticalGradient(
                listOf(
                    Color(0xFF070A14),
                    Color(0xFF111627),
                    Color(0xFF171D32)
                )
            )
        } else {
            Brush.verticalGradient(
                listOf(
                    Color(0xFFF6F8FF),
                    Color(0xFFECEFFF),
                    Color(0xFFF9FAFF)
                )
            )
        }
    }

    val primaryText = if (darkMode || spaceMode) {
        Color.White
    } else {
        Color(0xFF111522)
    }

    val secondaryText = if (darkMode || spaceMode) {
        Color(0xFFACB7D7)
    } else {
        Color(0xFF687086)
    }

    val glassWhite =
        if (darkMode || spaceMode) Color.White else Color(0xFFFFFFFF)

    val glassAlpha =
        (0.035f + transparency * 0.09f).coerceIn(0.03f, 0.15f)

    val tools = listOf(
        PershanHomeTool(
            "Translator",
            "Break the language barrier",
            Icons.Default.Language
        ),
        PershanHomeTool(
            "Lens",
            "Scan & translate",
            Icons.Default.CameraAlt
        ),
        PershanHomeTool(
            "Reader",
            "PDF & documents",
            Icons.Default.Book
        ),
        PershanHomeTool(
            "Voice",
            "Read text aloud",
            Icons.Default.MenuBook
        ),
        PershanHomeTool(
            "Study",
            "Learn & practice",
            Icons.Default.School
        ),
        PershanHomeTool(
            "Game Mode",
            "Translate games",
            Icons.Default.Games
        ),
        PershanHomeTool(
            "Settings",
            "Personalize Pershan",
            Icons.Default.Settings
        ),
        PershanHomeTool(
            "AI Tools",
            "Smart language tools",
            Icons.Default.AutoAwesome
        )
    )

    val filteredTools = tools.filter {
        it.title.contains(searchText, true) ||
            it.subtitle.contains(searchText, true)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundBrush)
    ) {
        if (spaceMode) {
            PershanStarField(
                modifier = Modifier.fillMaxSize(),
                accent = accent
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 18.dp)
        ) {
            AnimatedVisibility(
                visible = introVisible,
                enter = fadeIn() + slideInVertically(
                    initialOffsetY = { -40 }
                ),
                exit = fadeOut()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    GlassCircleButton(
                        icon = Icons.Default.Menu,
                        contentDescription = "Menu",
                        accent = accent,
                        onClick = { hologramMenu = true },
                        glow = glowIntensity
                    )

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "PERSHAN",
                                color = primaryText,
                                fontSize = 29.sp,
                                letterSpacing = 4.sp
                            )

                            Spacer(Modifier.width(7.dp))

                            Icon(
                                imageVector = Icons.Default.AutoAwesome,
                                contentDescription = null,
                                tint = accent,
                                modifier = Modifier.size(18.dp)
                            )
                        }

                        Text(
                            text = if (neonAiMode) {
                                "NEON AI MODE"
                            } else {
                                "YOUR LANGUAGE UNIVERSE"
                            },
                            color = secondaryText,
                            fontSize = 10.sp,
                            letterSpacing = 1.5.sp
                        )
                    }

                    GlassCircleButton(
                        icon = if (darkMode) {
                            Icons.Default.LightMode
                        } else {
                            Icons.Default.DarkMode
                        },
                        contentDescription = "Theme",
                        accent = accent,
                        onClick = onThemeChange,
                        glow = glowIntensity
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            AnimatedVisibility(
                visible = introVisible,
                enter = fadeIn(tween(400))
            ) {
                GlassSearchBar(
                    value = searchText,
                    onValueChange = { searchText = it },
                    accent = accent,
                    primaryText = primaryText,
                    secondaryText = secondaryText,
                    glassWhite = glassWhite,
                    glassAlpha = glassAlpha
                )
            }

            Spacer(Modifier.height(16.dp))

            AnimatedContent(
                targetState = neonAiMode,
                transitionSpec = {
                    fadeIn(tween(300)) togetherWith fadeOut(tween(250))
                },
                label = "heroTransition"
            ) {
                heroMode ->
                PershanHeroCard(
                    neonAiMode = heroMode,
                    accent = accent,
                    primaryText = primaryText,
                    secondaryText = secondaryText,
                    glassWhite = glassWhite,
                    glassAlpha = glassAlpha,
                    glow = glowIntensity,
                    onClick = onTranslatorClick
                )
            }

            Spacer(Modifier.height(18.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Explore Pershan",
                    color = primaryText,
                    fontSize = 21.sp
                )

                Text(
                    text = "${filteredTools.size} tools",
                    color = secondaryText,
                    fontSize = 11.sp
                )
            }

            Spacer(Modifier.height(10.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(bottom = 18.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(filteredTools) { tool ->
                    PershanGlassToolCard(
                        tool = tool,
                        accent = accent,
                        primaryText = primaryText,
                        secondaryText = secondaryText,
                        glassWhite = glassWhite,
                        glassAlpha = glassAlpha,
                        glow = glowIntensity,
                        onClick = {
                            if (tool.title == "Translator") {
                                onTranslatorClick()
                            }
                        }
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Glass UI • Space Mode • Smooth Navigation",
                    color = secondaryText.copy(alpha = 0.7f),
                    fontSize = 10.sp
                )
            }
        }

        AnimatedVisibility(
            visible = hologramMenu,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            PershanHologramMenu(
                darkMode = darkMode,
                spaceMode = spaceMode,
                neonAiMode = neonAiMode,
                accent = accent,
                primaryText = primaryText,
                secondaryText = secondaryText,
                glassWhite = glassWhite,
                glassAlpha = glassAlpha,
                glow = glowIntensity,
                onClose = { hologramMenu = false },
                onSpaceMode = { spaceMode = !spaceMode },
                onNeonMode = { neonAiMode = !neonAiMode },
                onSettings = { settingsOpen = true }
            )
        }
    }

    if (settingsOpen) {
        PershanThemeDialog(
            accentIndex = accentIndex,
            accentColors = accentColors,
            glowIntensity = glowIntensity,
            transparency = transparency,
            darkMode = darkMode,
            onAccentChanged = { accentIndex = it },
            onGlowChanged = { glowIntensity = it },
            onTransparencyChanged = { transparency = it },
            onClose = { settingsOpen = false }
        )
    }
}

@Composable
private fun PershanStarField(
    modifier: Modifier,
    accent: Color
) {
    val stars = remember {
        List(95) {
            Star(
                x = Random.nextFloat(),
                y = Random.nextFloat(),
                radius = 0.6f + Random.nextFloat() * 1.8f,
                alpha = 0.20f + Random.nextFloat() * 0.65f,
                speed = 0.4f + Random.nextFloat() * 1.4f
            )
        }
    }

    val transition = rememberInfiniteTransition(label = "starTransition")

    val drift by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(9000),
            repeatMode = RepeatMode.Restart
        ),
        label = "starDrift"
    )

    Canvas(modifier = modifier) {
        stars.forEach { star ->
            val y = ((star.y + drift * 0.02f * star.speed) % 1f)

            drawCircle(
                color = accent.copy(alpha = star.alpha),
                radius = star.radius,
                center = androidx.compose.ui.geometry.Offset(
                    x = size.width * star.x,
                    y = size.height * y
                )
            )
        }
    }
}

@Composable
private fun GlassCircleButton(
    icon: ImageVector,
    contentDescription: String,
    accent: Color,
    onClick: () -> Unit,
    glow: Float
) {
    Box(
        modifier = Modifier
            .size(58.dp)
            .clip(CircleShape)
            .background(
                Color.White.copy(alpha = 0.065f)
            )
            .border(
                width = 1.dp,
                color = Color.White.copy(alpha = 0.17f),
                shape = CircleShape
            )
            .clickable(onClick = onClick)
            .graphicsLayer {
                shadowElevation = (10f + glow * 16f)
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = accent,
            modifier = Modifier.size(26.dp)
        )
    }
}

@Composable
private fun GlassSearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    accent: Color,
    primaryText: Color,
    secondaryText: Color,
    glassWhite: Color,
    glassAlpha: Float
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(22.dp))
            .background(glassWhite.copy(alpha = glassAlpha)),
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = accent
            )
        },
        placeholder = {
            Text(
                text = "Search anything...",
                color = secondaryText
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = accent.copy(alpha = 0.75f),
            unfocusedBorderColor = Color.White.copy(alpha = 0.12f),
            focusedTextColor = primaryText,
            unfocusedTextColor = primaryText
        ),
        shape = RoundedCornerShape(22.dp)
    )
}

@Composable
private fun PershanHeroCard(
    neonAiMode: Boolean,
    accent: Color,
    primaryText: Color,
    secondaryText: Color,
    glassWhite: Color,
    glassAlpha: Float,
    glow: Float,
    onClick: () -> Unit
) {
    val heroBrush = if (neonAiMode) {
        Brush.linearGradient(
            listOf(
                Color(0xFF5B20FF),
                Color(0xFF00D4FF),
                Color(0xFFFF2DD1)
            )
        )
    } else {
        Brush.linearGradient(
            listOf(
                accent,
                Color(0xFF8158FF),
                Color(0xFF2D8BFF)
            )
        )
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(218.dp)
            .clip(RoundedCornerShape(34.dp))
            .background(heroBrush)
            .border(
                width = 1.dp,
                color = Color.White.copy(alpha = 0.26f),
                shape = RoundedCornerShape(34.dp)
            )
            .clickable(onClick = onClick)
            .graphicsLayer {
                shadowElevation = 18f + glow * 22f
            }
            .padding(23.dp)
    ) {
        Box(
            modifier = Modifier
                .size(170.dp)
                .align(Alignment.TopEnd)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.075f))
        )

        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.14f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Language,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }

                Spacer(Modifier.width(12.dp))

                Column {
                    Text(
                        text = if (neonAiMode) {
                            "NEON AI TRANSLATOR"
                        } else {
                            "TRANSLATOR"
                        },
                        color = Color.White,
                        fontSize = 12.sp
                    )

                    Text(
                        text = "Persian ↔ English",
                        color = Color.White.copy(alpha = 0.75f),
                        fontSize = 11.sp
                    )
                }
            }

            Spacer(Modifier.height(18.dp))

            Text(
                text = "Break the language barrier.",
                color = Color.White,
                fontSize = 25.sp
            )

            Text(
                text = "Fast • Smart • Offline-first",
                color = Color.White.copy(alpha = 0.78f),
                fontSize = 13.sp
            )

            Spacer(Modifier.height(13.dp))

            Text(
                text = "OPEN  →",
                color = Color.White,
                fontSize = 11.sp
            )
        }
    }
}

@Composable
private fun PershanGlassToolCard(
    tool: PershanHomeTool,
    accent: Color,
    primaryText: Color,
    secondaryText: Color,
    glassWhite: Color,
    glassAlpha: Float,
    glow: Float,
    onClick: () -> Unit
) {
    val infinite = rememberInfiniteTransition(label = "cardMotion")

    val floatY by infinite.animateFloat(
        initialValue = 0f,
        targetValue = 2.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1800
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "floatY"
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(154.dp)
            .offset {
                IntOffset(
                    x = 0,
                    y = floatY.roundToInt()
                )
            }
            .clip(RoundedCornerShape(28.dp))
            .background(
                glassWhite.copy(alpha = glassAlpha)
            )
            .border(
                width = 1.dp,
                color = Color.White.copy(alpha = 0.11f),
                shape = RoundedCornerShape(28.dp)
            )
            .clickable(onClick = onClick)
            .graphicsLayer {
                shadowElevation = 8f + glow * 18f
                alpha = 0.96f
            }
            .padding(17.dp)
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(
                    Brush.linearGradient(
                        listOf(
                            accent.copy(alpha = 0.28f),
                            accent.copy(alpha = 0.10f)
                        )
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = tool.icon,
                contentDescription = tool.title,
                tint = accent,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(Modifier.height(11.dp))

        Text(
            text = tool.title,
            color = primaryText,
            fontSize = 16.sp
        )

        Spacer(Modifier.height(4.dp))

        Text(
            text = tool.subtitle,
            color = secondaryText,
            fontSize = 11.sp
        )
    }
}

@Composable
private fun PershanHologramMenu(
    darkMode: Boolean,
    spaceMode: Boolean,
    neonAiMode: Boolean,
    accent: Color,
    primaryText: Color,
    secondaryText: Color,
    glassWhite: Color,
    glassAlpha: Float,
    glow: Float,
    onClose: () -> Unit,
    onSpaceMode: () -> Unit,
    onNeonMode: () -> Unit,
    onSettings: () -> Unit
) {
    val panelWidth = 310.dp

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.38f))
    ) {
        Column(
            modifier = Modifier
                .width(panelWidth)
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            accent.copy(alpha = 0.10f),
                            glassWhite.copy(alpha = glassAlpha)
                        )
                    )
                )
                .border(
                    1.dp,
                    accent.copy(alpha = 0.22f)
                )
                .padding(18.dp)
                .graphicsLayer {
                    shadowElevation = 26f + glow * 30f
                }
                .offset { IntOffset(0, 0) }
                .clickable(enabled = false) {}
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "HOLOGRAM MENU",
                        color = primaryText,
                        fontSize = 17.sp
                    )

                    Text(
                        text = "Pershan control center",
                        color = secondaryText,
                        fontSize = 11.sp
                    )
                }

                IconButton(onClick = onClose) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        tint = primaryText
                    )
                }
            }

            Spacer(Modifier.height(18.dp))

            HologramMenuItem(
                title = "Space Mode",
                subtitle = if (spaceMode) "Enabled" else "Disabled",
                accent = accent,
                active = spaceMode,
                onClick = onSpaceMode
            )

            Spacer(Modifier.height(10.dp))

            HologramMenuItem(
                title = "Neon AI Mode",
                subtitle = if (neonAiMode) "Enabled" else "Disabled",
                accent = accent,
                active = neonAiMode,
                onClick = onNeonMode
            )

            Spacer(Modifier.height(10.dp))

            HologramMenuItem(
                title = "Theme Studio",
                subtitle = "Colors, glow & glass",
                accent = accent,
                active = false,
                onClick = onSettings
            )

            Spacer(Modifier.height(18.dp))

            Text(
                text = "UI STATES",
                color = secondaryText,
                fontSize = 10.sp
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = "Glass UI",
                color = primaryText,
                fontSize = 14.sp
            )

            Text(
                text = "Floating cards • floating menus • depth",
                color = secondaryText,
                fontSize = 11.sp
            )

            Spacer(Modifier.height(10.dp))

            Text(
                text = "Smooth navigation",
                color = primaryText,
                fontSize = 14.sp
            )

            Text(
                text = "Animated transitions between menus",
                color = secondaryText,
                fontSize = 11.sp
            )
        }
    }
}

@Composable
private fun HologramMenuItem(
    title: String,
    subtitle: String,
    accent: Color,
    active: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(22.dp))
            .background(
                if (active) {
                    accent.copy(alpha = 0.14f)
                } else {
                    Color.White.copy(alpha = 0.035f)
                }
            )
            .border(
                1.dp,
                accent.copy(alpha = if (active) 0.28f else 0.10f),
                RoundedCornerShape(22.dp)
            )
            .clickable(onClick = onClick)
            .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(10.dp)
                .clip(CircleShape)
                .background(
                    if (active) accent else secondaryColor(accent)
                )
        )

        Spacer(Modifier.width(12.dp))

        Column {
            Text(
                text = title,
                color = Color.White,
                fontSize = 14.sp
            )

            Text(
                text = subtitle,
                color = Color.White.copy(alpha = 0.60f),
                fontSize = 10.sp
            )
        }
    }
}

private fun secondaryColor(accent: Color): Color {
    return accent.copy(alpha = 0.45f)
}

@Composable
private fun PershanThemeDialog(
    accentIndex: Int,
    accentColors: List<Color>,
    glowIntensity: Float,
    transparency: Float,
    darkMode: Boolean,
    onAccentChanged: (Int) -> Unit,
    onGlowChanged: (Float) -> Unit,
    onTransparencyChanged: (Float) -> Unit,
    onClose: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onClose,
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Tune,
                    contentDescription = null,
                    tint = accentColors[accentIndex]
                )

                Spacer(Modifier.width(8.dp))

                Text("Theme Studio")
            }
        },
        text = {
            Column {
                Text("Accent Color", fontSize = 13.sp)

                Spacer(Modifier.height(8.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    accentColors.forEachIndexed { index, color ->
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(color)
                                .border(
                                    width = if (index == accentIndex) 3.dp else 1.dp,
                                    color = Color.White.copy(alpha = 0.8f),
                                    shape = CircleShape
                                )
                                .clickable {
                                    onAccentChanged(index)
                                }
                        )
                    }
                }

                Spacer(Modifier.height(18.dp))

                Text("UI Glow", fontSize = 13.sp)

                Slider(
                    value = glowIntensity,
                    onValueChange = onGlowChanged
                )

                Text(
                    text = "${(glowIntensity * 100).roundToInt()}%",
                    fontSize = 11.sp,
                    color = Color.Gray
                )

                Spacer(Modifier.height(12.dp))

                Text("Glass Transparency", fontSize = 13.sp)

                Slider(
                    value = transparency,
                    onValueChange = onTransparencyChanged
                )

                Text(
                    text = "${(transparency * 100).roundToInt()}%",
                    fontSize = 11.sp,
                    color = Color.Gray
                )

                Spacer(Modifier.height(16.dp))

                Text(
                    text = if (darkMode) {
                        "Dark glass theme"
                    } else {
                        "Light glass theme"
                    },
                    fontSize = 11.sp,
                    color = Color.Gray
                )
            }
        },
        confirmButton = {
            TextButton(onClick = onClose) {
                Text("Done")
            }
        },
        shape = RoundedCornerShape(28.dp)
    )
}
