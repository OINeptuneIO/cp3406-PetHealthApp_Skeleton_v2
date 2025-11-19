package com.example.pethealthtracker.utils

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.pethealthtracker.data.entity.Reminder
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class ReminderScheduler(private val context: Context) {
    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    fun scheduleReminder(reminder: Reminder, petName: String) {
        if (!reminder.isEnabled) {
            Log.d("ReminderScheduler", "Reminder is disabled, not scheduling")
            return
        }

        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val reminderDateTime = sdf.format(Date(reminder.reminderDate))
        Log.d("ReminderScheduler", "Scheduling reminder '${reminder.title}' for $reminderDateTime (${reminder.reminderDate})")

        val intent = Intent(context, ReminderReceiver::class.java).apply {
            putExtra("REMINDER_ID", reminder.id)
            putExtra("REMINDER_TITLE", reminder.title)
            putExtra("PET_NAME", petName)
            putExtra("REMINDER_TYPE", reminder.reminderType)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            reminder.id.hashCode(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        when (reminder.reminderType) {
            "ONCE" -> {
                Log.d("ReminderScheduler", "Scheduling ONCE reminder")
                scheduleExactReminder(reminder.reminderDate, pendingIntent)
            }
            "MONTHLY" -> {
                Log.d("ReminderScheduler", "Scheduling MONTHLY reminder")
                scheduleRecurringReminder(reminder, pendingIntent, 30)
            }
            "YEARLY" -> {
                Log.d("ReminderScheduler", "Scheduling YEARLY reminder")
                scheduleRecurringReminder(reminder, pendingIntent, 365)
            }
        }
    }

    private fun scheduleExactReminder(triggerTime: Long, pendingIntent: PendingIntent) {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val triggerDateTime = sdf.format(Date(triggerTime))
        val currentTime = System.currentTimeMillis()
        val delaySeconds = (triggerTime - currentTime) / 1000
        val delayMinutes = delaySeconds / 60
        Log.d("ReminderScheduler", "Setting exact alarm for $triggerDateTime (in $delayMinutes min, $delaySeconds sec)")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            // 检查权限
            val hasPermission = ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.SCHEDULE_EXACT_ALARM
            ) == PackageManager.PERMISSION_GRANTED

            Log.d("ReminderScheduler", "API 31+: canScheduleExactAlarms()=${alarmManager.canScheduleExactAlarms()}, hasPermission=$hasPermission")

            if (alarmManager.canScheduleExactAlarms() && hasPermission) {
                Log.d("ReminderScheduler", "Using setExactAndAllowWhileIdle (API 31+ with permission)")
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    triggerTime,
                    pendingIntent
                )
            } else {
                Log.w("ReminderScheduler", "Using setAndAllowWhileIdle instead (canScheduleExact=${alarmManager.canScheduleExactAlarms()}, permission=$hasPermission)")
                alarmManager.setAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    triggerTime,
                    pendingIntent
                )
            }
        } else {
            Log.d("ReminderScheduler", "Using setAndAllowWhileIdle (Pre-API 31)")
            alarmManager.setAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                triggerTime,
                pendingIntent
            )
        }
    }

    private fun scheduleRecurringReminder(
        reminder: Reminder,
        pendingIntent: PendingIntent,
        intervalDays: Int
    ) {
        val calendar = Calendar.getInstance().apply {
            timeInMillis = reminder.reminderDate
        }

        // 如果设定的时间已经过去，将其设置为下一个周期
        if (calendar.timeInMillis < System.currentTimeMillis()) {
            calendar.add(Calendar.DAY_OF_MONTH, intervalDays)
        }

        val intervalMillis = (intervalDays.toLong() * 24 * 60 * 60 * 1000)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (alarmManager.canScheduleExactAlarms()) {
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    pendingIntent
                )
            } else {
                alarmManager.setAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    pendingIntent
                )
            }
        } else {
            alarmManager.setAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                pendingIntent
            )
        }
    }

    fun cancelReminder(reminderId: String) {
        val intent = Intent(context, ReminderReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            reminderId.hashCode(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager.cancel(pendingIntent)
    }
}
