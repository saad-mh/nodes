package dev.saadm.nodes.data.repository

import dev.saadm.nodes.domain.models.Chat
import dev.saadm.nodes.domain.models.MessageStatus
import dev.saadm.nodes.domain.models.Story
import dev.saadm.nodes.domain.repository.ChatRepository

class FakeChatRepository : ChatRepository {
    override fun getStories(): List<Story> {
        return listOf(
            Story(id = "1", userName = "Khushi", unreadCount = 3),
            Story(id = "2", userName = "Nya", unreadCount = 1),
            Story(id = "3", userName = "Midas", unreadCount = 2),
            Story(id = "4", userName = "Fortnite"),
            Story(id = "5", userName = "Mango"),
            Story(id = "6", userName = "Yeah")
        )
    }

    override fun getChats(): List<Chat> {
        return listOf(
            Chat(
                id = "1",
                name = "Khushi",
                lastMessage = "Yeah, Sunday will be great",
                timestamp = "20min",
                unreadCount = 3,
                isPinned = true
            ),
            Chat(
                id = "2",
                name = "Nya",
                lastMessage = "You: I'll check the design now, as it seems to have happened that the last text overflowed.",
                timestamp = "2min",
                isPinned = true,
                isMuted = true,
                isGroup = true
            ),
            Chat(
                id = "3",
                name = "Midas",
                lastMessage = "Thanks Midas, see ya later",
                timestamp = "12min",
                isMuted = true,
                status = MessageStatus.READ
            ),
            Chat(
                id = "4",
                name = "Fortnite Group",
                lastMessage = "Mango: Sure hahaha",
                timestamp = "15min",
                isGroup = true
            ),
            Chat(
                id = "5",
                name = "No",
                lastMessage = "I'll be there in 5 min",
                timestamp = "18min",
                unreadCount = 1
            ),
            Chat(
                id = "6",
                name = "Alex",
                lastMessage = "Why would you do that?",
                timestamp = "20min",
                status = MessageStatus.READ
            )
        )
    }
}
