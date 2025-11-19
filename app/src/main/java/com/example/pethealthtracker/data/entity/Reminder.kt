package com.example.pethealthtracker.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.UUID

enum class ReminderType {
    ONCE,
    MONTHLY,
    YEARLY
}

@Entity(
    tableName = "reminders",
    foreignKeys = [
        ForeignKey(
            entity = Pet::class,
            parentColumns = ["id"],
            childColumns = ["petId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("petId")]
)
data class Reminder(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val petId: String,
    val title: String,
    val category: String,
    val reminderDate: Long,
    val reminderType: String = "ONCE",
    val isEnabled: Boolean = true,
    val notes: String = "",
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)
