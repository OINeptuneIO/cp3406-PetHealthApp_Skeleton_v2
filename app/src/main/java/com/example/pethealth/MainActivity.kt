package com.example.pethealth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pethealth.navigation.BottomNav
import com.example.pethealth.screens.*
import androidx.compose.foundation.layout.padding

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { PetHealthApp() }
    }
}

@Composable
fun PetHealthApp() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNav(navController) }
    ) { inner ->
        NavHost(
            navController = navController,
            startDestination = "health",
            modifier = Modifier.padding(inner)
        ) {
            composable("health") { HealthScreen() }
            composable("diet") { DietScreen() }
            composable("exercise") { ExerciseScreen() }
            composable("reminders") { ReminderScreen() }
        }
    }
}
