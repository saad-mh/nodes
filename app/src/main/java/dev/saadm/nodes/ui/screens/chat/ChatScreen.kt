package dev.saadm.nodes.ui.screens.chat

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.saadm.nodes.domain.models.Chat
import java.time.Clock.system
import java.time.LocalDateTime

@Composable
fun ChatScreen(
    chat: Chat
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
        ) {
            Column{

            }
        }
    }
}

@Composable
fun ChatHeader(
    chat: Chat
) {

}


@Preview
@Composable
fun PreviewChatScreen() {
    ChatScreen(
        Chat(
            id = "1",
            name = "Other Person",
            lastMessage = "Yeah this works",
            timestamp = LocalDateTime.now().toString()
        )
    )
}