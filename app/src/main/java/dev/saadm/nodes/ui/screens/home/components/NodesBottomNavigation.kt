package dev.saadm.nodes.ui.screens.home.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.BlurCircular
import androidx.compose.material.icons.rounded.BlurOn
import androidx.compose.material.icons.rounded.DeviceHub
import androidx.compose.material.icons.rounded.People
import androidx.compose.material.icons.rounded.PeopleOutline
import androidx.compose.material.icons.rounded.SettingsSuggest
import androidx.compose.material.icons.rounded.Spoke
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Label
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.mikepenz.iconics.compose.IconicsPainter
import com.mikepenz.iconics.typeface.library.ionicons.Ionicons
import dev.saadm.nodes.navigation.Screen
import kotlin.math.roundToInt

data class BottomNavItem(
    val route: String,
    val title: String,
    val selectedIcon: ImageVector,
    val passiveIcon: ImageVector,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NodesBottomNavigation(
    selectedRoute: String,
    onItemSelected: (String) -> Unit,
    onPlusClick: () -> Unit = {}
) {
    val items = remember {
        listOf(
            BottomNavItem(Screen.Home.route, "Chats", Icons.Rounded.DeviceHub, Icons.Rounded.Spoke),
            BottomNavItem(Screen.Contacts.route, "Contacts", Icons.Rounded.People, Icons.Rounded.PeopleOutline),
            BottomNavItem(Screen.Updates.route, "Updates", Icons.Rounded.BlurCircular, Icons.Rounded.BlurOn),
            BottomNavItem(Screen.Settings.route, "Settings", Icons.Rounded.SettingsSuggest, Icons.Rounded.SettingsSuggest)
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
            .padding(horizontal = 16.dp, vertical = 16.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Pill Navbar
            Box(
                modifier = Modifier
                    .shadow(elevation = 8.dp, shape = RoundedCornerShape(32.dp))
                    .height(64.dp)
                    .weight(1f, fill = false)
                    .widthIn(max = 400.dp)
                    .clip(RoundedCornerShape(32.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(horizontal = 4.dp)
            ) {
                // The sliding capsule
                if (itemBounds.containsKey(selectedIndex)) {
                    Box(
                        Modifier
                            .offset { IntOffset(indicatorX.value.roundToInt(), 4) }
                            .width(with(density) { indicatorWidth.value.toDp() })
                            .height(56.dp)
                            .clip(RoundedCornerShape(28.dp))
                            .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.7f))
                    )
                }

                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    items.forEachIndexed { index, item ->
                        val selected = index == selectedIndex
                        Box(
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
                                ) { onItemSelected(item.route) },
                            contentAlignment = Alignment.Center
                        ) {
                            val currentIcon = if (selected) item.selectedIcon else item.passiveIcon
                            Column(
                                modifier = Modifier,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ){
                                Icon(
                                    imageVector = currentIcon,
                                    contentDescription = item.title,
                                    modifier = Modifier.size(24.dp),
                                    tint = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
                                )
                                Text(
                                    text = item.title,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.75f)
                                )
                            }
                        }
                    }
                }
            }

            // FAB
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .shadow(8.dp, CircleShape)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surface)
                    .clickable { onPlusClick() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = remember { IconicsPainter(Ionicons.Icon.ion_android_add) },
                    contentDescription = "Add",
                    modifier = Modifier.size(32.dp),
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}


@Preview
@Composable
fun PreviewNavBar() {
    NodesBottomNavigation(
        selectedRoute = "Chats",
        onItemSelected = {}
    )
}