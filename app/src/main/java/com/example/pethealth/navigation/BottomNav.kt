package com.example.pethealth.navigation

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*

data class NavItem(val label: String, val route: String, val icon: ImageVector)

@Composable
fun BottomNav(navController: NavController) {
    val items = listOf(
        NavItem("Health", "health", Icons.Filled.Favorite),
        NavItem("Diet", "diet", Icons.Filled.Restaurant),
        NavItem("Exercise", "exercise", Icons.Filled.FitnessCenter),
        NavItem("Reminders", "reminders", Icons.Filled.Notifications)
    )
    NavigationBar {
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = { navController.navigate(item.route) },
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) }
            )
        }
    }
}
