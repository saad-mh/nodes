package com.yourapp.ui.navigation

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

data class NavItem(
    val route: String,
    val icon: ImageVector,
    val label: String
)

/**
 * Telegram-style pill bottom nav bar.
 *
 * The two things that make this read as "Telegram" instead of a generic Material bar:
 *
 * 1. Every item gets EQUAL width via Modifier.weight(1f) on the row container.
 *    Telegram never lets icons clump toward the center on wide screens — spacing
 *    stays proportional to screen width, edges match inter-item gaps.
 *
 * 2. The selection capsule is measured, not guessed. It slides + resizes to hug
 *    whatever the selected item's ACTUAL rendered bounds are (icon + label,
 *    whatever that width happens to be), via onGloballyPositioned. A fixed-width
 *    pill or a border is what makes hand-built versions look "bogus" — real
 *    Telegram pills are always exactly as wide as their content.
 */
@Composable
fun PillBottomNavBar(
    items: List<NavItem>,
    selectedRoute: String,
    onItemSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val density = LocalDensity.current
    val itemBounds = remember { mutableStateMapOf<Int, Rect>() }
    val selectedIndex = items.indexOfFirst { it.route == selectedRoute }.coerceAtLeast(0)

    val indicatorX = remember { Animatable(0f) }
    val indicatorWidth = remember { Animatable(0f) }
    val indicatorSpec = spring<Float>(dampingRatio = 0.7f, stiffness = 400f)

    // Two independent LaunchedEffects so offset and width animate concurrently,
    // not sequentially — this is what gives the "snap" feel instead of a wobble.
    LaunchedEffect(selectedIndex, itemBounds[selectedIndex]) {
        itemBounds[selectedIndex]?.let { indicatorX.animateTo(it.left, indicatorSpec) }
    }
    LaunchedEffect(selectedIndex, itemBounds[selectedIndex]) {
        itemBounds[selectedIndex]?.let { indicatorWidth.animateTo(it.width, indicatorSpec) }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            // Pad for the gesture/nav bar inset BEFORE sizing the content —
            // forgetting this is the #1 cause of the bar looking "cramped"
            // compared to Telegram's bottom breathing room.
            .windowInsetsPadding(WindowInsets.navigationBars)
            .height(64.dp)
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        // The sliding capsule sits BEHIND the row, positioned in px from measured bounds.
        Box(
            Modifier
                .offset { IntOffset(indicatorX.value.roundToInt(), 0) }
                .width(with(density) { indicatorWidth.value.toDp() })
                .fillMaxHeight()
                .clip(RoundedCornerShape(50)) // 50% = true pill, half of the box height
                .background(MaterialTheme.colorScheme.primaryContainer)
        )

        Row(modifier = Modifier.fillMaxSize()) {
            items.forEachIndexed { index, item ->
                val selected = index == selectedIndex
                Row(
                    modifier = Modifier
                        .weight(1f) // <- equal-width distribution, the spacing fix
                        .onGloballyPositioned { coords ->
                            val pos = coords.positionInParent()
                            itemBounds[index] = Rect(
                                left = pos.x,
                                top = pos.y,
                                right = pos.x + coords.size.width,
                                bottom = pos.y + coords.size.height
                            )
                        }
                        .clip(RoundedCornerShape(50))
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null // Telegram has no ripple here — instant state change
                        ) { onItemSelected(item.route) }
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(item.icon, contentDescription = item.label, modifier = Modifier.size(22.dp))
                    AnimatedVisibility(visible = selected) {
                        Row {
                            Spacer(Modifier.width(6.dp))
                            Text(item.label, style = MaterialTheme.typography.labelMedium)
                        }
                    }
                }
            }
        }
    }
}
