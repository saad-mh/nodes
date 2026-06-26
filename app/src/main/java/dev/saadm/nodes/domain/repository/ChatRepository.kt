package dev.saadm.nodes.domain.repository

import dev.saadm.nodes.domain.models.Chat
import dev.saadm.nodes.domain.models.Story

interface ChatRepository {
    fun getStories(): List<Story>
    fun getChats(): List<Chat>
}
