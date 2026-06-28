package dev.saadm.nodes.ui.screens.home

import dev.saadm.nodes.domain.models.Chat
import dev.saadm.nodes.domain.models.Story

data class ChatListUiState(
    val chats: List<Chat> = emptyList(),
    val stories: List<Story> = emptyList(),
    val isLoading: Boolean = false
)
