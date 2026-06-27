package dev.saadm.nodes.ui.screens.home.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.saadm.nodes.domain.models.Story

@Composable
fun StoriesSection(stories: List<Story>) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
    ) {
        if (stories.isNotEmpty()) {
            items(stories) { story ->
                StoryItem(story = story)
            }
        }
    }
}

@Preview
@Composable
fun PreviewStoriesSectionFull() {
    StoriesSection(
        listOf(
        Story(id = "1", userName = "Khushi", unreadCount = 3),
        Story(id = "2", userName = "Nya", unreadCount = 1),
        Story(id = "3", userName = "Midas", unreadCount = 2),
        Story(id = "4", userName = "Fortnite"),
        Story(id = "5", userName = "Mango"),
        Story(id = "6", userName = "Yeah")
    ))
}

@Preview
@Composable
fun PreviewStoriesSectionEmpty() {
    StoriesSection(listOf())
}
