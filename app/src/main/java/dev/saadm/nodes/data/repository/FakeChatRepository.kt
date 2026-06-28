package dev.saadm.nodes.data.repository

import dev.saadm.nodes.domain.models.Chat
import dev.saadm.nodes.domain.models.MessageStatus
import dev.saadm.nodes.domain.models.Story
import dev.saadm.nodes.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import java.util.Calendar
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class FakeChatRepository : ChatRepository {
    private val _chats = MutableStateFlow(createInitialChats())
    private val _stories = MutableStateFlow(createInitialStories())

    override fun getStories(): List<Story> = _stories.value
    override fun getChats(): List<Chat> = _chats.value

    fun getChatsFlow(): Flow<List<Chat>> = _chats.map { chats ->
        chats.sortedWith(compareByDescending<Chat> { it.isPinned }.thenByDescending { parseTimestamp(it.timestamp) })
    }

    fun getStoriesFlow(): Flow<List<Story>> = _stories.map { stories ->
        stories.sortedByDescending { it.isUser }
    }

    private fun parseTimestamp(timestamp: String): Long {
        // Simplified parsing for fake data
        return when {
            timestamp == "now" -> System.currentTimeMillis()
            timestamp.endsWith("m") -> System.currentTimeMillis() - timestamp.dropLast(1).toLong() * 60 * 1000
            timestamp.endsWith("h") -> System.currentTimeMillis() - timestamp.dropLast(1).toLong() * 60 * 60 * 1000
            else -> 0L // Fallback for formatted dates
        }
    }

    private fun createInitialStories(): List<Story> {
        return listOf(
            Story(id = "user", userName = "My Story", isUser = true, unreadCount = 0),
            Story(id = "1", userName = "Khushi", unreadCount = 3),
            Story(id = "2", userName = "Nya", unreadCount = 1),
            Story(id = "3", userName = "Midas", unreadCount = 0),
            Story(id = "4", userName = "Fortnite", unreadCount = 5),
            Story(id = "5", userName = "Mango", unreadCount = 0),
            Story(id = "6", userName = "Alex", unreadCount = 2),
            Story(id = "7", userName = "Sarah", unreadCount = 1)
        )
    }

    private fun createInitialChats(): List<Chat> {
        val now = System.currentTimeMillis()
        val calendar = Calendar.getInstance()

        return listOf(
            Chat(
                id = "1",
                name = "Khushi",
                lastMessage = "Yeah, Sunday will be great! Let's meet at 5.",
                timestamp = "2m",
                unreadCount = 3,
                isPinned = true
            ),
            Chat(
                id = "2",
                name = "Nodes Dev Team",
                lastMessage = "Saad: I've pushed the latest changes to the repository.",
                timestamp = "15m",
                isPinned = true,
                isGroup = true,
                unreadCount = 47
            ),
            Chat(
                id = "3",
                name = "Midas",
                lastMessage = "The golden touch is ready.",
                timestamp = "now",
                status = MessageStatus.READ
            ),
            Chat(
                id = "4",
                name = "Fortnite Group",
                lastMessage = "Mango: Anyone for a game?",
                timestamp = "1h",
                isGroup = true,
                isMuted = true,
                unreadCount = 5
            ),
            Chat(
                id = "5",
                name = "Alex",
                lastMessage = "Did you see the new trailer?",
                timestamp = "Yesterday",
                status = MessageStatus.DELIVERED
            ),
            Chat(
                id = "6",
                name = "Sarah Jenkins",
                lastMessage = "I'll be there in 5 min, just parking the car now.",
                timestamp = "14:20",
                unreadCount = 1
            ),
            Chat(
                id = "7",
                name = "A very long name that should definitely be truncated in the UI to see how it handles overflow",
                lastMessage = "This is a very long message that should also be truncated to see how the ellipsis works in the middle column of the chat item.",
                timestamp = "Tue",
                status = MessageStatus.SENT
            ),
            Chat(
                id = "8",
                name = "Work Chat",
                lastMessage = "Boss: Deadline is tomorrow.",
                timestamp = "Mon",
                isGroup = true,
                isMuted = true
            ),
            Chat(
                id = "9",
                name = "Mom",
                lastMessage = "Call me when you're free.",
                timestamp = "05/06/24",
                status = MessageStatus.READ
            ),
            Chat(
                id = "10",
                name = "John Doe",
                lastMessage = "See you soon!",
                timestamp = "22/05/24",
                status = MessageStatus.READ
            )
        ).plus((11..20).map { i ->
            Chat(
                id = "$i",
                name = "Contact $i",
                lastMessage = "Random message $i",
                timestamp = "${i}m",
                status = if (i % 2 == 0) MessageStatus.READ else MessageStatus.NONE
            )
        })
    }
}
