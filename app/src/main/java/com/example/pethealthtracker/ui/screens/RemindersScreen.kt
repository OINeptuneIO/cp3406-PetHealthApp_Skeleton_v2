package com.example.pethealthtracker.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pethealthtracker.data.entity.Reminder
import com.example.pethealthtracker.data.repository.PetRepository
import com.example.pethealthtracker.ui.viewmodel.ReminderViewModel
import com.example.pethealthtracker.ui.components.IOSCalendar
import com.example.pethealthtracker.ui.components.DateRemindersBottomSheet
import com.example.pethealthtracker.ui.components.IOSReminderForm
import kotlinx.coroutines.flow.flowOf
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RemindersScreen(
    navController: NavController,
    reminderViewModel: ReminderViewModel,
    petRepository: PetRepository
) {
    var selectedDateMillis by remember { mutableLongStateOf(System.currentTimeMillis()) }
    var showDateRemindersSheet by remember { mutableStateOf(false) }
    var showAddReminderForm by remember { mutableStateOf(false) }
    var selectedPetIdForAdd by remember { mutableStateOf("") }

    // Get all pets
    val pets by petRepository.getAllPets().collectAsState(emptyList())

    // Get ALL reminders (all pets combined)
    val allReminders by reminderViewModel.enabledReminders.collectAsState(emptyList())

    // Get reminders for the selected date
    val remindersForSelectedDate = remember(selectedDateMillis, allReminders) {
        allReminders.filter { reminder ->
            val reminderCal = Calendar.getInstance()
            reminderCal.timeInMillis = reminder.reminderDate

            val selectedCal = Calendar.getInstance()
            selectedCal.timeInMillis = selectedDateMillis

            reminderCal.get(Calendar.YEAR) == selectedCal.get(Calendar.YEAR) &&
            reminderCal.get(Calendar.MONTH) == selectedCal.get(Calendar.MONTH) &&
            reminderCal.get(Calendar.DAY_OF_MONTH) == selectedCal.get(Calendar.DAY_OF_MONTH)
        }
    }

    // Get all dates that have reminders for highlighting
    val highlightedDates = remember(allReminders) {
        allReminders.map { reminder ->
            val cal = Calendar.getInstance()
            cal.timeInMillis = reminder.reminderDate
            cal.set(Calendar.HOUR_OF_DAY, 0)
            cal.set(Calendar.MINUTE, 0)
            cal.set(Calendar.SECOND, 0)
            cal.set(Calendar.MILLISECOND, 0)
            cal.timeInMillis
        }.toSet()
    }

    // Create a map of petId -> pet name for quick lookup
    val petMap = remember(pets) {
        pets.associateBy { it.id }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Reminder Calendar") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // iOS风格日历
            if (pets.isNotEmpty()) {
                IOSCalendar(
                    onDateSelected = { dateMillis ->
                        selectedDateMillis = dateMillis
                        showDateRemindersSheet = true
                    },
                    highlightedDates = highlightedDates
                )
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(100.dp))
                    Text(
                        text = "No Pets",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "Please add a pet on the home page first",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }

    // 日期提醒列表底部弹窗
    DateRemindersBottomSheet(
        isVisible = showDateRemindersSheet,
        selectedDateMillis = selectedDateMillis,
        reminders = remindersForSelectedDate,
        petMap = petMap,
        onDismiss = { showDateRemindersSheet = false },
        onAddReminder = {
            selectedPetIdForAdd = if (pets.isNotEmpty()) pets.first().id else ""
            showAddReminderForm = true
            showDateRemindersSheet = false
        },
        onToggleReminder = { reminderId, enabled ->
            reminderViewModel.updateReminderStatus(reminderId, enabled)
        },
        onDeleteReminder = { reminderId ->
            reminderViewModel.deleteReminderById(reminderId)
        }
    )

    // 添加提醒表单对话框
    if (showAddReminderForm && selectedPetIdForAdd.isNotEmpty()) {
        IOSReminderForm(
            onDismiss = {
                showAddReminderForm = false
                showDateRemindersSheet = true
            },
            onConfirm = { title, category, reminderType, dateMillis, hour, minute, notes ->
                // 将日期和时间组合成完整的时间戳
                val calendar = Calendar.getInstance()
                calendar.timeInMillis = dateMillis
                calendar.set(Calendar.HOUR_OF_DAY, hour)
                calendar.set(Calendar.MINUTE, minute)
                calendar.set(Calendar.SECOND, 0)
                calendar.set(Calendar.MILLISECOND, 0)

                val reminder = Reminder(
                    petId = selectedPetIdForAdd,
                    title = title,
                    category = category,
                    reminderDate = calendar.timeInMillis,
                    reminderType = reminderType,
                    notes = notes
                )
                reminderViewModel.addReminder(reminder)
                showAddReminderForm = false
                showDateRemindersSheet = true
            }
        )
    }
}
