package dev.saadm.nodes.ui.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.saadm.nodes.core.theme.Primary

@Composable
fun UnreadBadge(
    count: Int,
    isMuted: Boolean = false,
    modifier: Modifier = Modifier
) {
    if (count <= 0) return

    val backgroundColor = if (isMuted) Color.LightGray else Primary
    val textColor = if (isMuted) Color.Gray else Color.White

    Box(
        modifier = modifier
            .sizeIn(minWidth = 20.dp, minHeight = 20.dp)
            .clip(CircleShape)
            .background(backgroundColor)
            .padding(horizontal = 6.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = count.toString(),
            color = textColor,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
