package dev.saadm.nodes.ui.screens.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.saadm.nodes.domain.models.Chat
import dev.saadm.nodes.domain.models.MessageStatus

@Composable
fun ChatList(chats: List<Chat>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        if (chats.isNotEmpty()) {
            items(chats) { chat ->
                ChatItem(chat = chat)
            }
        } else {
            item {
                Box(
                    modifier = Modifier.fillParentMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    val emptyChatMessages = listOf(
                        "It's quiet. Too quiet.",
                        "A message a day keeps the silence away.",
                        "Conversations make this app feel alive. Start one!",
                        "The most important thing in communication is hearing what isn't said.",
                        "Silence is golden. Also kind of boring.",
                        "We checked twice. Still empty.",
                        "To speak kindly costs nothing and gains much."

                    )
                    Text(text = emptyChatMessages.random())
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewChatListEmpty() {
    ChatList(chats = emptyList())
}

@Preview(showBackground = true)
@Composable
fun PreviewChatList() {
    ChatList(
        listOf(
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
                unreadCount = 2
            ),
            Chat(
                id = "6",
                name = "Alex",
                lastMessage = "Why would you do that?",
                timestamp = "20min",
                status = MessageStatus.READ
            )
        )
    )
}
