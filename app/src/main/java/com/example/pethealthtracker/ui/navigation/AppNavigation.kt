package com.example.pethealthtracker.ui.navigation

import android.media.MediaPlayer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.pethealthtracker.data.AppDatabase
import com.example.pethealthtracker.data.entity.Reminder
import com.example.pethealthtracker.data.repository.PetRepository
import com.example.pethealthtracker.data.repository.ReminderRepository
import com.example.pethealthtracker.ui.screens.HomeScreen
import com.example.pethealthtracker.ui.screens.HealthScreen
import com.example.pethealthtracker.ui.screens.DietScreen
import com.example.pethealthtracker.ui.screens.ExerciseScreen
import com.example.pethealthtracker.ui.screens.RemindersScreen
import com.example.pethealthtracker.ui.viewmodel.ReminderViewModel
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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

    // 全局提醒状态
    var showGlobalReminder by remember { mutableStateOf(false) }
    var globalUpcomingReminder by remember { mutableStateOf<Reminder?>(null) }
    var globalReminderPetName by remember { mutableStateOf("") }
    var globalMediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }
    var globalHasShownReminder by remember { mutableStateOf(setOf<String>()) }

    // 创建数据库和ViewModel（全局共用）
    val appDatabase = remember {
        AppDatabase.getInstance(navController.context)
    }

    val reminderViewModel = remember {
        val reminderRepository = ReminderRepository(appDatabase.reminderDao())
        ReminderViewModel(reminderRepository)
    }

    val petRepository = remember {
        PetRepository(appDatabase.petDao())
    }

    // 全局提醒检查循环（每秒轮询一次）
    LaunchedEffect(Unit) {
        android.util.Log.d("AppNavigation", "Starting global reminder check loop")
        var checkCount = 0
        while (true) {
            try {
                checkCount++

                // 获取所有启用的提醒，手动检查时间
                val allEnabledReminders = reminderViewModel.enabledReminders.value
                val currentTimeMillis = System.currentTimeMillis()

                // 找到即将触发的提醒（在提醒时间点正好到达时，前后各500ms容错）
                val currentUpcomingReminders = allEnabledReminders.filter { reminder ->
                    val timeDiff = reminder.reminderDate - currentTimeMillis
                    timeDiff >= -500 && timeDiff <= 500  // 提醒时间点前后各0.5秒
                }

                // 定期打印日志（每10次打印一次以避免日志过多）
                if (checkCount % 10 == 0) {
                    android.util.Log.d("AppNavigation", "Check #$checkCount: ${currentUpcomingReminders.size} upcoming reminders, hasShownReminder=${globalHasShownReminder.size} shown")
                    android.util.Log.d("AppNavigation", "Current time: ${SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(currentTimeMillis)}")
                    allEnabledReminders.forEach { reminder ->
                        val timeDiff = reminder.reminderDate - currentTimeMillis
                        if (timeDiff >= -5000 && timeDiff <= 5000) {  // 显示接近的提醒
                            android.util.Log.d("AppNavigation", "  - ${reminder.title} at ${SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(reminder.reminderDate)}, timeDiff=${timeDiff}ms")
                        }
                    }
                }

                if (currentUpcomingReminders.isNotEmpty()) {
                    for (reminder in currentUpcomingReminders) {
                        // 只显示未展示过的提醒
                        if (reminder.id !in globalHasShownReminder) {
                            android.util.Log.d("AppNavigation", "✓ SHOWING REMINDER: '${reminder.title}' at ${SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(reminder.reminderDate)}")
                            globalUpcomingReminder = reminder
                            showGlobalReminder = true
                            globalHasShownReminder = globalHasShownReminder + reminder.id

                            // 从数据库中获取宠物名称
                            val petDao = appDatabase.petDao()
                            val pet = try {
                                kotlinx.coroutines.runBlocking {
                                    petDao.getPetById(reminder.petId)
                                }
                            } catch (e: Exception) {
                                null
                            }
                            val petName = pet?.name ?: "Your Pet"
                            globalReminderPetName = petName
                            android.util.Log.d("AppNavigation", "Pet name: $petName")

                            // 播放音频
                            android.util.Log.d("AppNavigation", "Playing notification sound...")
                            playGlobalNotificationSound(navController.context) { player ->
                                android.util.Log.d("AppNavigation", "Media player created")
                                globalMediaPlayer = player
                            }
                            break
                        }
                    }
                }

                // 等待1秒后再检查
                delay(1000)
            } catch (e: Exception) {
                android.util.Log.e("AppNavigation", "Error checking reminders", e)
                delay(1000)
            }
        }
    }

    // 清理资源
    DisposableEffect(Unit) {
        onDispose {
            try {
                globalMediaPlayer?.apply {
                    if (isPlaying) {
                        stop()
                    }
                    reset()
                    release()
                }
            } catch (e: Exception) {
                android.util.Log.e("AppNavigation", "Error releasing media player", e)
            }
        }
    }

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
                RemindersScreen(navController, reminderViewModel, petRepository)
            }
        }
    }

    // 全局提醒通知对话框
    if (showGlobalReminder && globalUpcomingReminder != null) {
        AlertDialog(
            onDismissRequest = {
                showGlobalReminder = false
                try {
                    globalMediaPlayer?.apply {
                        if (isPlaying) {
                            stop()
                        }
                    }
                } catch (e: Exception) {
                    android.util.Log.e("AppNavigation", "Error stopping media player on dismiss", e)
                }
            },
            title = {
                Text(
                    text = "🔔 提醒",
                    style = androidx.compose.material3.MaterialTheme.typography.headlineSmall
                )
            },
            text = {
                androidx.compose.foundation.layout.Column {
                    Text(
                        text = globalUpcomingReminder!!.title,
                        style = androidx.compose.material3.MaterialTheme.typography.titleMedium
                    )
                    androidx.compose.foundation.layout.Spacer(modifier = Modifier.padding(8.dp))
                    Text(
                        text = "宠物: $globalReminderPetName",
                        style = androidx.compose.material3.MaterialTheme.typography.bodyMedium
                    )
                    androidx.compose.foundation.layout.Spacer(modifier = Modifier.padding(8.dp))
                    Text(
                        text = "分类: ${globalUpcomingReminder!!.category}",
                        style = androidx.compose.material3.MaterialTheme.typography.bodyMedium
                    )
                    if (globalUpcomingReminder!!.notes.isNotEmpty()) {
                        androidx.compose.foundation.layout.Spacer(modifier = Modifier.padding(8.dp))
                        Text(
                            text = "备注: ${globalUpcomingReminder!!.notes}",
                            style = androidx.compose.material3.MaterialTheme.typography.bodySmall
                        )
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        showGlobalReminder = false
                        try {
                            globalMediaPlayer?.apply {
                                if (isPlaying) {
                                    stop()
                                }
                            }
                        } catch (e: Exception) {
                            android.util.Log.e("AppNavigation", "Error stopping media player on button click", e)
                        }
                    }
                ) {
                    Text("关闭")
                }
            }
        )
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
        Icons.Default.Home,
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

/**
 * 播放全局通知音频
 */
private fun playGlobalNotificationSound(
    context: android.content.Context,
    onPlayerCreated: (MediaPlayer) -> Unit
) {
    try {
        val mediaPlayer = MediaPlayer()
        try {
            // 使用系统默认的通知音
            val alarmUri = android.media.RingtoneManager.getDefaultUri(
                android.media.RingtoneManager.TYPE_NOTIFICATION
            )
            mediaPlayer.setDataSource(context, alarmUri)
            mediaPlayer.setAudioAttributes(
                android.media.AudioAttributes.Builder()
                    .setUsage(android.media.AudioAttributes.USAGE_NOTIFICATION)
                    .setContentType(android.media.AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build()
            )

            mediaPlayer.setOnPreparedListener { mp ->
                try {
                    if (!mp.isPlaying) {
                        mp.start()
                    }
                } catch (e: Exception) {
                    android.util.Log.e("AppNavigation", "Error starting media player", e)
                }
            }

            mediaPlayer.setOnCompletionListener { mp ->
                try {
                    mp.reset()
                    mp.release()
                } catch (e: Exception) {
                    android.util.Log.e("AppNavigation", "Error releasing media player in completion listener", e)
                }
            }

            mediaPlayer.setOnErrorListener { mp, what, extra ->
                android.util.Log.e("AppNavigation", "MediaPlayer error: what=$what, extra=$extra")
                try {
                    mp.reset()
                    mp.release()
                } catch (e: Exception) {
                    android.util.Log.e("AppNavigation", "Error handling media player error", e)
                }
                true
            }

            mediaPlayer.prepareAsync()
            onPlayerCreated(mediaPlayer)
        } catch (e: Exception) {
            android.util.Log.e("AppNavigation", "Error setting up media player", e)
            mediaPlayer.release()
        }
    } catch (e: Exception) {
        android.util.Log.e("AppNavigation", "Error creating media player", e)
    }
}
