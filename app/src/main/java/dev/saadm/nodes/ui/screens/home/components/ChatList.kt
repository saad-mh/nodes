package dev.saadm.nodes.ui.screens.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.saadm.nodes.domain.models.Chat

@Composable
fun ChatList(
    chats: List<Chat>,
    onChatClick: (Chat) -> Unit,
    modifier: Modifier = Modifier
) {
    if (chats.isEmpty()) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No chats yet",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize()
        ) {
            items(chats, key = { it.id }) { chat ->
                ChatItem(
                    chat = chat,
                    onClick = { onChatClick(chat) },
                    onLongClick = { /* Handle long click if needed */ }
                )
            }
        }
    }
}
