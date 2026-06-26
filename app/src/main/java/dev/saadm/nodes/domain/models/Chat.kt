package dev.saadm.nodes.domain.models

data class Chat(
    val id: String,
    val name: String,
    val lastMessage: String,
    val timestamp: String,
    val imageUrl: String? = null,
    val unreadCount: Int = 0,
    val isPinned: Boolean = false,
    val isMuted: Boolean = false,
    val status: MessageStatus = MessageStatus.NONE,
    val isGroup: Boolean = false
)
