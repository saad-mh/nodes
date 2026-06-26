package dev.saadm.nodes.ui.screens.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun SplashScreen(onTimeout: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Nodes",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(
                modifier = Modifier.height(8.dp)
            )
            Text(
                text = "is connecting",
                style = MaterialTheme.typography.bodyMedium,
            )
            Spacer(
                modifier = Modifier.height(16.dp)
            )
            ProgressBar(onComplete = onTimeout)
        }
    }
}

@Composable
fun ProgressBar(onComplete: () -> Unit) {
    val progress = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        val random = kotlin.random.Random(System.currentTimeMillis())

        val checkpoints = List(5) {
            random.nextFloat() * 0.15f + 0.08f // 8-23% increments
        }

        var current = 0f

        checkpoints.forEachIndexed { index, increment ->
            current = (current + increment).coerceAtMost(0.9f)

            progress.animateTo(
                targetValue = current,
                animationSpec = tween(
                    durationMillis = random.nextInt(180, 420),
                    easing = FastOutSlowInEasing
                )
            )

            if (index != checkpoints.lastIndex) {
                delay(random.nextLong(120, 350).milliseconds)
            }
        }

        progress.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 400,
                easing = FastOutSlowInEasing
            )
        )

        onComplete()
    }

    LinearProgressIndicator(
        progress = { progress.value },
        modifier = Modifier
            .fillMaxWidth(0.45f)
            .height(6.dp),
        strokeCap = StrokeCap.Round,
        color = MaterialTheme.colorScheme.primary,
        trackColor = MaterialTheme.colorScheme.surfaceVariant,
    )
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewSplashScreen() {
    SplashScreen()
}