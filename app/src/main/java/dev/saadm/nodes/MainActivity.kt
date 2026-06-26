package dev.saadm.nodes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.saadm.nodes.core.theme.NodesTheme
import dev.saadm.nodes.ui.NodesApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NodesTheme {
                NodesApp()
            }
        }
    }
}

@Preview
@Composable
fun MainActivityPreview() {
    NodesTheme {
        NodesApp()
    }
}
