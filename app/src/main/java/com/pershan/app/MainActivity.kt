package com.pershan.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Button
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

data class PershanFeature(
    val title: String,
    val description: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)

@Composable
fun PershanApp() {

    var darkMode by remember { mutableStateOf(true) }
    var selectedFeature by remember { mutableStateOf<String?>(null) }

    val backgroundTop = if (darkMode) {
        Color(0xFF050817)
    } else {
        Color(0xFFF5F7FF)
    }

    val backgroundBottom = if (darkMode) {
        Color(0xFF111B3A)
    } else {
        Color(0xFFE8ECFA)
    }

    val textColor = if (darkMode) {
        Color.White
    } else {
        Color(0xFF11131A)
    }

    val secondaryText = if (darkMode) {
        Color(0xFFB9C2DD)
    } else {
        Color(0xFF5D6475)
    }

    val cardColor = if (darkMode) {
        Color(0xFF151D35)
    } else {
        Color.White
    }

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
                        fontSize = 30.sp,
                        fontWeight = FontWeight.ExtraBold
                    )

                    Text(
                        text = "Your offline language ecosystem",
                        color = secondaryText,
                        fontSize = 14.sp
                    )
                }

                Button(
                    onClick = {
                        darkMode = !darkMode
                    }
                ) {
                    Text(
                        text = if (darkMode) "☀️" else "🌙"
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
                    containerColor = cardColor.copy(alpha = 0.94f)
                )
            ) {

                Column(
                    modifier = Modifier.padding(22.dp)
                ) {

                    Text(
                        text = "🌌 Welcome to Pershan",
                        color = textColor,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text(
                        text = "Translate, read, learn and understand your world — with powerful offline tools.",
                        color = secondaryText,
                        fontSize = 15.sp
                    )

                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )

                    OutlinedButton(
                        onClick = {
                            selectedFeature = "AI Assistant"
                        }
                    ) {
                        Text("🤖 Open AI Assistant")
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(22.dp)
            )

            Text(
                text = "Pershan Tools",
                color = textColor,
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold
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
                            selectedFeature = feature.title
                        }
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(
                text = "Pershan • Offline First",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                color = secondaryText,
                fontSize = 12.sp
            )
        }

        if (selectedFeature != null) {

            FeatureDialog(
                featureName = selectedFeature!!,
                textColor = textColor,
                secondaryText = secondaryText,
                onClose = {
                    selectedFeature = null
                }
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
            containerColor = cardColor.copy(alpha = 0.95f)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
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
                    tint = Color(0xFF8EA0FF),
                    modifier = Modifier.size(27.dp)
                )
            }

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(
                text = feature.title,
                color = textColor,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(
                text = feature.description,
                color = secondaryText,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun FeatureDialog(
    featureName: String,
    textColor: Color,
    secondaryText: Color,
    onClose: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Color.Black.copy(alpha = 0.65f)
            ),
        contentAlignment = Alignment.Center
    ) {

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(28.dp),
            shape = RoundedCornerShape(28.dp),
            color = Color(0xFF151D35)
        ) {

            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "✨ $featureName",
                    color = textColor,
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Text(
                    text = "This Pershan module is ready to be connected to its real engine.",
                    color = secondaryText,
                    fontSize = 14.sp
                )

                Spacer(
                    modifier = Modifier.height(20.dp)
                )

                Button(
                    onClick = onClose
                ) {
                    Text("Close")
                }
            }
        }
    }
}
