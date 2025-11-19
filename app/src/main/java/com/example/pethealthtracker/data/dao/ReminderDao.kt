package com.example.pethealthtracker.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.pethealthtracker.data.entity.Reminder
import kotlinx.coroutines.flow.Flow

@Dao
interface ReminderDao {
    @Insert
    suspend fun insert(reminder: Reminder)

    @Update
    suspend fun update(reminder: Reminder)

    @Delete
    suspend fun delete(reminder: Reminder)

    @Query("SELECT * FROM reminders WHERE id = :id")
    suspend fun getReminderById(id: String): Reminder?

    @Query("SELECT * FROM reminders WHERE petId = :petId ORDER BY reminderDate ASC")
    fun getRemindersByPetId(petId: String): Flow<List<Reminder>>

    @Query("SELECT * FROM reminders WHERE isEnabled = 1 ORDER BY reminderDate ASC")
    fun getEnabledReminders(): Flow<List<Reminder>>

    @Query("SELECT * FROM reminders ORDER BY reminderDate ASC")
    suspend fun getAllReminders(): List<Reminder>

    @Query("UPDATE reminders SET isEnabled = :enabled WHERE id = :id")
    suspend fun updateReminderStatus(id: String, enabled: Boolean)

    @Query("DELETE FROM reminders WHERE petId = :petId")
    suspend fun deleteByPetId(petId: String)
}
