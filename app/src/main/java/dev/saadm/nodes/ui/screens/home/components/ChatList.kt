package dev.saadm.nodes.ui.screens.home.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.saadm.nodes.domain.models.Chat

@Composable
fun ChatList(chats: List<Chat>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(chats) { chat ->
            ChatItem(chat = chat)
        }
    }
}
