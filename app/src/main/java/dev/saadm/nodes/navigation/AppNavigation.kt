package dev.saadm.nodes.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.saadm.nodes.ui.screens.home.HomeScreen
import dev.saadm.nodes.ui.screens.settings.GeneralSettingsScreen
import dev.saadm.nodes.ui.screens.settings.SettingsScreen
import dev.saadm.nodes.ui.screens.splash.SplashScreen

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
    object Contacts : Screen("contacts")
    object Updates : Screen("updates")
    object Calls : Screen("calls")
    object Profile : Screen("profile")
    object Settings : Screen("settings")
    object GeneralSettings : Screen("general_settings")
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
        modifier = modifier,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None }
    ) {
        composable(Screen.Splash.route) {
            SplashScreen {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Splash.route) { inclusive = true }
                }
            }
        }
        composable(Screen.Home.route) {
            HomeScreen(onSettingsClick = {
                navController.navigate(Screen.Settings.route)
            })
        }
        composable(Screen.Contacts.route) {
            dev.saadm.nodes.ui.screens.contacts.ContactsScreen(onBackClick = { navController.popBackStack() })
        }
        composable(Screen.Updates.route) {
            PlaceholderScreen("Updates")
        }
        composable(Screen.Calls.route) {
            PlaceholderScreen("Calls")
        }
        composable(Screen.Profile.route) {
            PlaceholderScreen("Profile")
        }
        composable(Screen.Settings.route) {
            SettingsScreen(
                onBackClick = { navController.popBackStack() },
                onGeneralSettingsClick = { navController.navigate(Screen.GeneralSettings.route) }
            )
        }
        composable(Screen.GeneralSettings.route) {
            GeneralSettingsScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}

@Composable
fun PlaceholderScreen(name: String) {
    androidx.compose.foundation.layout.Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        androidx.compose.material3.Text(text = "$name Screen")
    }
}
