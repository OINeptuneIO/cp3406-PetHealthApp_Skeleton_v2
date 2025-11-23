package com.example.pethealthtracker.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pethealthtracker.data.entity.Reminder
import com.example.pethealthtracker.data.repository.ReminderRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ReminderViewModel(private val repository: ReminderRepository) : ViewModel() {
    fun getRemindersByPetId(petId: String): StateFlow<List<Reminder>> =
        repository.getRemindersByPetId(petId)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val enabledReminders: StateFlow<List<Reminder>> =
        repository.getEnabledReminders()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addReminder(reminder: Reminder) {
        viewModelScope.launch {
            repository.addReminder(reminder)
        }
    }

    fun updateReminder(reminder: Reminder) {
        viewModelScope.launch {
            repository.updateReminder(reminder)
        }
    }

    fun deleteReminder(reminder: Reminder) {
        viewModelScope.launch {
            repository.deleteReminder(reminder)
        }
    }

    fun deleteReminderById(reminderId: String) {
        viewModelScope.launch {
            repository.deleteReminderById(reminderId)
        }
    }

    fun updateReminderStatus(id: String, enabled: Boolean) {
        viewModelScope.launch {
            repository.updateReminderStatus(id, enabled)
        }
    }

    /**
     * 获取即将到来的提醒（在提醒时间点正好到达时）
     * @param withinMillis 时间范围（毫秒），默认500ms（前后各容错）
     */
    fun getUpcomingReminders(withinMillis: Long = 500): StateFlow<List<Reminder>> {
        val currentTimeMillis = System.currentTimeMillis()

        return enabledReminders
            .map { reminders ->
                val filtered = reminders.filter { reminder ->
                    val timeDiff = reminder.reminderDate - currentTimeMillis
                    // 提醒时间点正好到达时（前后各容错，避免时间误差）
                    val isUpcoming = timeDiff >= -withinMillis && timeDiff <= withinMillis

                    if (timeDiff >= -5000 && timeDiff <= 5000) {  // 调试范围
                        android.util.Log.d(
                            "ReminderViewModel",
                            "Reminder '${reminder.title}': reminderDate=${android.text.format.DateFormat.format("HH:mm:ss", reminder.reminderDate)}, " +
                                    "currentTime=${android.text.format.DateFormat.format("HH:mm:ss", currentTimeMillis)}, " +
                                    "timeDiff=${timeDiff}ms, isUpcoming=$isUpcoming"
                        )
                    }
                    isUpcoming
                }
                if (filtered.isNotEmpty()) {
                    android.util.Log.d("ReminderViewModel", "✓ Found ${filtered.size} upcoming reminders")
                } else {
                    if (reminders.isNotEmpty()) {
                        android.util.Log.d("ReminderViewModel", "ℹ Checked ${reminders.size} enabled reminders but none in range")
                    } else {
                        android.util.Log.d("ReminderViewModel", "ℹ No enabled reminders at all")
                    }
                }
                filtered
            }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    }

    /**
     * 获取即将到来的提醒用于通知栏显示（在当前时间之后）
     * @param withinMinutes 时间范围（分钟），默认24小时
     */
    fun getUpcomingRemindersForNotification(withinMinutes: Long = 24 * 60): StateFlow<List<Reminder>> {
        val currentTimeMillis = System.currentTimeMillis()
        val rangeMillis = withinMinutes * 60 * 1000

        return enabledReminders
            .map { reminders ->
                reminders.filter { reminder ->
                    val timeDiff = reminder.reminderDate - currentTimeMillis
                    // 显示所有未来的提醒（在指定范围内）
                    timeDiff > 0 && timeDiff <= rangeMillis
                }
                .sortedBy { it.reminderDate }  // 按时间排序
            }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    }
}
