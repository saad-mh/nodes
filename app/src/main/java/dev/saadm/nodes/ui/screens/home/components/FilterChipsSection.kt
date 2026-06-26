package dev.saadm.nodes.ui.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FilterChipsSection() {
    val filters = listOf("All", "Favorites", "Work", "Groups", "Community")
    val selectedFilter = "All"

    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(filters) { filter ->
            val isSelected = filter == selectedFilter
            FilterChip(filter = filter, isSelected = isSelected)
        }
    }
}

@Composable
fun FilterChip(filter: String, isSelected: Boolean) {
    Box(
        modifier = Modifier
            .padding(end = 8.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(if (isSelected) Color(0xFF4285F4) else Color(0xFFF1F3F4))
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = filter,
            color = if (isSelected) Color.White else Color.Gray,
            fontSize = 14.sp
        )
    }
}
