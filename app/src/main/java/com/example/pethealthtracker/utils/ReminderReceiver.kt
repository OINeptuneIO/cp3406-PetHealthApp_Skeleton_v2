package com.example.pethealthtracker.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.app.NotificationManager
import android.app.PendingIntent
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.pethealthtracker.MainActivity
import com.example.pethealthtracker.R

class ReminderReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("ReminderReceiver", "onReceive called")
        if (context == null || intent == null) {
            Log.e("ReminderReceiver", "Context or Intent is null")
            return
        }

        val reminderId = intent.getStringExtra("REMINDER_ID") ?: return
        val reminderTitle = intent.getStringExtra("REMINDER_TITLE") ?: "Pet Reminder"
        val petName = intent.getStringExtra("PET_NAME") ?: "Your Pet"
        val reminderType = intent.getStringExtra("REMINDER_TYPE") ?: "ONCE"

        Log.d("ReminderReceiver", "Reminder triggered: $reminderTitle for $petName (Type: $reminderType)")

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val mainIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            context, reminderId.hashCode(), mainIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // 使用系统默认通知声音
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val builder = NotificationCompat.Builder(context, "pet_reminders")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(reminderTitle)
            .setContentText("Reminder for $petName")
            .setSubText(when (reminderType) {
                "ONCE" -> "One-time reminder"
                "MONTHLY" -> "Monthly reminder"
                "YEARLY" -> "Yearly reminder"
                else -> ""
            })
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setCategory(NotificationCompat.CATEGORY_REMINDER)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            // 设置声音 - 使用系统默认提示音
            .setSound(defaultSoundUri)
            // 设置振动 - 模式：暂停 500ms 200ms 500ms
            .setVibrate(longArrayOf(0, 500, 200, 500))
            // 设置灯光 - 蓝色闪烁
            .setLights(0xFF0000FF.toInt(), 1000, 2000)
            // 高优先级通知
            .setPriority(NotificationCompat.PRIORITY_MAX)

        // Android 8.0+ 设置重要性
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setFullScreenIntent(pendingIntent, true)
        }

        Log.d("ReminderReceiver", "Showing notification with ID: ${reminderId.hashCode()}")
        notificationManager.notify(reminderId.hashCode(), builder.build())
        Log.d("ReminderReceiver", "Notification displayed successfully")
    }
}
