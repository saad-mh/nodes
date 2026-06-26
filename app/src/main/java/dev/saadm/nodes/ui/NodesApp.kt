package dev.saadm.nodes.ui

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import dev.saadm.nodes.ui.screens.home.HomeScreen
import dev.saadm.nodes.ui.screens.splash.SplashScreen

enum class Screen {
    Splash, Home
}

@Composable
fun NodesApp() {
    var currentScreen by remember { mutableStateOf(Screen.Splash) }

    Crossfade(
        targetState = currentScreen,
        animationSpec = tween(durationMillis = 1000),
        label = "screen_transition"
    ) { screen ->
        when (screen) {
            Screen.Splash -> SplashScreen { currentScreen = Screen.Home }
            Screen.Home -> HomeScreen()
        }
    }
}
