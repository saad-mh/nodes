package dev.saadm.nodes.ui

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import dev.saadm.nodes.ui.screens.home.HomeScreen
import dev.saadm.nodes.ui.screens.splash.SplashScreen

enum class Screen {
    Splash, Home
}

@Composable
fun NodesApp() {
    var currentScreen by remember { mutableStateOf(Screen.Splash) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Crossfade(
            targetState = currentScreen,
            animationSpec = tween(durationMillis = 500),
            label = "screen_transition"
        ) { screen ->
            when (screen) {
                Screen.Splash -> SplashScreen { currentScreen = Screen.Home }
                Screen.Home -> HomeScreen()
            }
        }
    }
}
