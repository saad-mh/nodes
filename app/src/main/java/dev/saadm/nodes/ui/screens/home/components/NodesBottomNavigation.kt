package dev.saadm.nodes.ui.screens.home.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mikepenz.iconics.compose.IconicsPainter
import com.mikepenz.iconics.typeface.IIcon
import com.mikepenz.iconics.typeface.library.ionicons.Ionicons
import dev.saadm.nodes.navigation.Screen
import kotlin.math.roundToInt

data class BottomNavItem(
    val route: String,
    val title: String,
    val selectedIcon: IIcon,
    val passiveIcon: IIcon,
)

@Composable
fun NodesBottomNavigation(
    selectedRoute: String,
    onItemSelected: (String) -> Unit
) {
    val items = remember {
        listOf(
            BottomNavItem(Screen.Home.route, "Chats", Ionicons.Icon.ion_chatbubbles, Ionicons.Icon.ion_ios_chatbubble_outline),
            BottomNavItem(Screen.Updates.route, "Updates", Ionicons.Icon.ion_ios_infinite, Ionicons.Icon.ion_ios_infinite_outline),
            BottomNavItem(Screen.Calls.route, "Calls", Ionicons.Icon.ion_android_apps, Ionicons.Icon.ion_ios_gear),
            BottomNavItem(Screen.Settings.route, "Settings", Ionicons.Icon.ion_ios_people, Ionicons.Icon.ion_sad)
        )
    }

    val density = LocalDensity.current
    val itemBounds = remember { mutableStateMapOf<Int, Rect>() }
    val selectedIndex = items.indexOfFirst { it.route == selectedRoute }.coerceAtLeast(0)

    val indicatorX = remember { Animatable(0f) }
    val indicatorWidth = remember { Animatable(0f) }
    val indicatorSpec = spring<Float>(dampingRatio = 0.8f, stiffness = 400f)

    LaunchedEffect(selectedIndex, itemBounds[selectedIndex]) {
        itemBounds[selectedIndex]?.let { indicatorX.animateTo(it.left, indicatorSpec) }
    }
    LaunchedEffect(selectedIndex, itemBounds[selectedIndex]) {
        itemBounds[selectedIndex]?.let { indicatorWidth.animateTo(it.width, indicatorSpec) }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .windowInsetsPadding(WindowInsets.navigationBars)
            .padding(horizontal = 24.dp, vertical = 16.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Box(
            modifier = Modifier
                .shadow(elevation = 12.dp, shape = RoundedCornerShape(36.dp))
                .height(72.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(36.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.95f))
                .padding(horizontal = 8.dp)
        ) {
            // The sliding capsule
            Box(
                Modifier
                    .offset { IntOffset(indicatorX.value.roundToInt(), 8) }
                    .width(with(density) { indicatorWidth.value.toDp() })
                    .height(56.dp)
                    .clip(RoundedCornerShape(28.dp))
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.12f))
            )

            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                items.forEachIndexed { index, item ->
                    val selected = index == selectedIndex
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .onGloballyPositioned { coords ->
                                val pos = coords.positionInParent()
                                itemBounds[index] = Rect(
                                    left = pos.x,
                                    top = pos.y,
                                    right = pos.x + coords.size.width,
                                    bottom = pos.y + coords.size.height
                                )
                            }
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) { onItemSelected(item.route) }
                            .padding(vertical = 8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        val currentIcon = if (selected) item.selectedIcon else item.passiveIcon
                        Icon(
                            painter = remember(currentIcon) { IconicsPainter(currentIcon) },
                            contentDescription = item.title,
                            modifier = Modifier.size(24.dp),
                            tint = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = item.title,
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontSize = 10.sp,
                                fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium
                            ),
                            color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}
