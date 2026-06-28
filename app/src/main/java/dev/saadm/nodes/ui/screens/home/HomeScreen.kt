package dev.saadm.nodes.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.saadm.nodes.core.theme.NodesTheme
import dev.saadm.nodes.core.theme.Primary
import dev.saadm.nodes.ui.screens.home.components.ChatList
import dev.saadm.nodes.ui.screens.home.components.ChatTopBar
import dev.saadm.nodes.ui.screens.home.components.StoriesSection

@Composable
fun HomeScreen(
    viewModel: ChatListViewModel = viewModel(),
    onSettingsClick: () -> Unit = {},
    onChatClick: (String) -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = { ChatTopBar(onSettingsClick = onSettingsClick) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* New Chat */ },
                containerColor = Primary,
                contentColor = Color.White,
                shape = androidx.compose.foundation.shape.CircleShape,
                modifier = Modifier.size(56.dp)
            ) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "New Message")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            StoriesSection(stories = uiState.stories)
            ChatList(
                chats = uiState.chats,
                onChatClick = { chat -> onChatClick(chat.id) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenLightPreview() {
    NodesTheme(darkTheme = false) {
        HomeScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenDarkPreview() {
    NodesTheme(darkTheme = true) {
        HomeScreen()
    }
}
