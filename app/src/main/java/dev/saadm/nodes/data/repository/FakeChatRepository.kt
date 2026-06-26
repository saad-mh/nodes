package dev.saadm.nodes.data.repository

import dev.saadm.nodes.domain.models.Chat
import dev.saadm.nodes.domain.models.MessageStatus
import dev.saadm.nodes.domain.models.Story
import dev.saadm.nodes.domain.repository.ChatRepository

class FakeChatRepository : ChatRepository {
    override fun getStories(): List<Story> {
        return listOf(
            Story(id = "1", userName = "You", isUser = true),
            Story(id = "2", userName = "Lisa", unreadCount = 3),
            Story(id = "3", userName = "Nya", unreadCount = 1),
            Story(id = "4", userName = "Lucas", unreadCount = 2),
            Story(id = "5", userName = "Joe"),
            Story(id = "6", userName = "Emma"),
            Story(id = "7", userName = "Alex")
        )
    }

    override fun getChats(): List<Chat> {
        return listOf(
            Chat(
                id = "1",
                name = "Nick P.",
                lastMessage = "Yeah, Sunday will be great",
                timestamp = "20min",
                unreadCount = 3,
                isPinned = true
            ),
            Chat(
                id = "2",
                name = "Project 1 Chat",
                lastMessage = "You: I've to take another look before...",
                timestamp = "2min",
                isPinned = true,
                isGroup = true
            ),
            Chat(
                id = "3",
                name = "Noah K.",
                lastMessage = "Thanks Noah, see ya later",
                timestamp = "12min",
                isMuted = true,
                status = MessageStatus.READ
            ),
            Chat(
                id = "4",
                name = "Maya P. Lisa K. Nya R.",
                lastMessage = "Lisa: Sure hahaha",
                timestamp = "15min",
                isGroup = true
            ),
            Chat(
                id = "5",
                name = "Sofia B.",
                lastMessage = "I'll be there in 5 min",
                timestamp = "18min",
                unreadCount = 1
            ),
            Chat(
                id = "6",
                name = "Malik Z.",
                lastMessage = "Why would you do that?",
                timestamp = "20min",
                status = MessageStatus.READ
            )
        )
    }
}
