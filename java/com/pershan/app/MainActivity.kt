package com.pershan.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.Composable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PershanApp()
        }
    }
}

/*
 * ============================================================
 * PERSHAN APP
 * پیام ۱ از ۱۰
 * هسته اصلی برنامه + Home + Navigation پایه
 * ============================================================
 */

@Composable
fun PershanApp() {

    var darkMode by rememberSaveable {
        mutableStateOf(true)
    }

    var currentScreen by rememberSaveable {
        mutableStateOf(PershanScreen.HOME.name)
    }

    MaterialTheme {

        Surface {

            when (
                PershanScreen.valueOf(currentScreen)
            ) {

                PershanScreen.HOME -> {

                    PershanSpaceHome(
                        darkMode = darkMode,

                        onThemeChange = {
                            darkMode = !darkMode
                        },

                        onTranslatorClick = {
                            currentScreen =
                                PershanScreen.TRANSLATOR.name
                        }
                    )
                }

                PershanScreen.TRANSLATOR -> {

                    TranslatorPlaceholder(
                        onBack = {
                            currentScreen =
                                PershanScreen.HOME.name
                        }
                    )
                }

                PershanScreen.LENS -> {

                    FeaturePlaceholder(
                        title = "Lens",
                        onBack = {
                            currentScreen =
                                PershanScreen.HOME.name
                        }
                    )
                }

                PershanScreen.READER -> {

                    FeaturePlaceholder(
                        title = "Reader",
                        onBack = {
                            currentScreen =
                                PershanScreen.HOME.name
                        }
                    )
                }

                PershanScreen.VOICE -> {

                    FeaturePlaceholder(
                        title = "Voice",
                        onBack = {
                            currentScreen =
                                PershanScreen.HOME.name
                        }
                    )
                }

                PershanScreen.STUDY -> {

                    FeaturePlaceholder(
                        title = "Study",
                        onBack = {
                            currentScreen =
                                PershanScreen.HOME.name
                        }
                    )
                }

                PershanScreen.GAME_MODE -> {

                    FeaturePlaceholder(
                        title = "Game Mode",
                        onBack = {
                            currentScreen =
                                PershanScreen.HOME.name
                        }
                    )
                }

                PershanScreen.SETTINGS -> {

                    FeaturePlaceholder(
                        title = "Settings",
                        onBack = {
                            currentScreen =
                                PershanScreen.HOME.name
                        }
                    )
                }
            }
        }
    }
}

/*
 * ============================================================
 * SCREEN LIST
 * ============================================================
 */

enum class PershanScreen {

    HOME,

    TRANSLATOR,

    LENS,

    READER,

    VOICE,

    STUDY,

    GAME_MODE,

    SETTINGS
}

/*
 * ============================================================
 * TRANSLATOR PLACEHOLDER
 *
 * فعلاً فقط جای صفحه را مشخص می‌کند.
 * در پیام‌های بعدی تبدیل به Translator واقعی می‌شود.
 * ============================================================
 */

@Composable
private fun TranslatorPlaceholder(
    onBack: () -> Unit
) {

    androidx.compose.foundation.layout.Column(
        modifier =
            androidx.compose.ui.Modifier
                .fillMaxSize()
                .padding(24.dp)
    ) {

        androidx.compose.material3.Text(
            text = "Translator",
            style =
                MaterialTheme.typography.headlineLarge
        )

        androidx.compose.foundation.layout.Spacer(
            modifier =
                androidx.compose.ui.Modifier
                    .height(20.dp)
        )

        androidx.compose.material3.Text(
            text =
                "Translator module will be connected here."
        )

        androidx.compose.foundation.layout.Spacer(
            modifier =
                androidx.compose.ui.Modifier
                    .height(30.dp)
        )

        androidx.compose.material3.Button(
            onClick = onBack
        ) {

            androidx.compose.material3.Text(
                text = "Back"
            )
        }
    }
}

/*
 * ============================================================
 * GENERIC FEATURE PLACEHOLDER
 * ============================================================
 */

@Composable
private fun FeaturePlaceholder(
    title: String,
    onBack: () -> Unit
) {

    androidx.compose.foundation.layout.Column(
        modifier =
            androidx.compose.ui.Modifier
                .fillMaxSize()
                .padding(24.dp)
    ) {

        androidx.compose.material3.Text(
            text = title,
            style =
                MaterialTheme.typography.headlineLarge
        )

        androidx.compose.foundation.layout.Spacer(
            modifier =
                androidx.compose.ui.Modifier
                    .height(20.dp)
        )

        androidx.compose.material3.Text(
            text =
                "$title module will be connected here."
        )

        androidx.compose.foundation.layout.Spacer(
            modifier =
                androidx.compose.ui.Modifier
                    .height(30.dp)
        )

        androidx.compose.material3.Button(
            onClick = onBack
        ) {

            androidx.compose.material3.Text(
                text = "Back"
            )
        }
    }
}
