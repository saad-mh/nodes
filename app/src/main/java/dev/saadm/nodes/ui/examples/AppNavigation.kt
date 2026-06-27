package com.yourapp.ui.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.Contacts
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.*

/**
 * Composition-local handle to the OUTER NavController — the one that owns full-screen
 * "push" destinations (Settings > General, Settings > Privacy, etc). Any screen,
 * however deep inside a tab, grabs this to push a page that covers everything,
 * including the bottom nav bar.
 */
val LocalRootNavController = staticCompositionLocalOf<NavHostController> {
    error("No root NavController provided — wrap your tree in AppRoot()")
}

@Composable
fun AppRoot() {
    val rootNavController = rememberNavController()

    CompositionLocalProvider(LocalRootNavController provides rootNavController) {
        NavHost(
            navController = rootNavController,
            startDestination = "main",
            // Slide-from-right push, with a slight parallax on the screen being
            // covered — this is what makes the transition read as "cascading"
            // rather than a flat crossfade.
            enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -it / 4 }) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -it / 4 }) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { it }) }
        ) {
            // "main" is the ENTIRE tabbed experience — Scaffold + pill bar + tab
            // graph — and is the base of the stack, exactly like Telegram's root
            // fragment.
            composable("main") { MainScreen() }

            // Cascaded / full-screen pages are SIBLINGS of "main" on this same
            // outer graph, never nested inside it. Pushing one swaps "main" out
            // of composition entirely — bottom bar included — and restores it,
            // state intact, on pop.
            composable("settings/general") { GeneralSettingsScreen() }
            composable("settings/privacy") { PrivacySettingsScreen() }
            composable("settings/data") { DataSettingsScreen() }
        }
    }
}

@Composable
private fun MainScreen() {
    val tabNavController = rememberNavController()
    val rootNavController = LocalRootNavController.current

    val tabs = listOf(
        NavItem("chats", Icons.Outlined.ChatBubbleOutline, "Chats"),
        NavItem("contacts", Icons.Outlined.Contacts, "Contacts"),
        NavItem("calls", Icons.Outlined.Call, "Calls"),
        NavItem("settings", Icons.Outlined.Settings, "Settings")
    )

    val currentEntry by tabNavController.currentBackStackEntryAsState()
    val currentRoute = currentEntry?.destination?.route ?: "chats"

    Scaffold(
        bottomBar = {
            PillBottomNavBar(
                items = tabs,
                selectedRoute = currentRoute,
                onItemSelected = { route ->
                    tabNavController.navigate(route) {
                        // Pop back to start before pushing — without this, hopping
                        // Chats -> Contacts -> Calls -> Chats builds up a stack
                        // instead of behaving like real tabs.
                        popUpTo(tabNavController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true // scroll position / selection survives the hop
                    }
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = tabNavController,
            startDestination = "chats",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("chats") { ChatsScreen() }
            composable("contacts") { ContactsScreen() }
            composable("calls") { CallsScreen() }
            composable("settings") {
                SettingsScreen(
                    // Note: these call rootNavController, NOT tabNavController.
                    // That's the entire trick — it pushes onto the OUTER stack,
                    // which covers the Scaffold (and the bottom bar inside it)
                    // completely.
                    onOpenGeneral = { rootNavController.navigate("settings/general") },
                    onOpenPrivacy = { rootNavController.navigate("settings/privacy") },
                    onOpenData = { rootNavController.navigate("settings/data") }
                )
            }
        }
    }
}

// --- Placeholder screens below: swap these for your real composables. ---

@Composable private fun ChatsScreen() { /* TODO */ }
@Composable private fun ContactsScreen() { /* TODO */ }
@Composable private fun CallsScreen() { /* TODO */ }

@Composable
private fun SettingsScreen(
    onOpenGeneral: () -> Unit,
    onOpenPrivacy: () -> Unit,
    onOpenData: () -> Unit
) { /* TODO: list rows that call onOpenGeneral / onOpenPrivacy / onOpenData */ }

@Composable private fun GeneralSettingsScreen() { /* TODO: full-screen detail page */ }
@Composable private fun PrivacySettingsScreen() { /* TODO: full-screen detail page */ }
@Composable private fun DataSettingsScreen() { /* TODO: full-screen detail page */ }
