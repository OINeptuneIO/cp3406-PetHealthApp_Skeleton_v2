package com.example.pethealthtracker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import com.example.pethealthtracker.data.AppDatabase
import com.example.pethealthtracker.ui.navigation.AppNavigation
import com.example.pethealthtracker.ui.theme.PetHealthTrackerTheme
import com.example.pethealthtracker.utils.ReminderScheduler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val scheduleExactAlarmPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            Log.d("MainActivity", "SCHEDULE_EXACT_ALARM permission granted")
//            android.widget.Toast.makeText(this, "日程提醒权限已授予", android.widget.Toast.LENGTH_SHORT).show()
        } else {
            Log.w("MainActivity", "SCHEDULE_EXACT_ALARM permission denied")
//            android.widget.Toast.makeText(this, "日程提醒权限被拒绝，闹钟可能无法工作", android.widget.Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 请求权限
        requestScheduleExactAlarmPermission()

        // 创建通知渠道
        createNotificationChannel()

        // 恢复已保存的提醒
        restoreReminders()

        setContent {
            PetHealthTrackerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }

    private fun requestScheduleExactAlarmPermission() {
        val apiLevel = Build.VERSION.SDK_INT
        Log.d("MainActivity", "Checking permissions... Current API level: $apiLevel (S=${Build.VERSION_CODES.S})")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            Log.d("MainActivity", "Device is Android 12+ (S or higher)")

            val permission = android.Manifest.permission.SCHEDULE_EXACT_ALARM
            val hasPermission = ContextCompat.checkSelfPermission(
                this,
                permission
            ) == PackageManager.PERMISSION_GRANTED

            Log.d("MainActivity", "Permission check: SCHEDULE_EXACT_ALARM = $hasPermission")

            if (!hasPermission) {
                Log.d("MainActivity", "Permission not granted, requesting SCHEDULE_EXACT_ALARM...")
                try {
                    scheduleExactAlarmPermissionLauncher.launch(permission)
                    Log.d("MainActivity", "Permission request launched successfully")
                } catch (e: Exception) {
                    Log.e("MainActivity", "Error launching permission request", e)
                }
            } else {
                Log.d("MainActivity", "SCHEDULE_EXACT_ALARM permission already granted")
//                android.widget.Toast.makeText(this, "日程提醒权限已授予", android.widget.Toast.LENGTH_SHORT).show()
            }
        } else {
            Log.d("MainActivity", "Device is below Android 12 (API level $apiLevel), permission not needed")
        }
    }

    private fun restoreReminders() {
        CoroutineScope(Dispatchers.Default).launch {
            try {
                Log.d("MainActivity", "Starting reminder restoration...")
                val database = AppDatabase.getInstance(this@MainActivity)
                val reminderDao = database.reminderDao()
                val petDao = database.petDao()
                val reminderScheduler = ReminderScheduler(this@MainActivity)

                // 获取所有启用的提醒
                reminderDao.getAllReminders().forEach { reminder ->
                    if (reminder.isEnabled) {
                        val pet = petDao.getPetById(reminder.petId)
                        if (pet != null) {
                            Log.d("MainActivity", "Restoring reminder: ${reminder.title}")
                            reminderScheduler.scheduleReminder(reminder, pet.name)
                        }
                    }
                }
                Log.d("MainActivity", "Reminder restoration completed")
            } catch (e: Exception) {
                Log.e("MainActivity", "Error restoring reminders", e)
            }
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Pet Health Reminders"
            val descriptionText = "Reminders for pet health activities and vaccinations"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("pet_reminders", name, importance).apply {
                description = descriptionText
                enableVibration(true)
                enableLights(true)
                setShowBadge(true)
            }

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}
