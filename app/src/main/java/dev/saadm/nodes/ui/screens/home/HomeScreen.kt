package dev.saadm.nodes.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.saadm.nodes.data.repository.FakeChatRepository
import dev.saadm.nodes.domain.repository.ChatRepository
import dev.saadm.nodes.ui.screens.home.components.ChatList
import dev.saadm.nodes.ui.screens.home.components.ChatTopBar
import dev.saadm.nodes.ui.screens.home.components.FilterChipsSection
import dev.saadm.nodes.ui.screens.home.components.StoriesSection

@Composable
fun HomeScreen(
    repository: ChatRepository = FakeChatRepository(),
    onSettingsClick: () -> Unit = {}
) {
    val stories = remember { repository.getStories() }
    val chats = remember { repository.getChats() }

    Scaffold(
        topBar = { ChatTopBar(onSettingsClick = onSettingsClick) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* TODO */ },
                containerColor = Color.White,
                contentColor = Color.Black,
                shape = CircleShape,
                modifier = Modifier
                    .size(56.dp)
                    .padding(bottom = 0.dp) // Adjust based on bottom nav
            ) {
                Icon(Icons.Default.Add, contentDescription = "New Chat")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            StoriesSection(stories = stories)
            FilterChipsSection()
            ChatList(chats = chats)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
