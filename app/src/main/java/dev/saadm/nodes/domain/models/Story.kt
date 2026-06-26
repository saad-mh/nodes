package dev.saadm.nodes.domain.models

data class Story(
    val id: String,
    val userName: String,
    val imageUrl: String? = null,
    val unreadCount: Int = 0,
    val isUser: Boolean = false
)
