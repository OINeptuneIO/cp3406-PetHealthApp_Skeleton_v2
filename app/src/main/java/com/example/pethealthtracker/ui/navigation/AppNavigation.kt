package com.example.pethealthtracker.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.pethealthtracker.ui.screens.HomeScreen
import com.example.pethealthtracker.ui.screens.HealthScreen
import com.example.pethealthtracker.ui.screens.DietScreen
import com.example.pethealthtracker.ui.screens.ExerciseScreen
import com.example.pethealthtracker.ui.screens.RemindersScreen

sealed class NavRoute(val route: String, val title: String) {
    object Home : NavRoute("home", "Home")
    object Health : NavRoute("health", "Health")
    object Diet : NavRoute("diet", "Diet")
    object Exercise : NavRoute("exercise", "Exercise")
    object Reminders : NavRoute("reminders", "Reminder")
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = NavRoute.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(NavRoute.Home.route) {
                HomeScreen(navController)
            }
            composable(NavRoute.Health.route) {
                HealthScreen(navController)
            }
            composable(NavRoute.Diet.route) {
                DietScreen(navController)
            }
            composable(NavRoute.Exercise.route) {
                ExerciseScreen(navController)
            }
            composable(NavRoute.Reminders.route) {
                RemindersScreen(navController)
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        NavRoute.Home,
        NavRoute.Health,
        NavRoute.Diet,
        NavRoute.Exercise,
        NavRoute.Reminders
    )

    val icons = listOf(
        Icons.Default.Favorite,
        Icons.Default.Favorite,
        Icons.Default.Restaurant,
        Icons.Default.FitnessCenter,
        Icons.Default.Notifications
    )

    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        items.forEachIndexed { index, route ->
            NavigationBarItem(
                icon = { Icon(icons[index], contentDescription = route.title) },
                label = { Text(route.title) },
                selected = currentDestination?.hierarchy?.any { it.route == route.route } == true,
                onClick = {
                    navController.navigate(route.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}
