package dev.saadm.nodes.ui.screens.home.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.Group
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun NodesBottomNavigation() {
    val items = listOf(
        BottomNavItem("Chats", Icons.Filled.ChatBubbleOutline),
        BottomNavItem("Call", Icons.Outlined.Phone),
        BottomNavItem("Updates", Icons.Outlined.Group),
        BottomNavItem("Profile", Icons.Outlined.PersonOutline)
    )

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                selected = item.title == "Chats",
                onClick = { /* TODO */ },
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(item.title) }
            )
        }
    }
}

data class BottomNavItem(
    val title: String,
    val icon: ImageVector
)
