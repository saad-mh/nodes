package dev.saadm.nodes.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.saadm.nodes.navigation.AppNavHost
import dev.saadm.nodes.navigation.Screen
import dev.saadm.nodes.ui.screens.contacts.ContactsScreen
import dev.saadm.nodes.ui.screens.home.components.NodesBottomNavigation
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NodesApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    var showContactsSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()

    // Define which routes should show the bottom bar
    val showBottomBar = currentRoute in listOf(
        Screen.Home.route,
        Screen.Contacts.route,
        Screen.Updates.route,
        Screen.Calls.route,
        Screen.Settings.route,
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            bottomBar = {
                if (showBottomBar) {
                    NodesBottomNavigation(
                        selectedRoute = currentRoute ?: Screen.Home.route,
                        onItemSelected = { route ->
                            if (currentRoute != route) {
                                navController.navigate(route) {
                                    popUpTo(Screen.Home.route) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        },
                        onPlusClick = {
                            showContactsSheet = true
                        }
                    )
                }
            }
        ) { innerPadding ->
            AppNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding)
            )

            if (showContactsSheet) {
                ModalBottomSheet(
                    onDismissRequest = { showContactsSheet = false },
                    sheetState = sheetState,
                    dragHandle = { BottomSheetDefaults.DragHandle() },
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.onSurface,
                ) {
                    ContactsScreen(
                        onBackClick = {
                            scope.launch { sheetState.hide() }.invokeOnCompletion {
                                if (!sheetState.isVisible) {
                                    showContactsSheet = false
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}
