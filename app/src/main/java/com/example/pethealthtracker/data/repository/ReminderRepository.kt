package com.example.pethealthtracker.data.repository

import com.example.pethealthtracker.data.dao.ReminderDao
import com.example.pethealthtracker.data.entity.Reminder
import kotlinx.coroutines.flow.Flow

class ReminderRepository(private val reminderDao: ReminderDao) {
    fun getRemindersByPetId(petId: String): Flow<List<Reminder>> =
        reminderDao.getRemindersByPetId(petId)

    fun getEnabledReminders(): Flow<List<Reminder>> =
        reminderDao.getEnabledReminders()

    suspend fun addReminder(reminder: Reminder) =
        reminderDao.insert(reminder)

    suspend fun updateReminder(reminder: Reminder) =
        reminderDao.update(reminder)

    suspend fun deleteReminder(reminder: Reminder) =
        reminderDao.delete(reminder)

    suspend fun updateReminderStatus(id: String, enabled: Boolean) =
        reminderDao.updateReminderStatus(id, enabled)

    suspend fun deleteByPetId(petId: String) =
        reminderDao.deleteByPetId(petId)
}
