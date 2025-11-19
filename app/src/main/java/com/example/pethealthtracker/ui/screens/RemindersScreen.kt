package com.example.pethealthtracker.ui.screens

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.pethealthtracker.data.AppDatabase
import com.example.pethealthtracker.data.entity.Reminder
import com.example.pethealthtracker.data.repository.PetRepository
import com.example.pethealthtracker.data.repository.ReminderRepository
import com.example.pethealthtracker.ui.viewmodel.ReminderViewModel
import com.example.pethealthtracker.utils.ReminderScheduler
import kotlinx.coroutines.flow.flowOf
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun RemindersScreen(navController: NavController) {
    var showAddReminderDialog by remember { mutableStateOf(false) }
    var selectedPetId by remember { mutableStateOf("") }

    // Create repositories and ViewModel
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

    val reminderScheduler = remember {
        ReminderScheduler(navController.context)
    }

    // Get all pets
    val pets by petRepository.getAllPets().collectAsState(emptyList())

    // Initialize selectedPetId with the first pet if empty
    if (selectedPetId.isEmpty() && pets.isNotEmpty()) {
        selectedPetId = pets.first().id
    }

    // Get reminders for the selected pet - use remember to avoid recreating the flow
    val remindersFlow = remember(selectedPetId) {
        if (selectedPetId.isNotEmpty()) {
            reminderViewModel.getRemindersByPetId(selectedPetId)
        } else {
            flowOf(emptyList())
        }
    }
    val reminders by remindersFlow.collectAsState(emptyList())

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Reminders & Calendar") })
        },
        floatingActionButton = {
            if (selectedPetId.isNotEmpty()) {
                FloatingActionButton(
                    onClick = { showAddReminderDialog = true }
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add Reminder")
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // 权限检查警告
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                val hasPermission = ContextCompat.checkSelfPermission(
                    navController.context,
                    android.Manifest.permission.SCHEDULE_EXACT_ALARM
                ) == PackageManager.PERMISSION_GRANTED

                if (!hasPermission) {
                    // 权限未授予 - 显示警告和设置按钮
                    androidx.compose.material3.Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        colors = androidx.compose.material3.CardDefaults.cardColors(
                            containerColor = androidx.compose.material3.MaterialTheme.colorScheme.errorContainer
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = "⚠️ 权限提示",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onErrorContainer
                            )
                            Text(
                                text = "日程提醒权限未授予，闹钟功能将无法正常工作。请在设置中授予\"日程提醒\"权限。",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onErrorContainer,
                                modifier = Modifier.padding(top = 8.dp, bottom = 12.dp)
                            )
                            Button(
                                onClick = {
                                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                                        data = Uri.fromParts("package", navController.context.packageName, null)
                                    }
                                    navController.context.startActivity(intent)
                                },
                                modifier = Modifier.align(Alignment.End)
                            ) {
                                Text("去设置")
                            }
                        }
                    }
                }
            }

            Text(
                text = "Upcoming Reminders",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            if (pets.isEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "No pets found",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "Go to Home to add a pet first",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            } else {
                Text(
                    text = "Select Pet:",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ) {
                    pets.forEach { pet ->
                        androidx.compose.material3.FilterChip(
                            selected = selectedPetId == pet.id,
                            onClick = { selectedPetId = pet.id },
                            label = { Text(pet.name) }
                        )
                    }
                }

                if (reminders.isEmpty()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(vertical = 32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "No reminders yet",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            text = "Tap + to add a reminder",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                } else {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(reminders.size) { index ->
                            val reminder = reminders[index]
                            val selectedPet = pets.find { it.id == selectedPetId }
                            ReminderCard(
                                title = reminder.title,
                                date = formatDateTime(reminder.reminderDate),
                                category = reminder.category,
                                reminderType = reminder.reminderType,
                                isEnabled = reminder.isEnabled,
                                onToggle = { enabled ->
                                    reminderViewModel.updateReminderStatus(reminder.id, enabled)
                                    val updatedReminder = reminder.copy(isEnabled = enabled)
                                    if (enabled && selectedPet != null) {
                                        reminderScheduler.scheduleReminder(updatedReminder, selectedPet.name)
                                    } else {
                                        reminderScheduler.cancelReminder(reminder.id)
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    if (showAddReminderDialog && selectedPetId.isNotEmpty()) {
        val selectedPet = pets.find { it.id == selectedPetId }
        AddReminderDialog(
            onDismiss = { showAddReminderDialog = false },
            onConfirm = { title, category, reminderType, date, time, notes ->
                val reminderDate = parseDateTime(date, time)
                val reminder = Reminder(
                    petId = selectedPetId,
                    title = title,
                    category = category,
                    reminderDate = reminderDate,
                    reminderType = reminderType,
                    notes = notes
                )
                reminderViewModel.addReminder(reminder)
                // Schedule the reminder
                if (selectedPet != null) {
                    reminderScheduler.scheduleReminder(reminder, selectedPet.name)
                }
                showAddReminderDialog = false
            }
        )
    }
}

private fun formatDateTime(timestamp: Long): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    return sdf.format(Date(timestamp))
}

private fun parseDateTime(date: String, time: String): Long {
    return try {
        // 处理时间格式：支持 HH:mm 和 HH:mm:ss
        val normalizedTime = if (time.count { it == ':' } == 1) {
            "$time:00"  // 如果只有 HH:mm，添加秒数
        } else {
            time
        }

        val dateTimeString = "$date $normalizedTime"
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val parsedDate = sdf.parse(dateTimeString)
        val timestamp = parsedDate?.time ?: System.currentTimeMillis()

        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val formattedTime = simpleDateFormat.format(Date(timestamp))
        val currentTime = System.currentTimeMillis()
        val delaySeconds = (timestamp - currentTime) / 1000
        val delayMinutes = delaySeconds / 60

        android.util.Log.d("RemindersScreen", "Parsed datetime: input='$date $time' -> normalized='$dateTimeString', timestamp=$timestamp, formatted='$formattedTime', delay=$delayMinutes min $delaySeconds sec")

        if (timestamp < currentTime) {
            android.util.Log.w("RemindersScreen", "WARNING: Reminder time is in the past! Current time: ${simpleDateFormat.format(Date(currentTime))}, Reminder time: $formattedTime")
        } else {
            android.util.Log.i("RemindersScreen", "Reminder scheduled for future time, delay: $delayMinutes minutes")
        }

        timestamp
    } catch (e: Exception) {
        android.util.Log.e("RemindersScreen", "Error parsing datetime: date='$date', time='$time'", e)
        System.currentTimeMillis()
    }
}

@Composable
fun ReminderCard(
    title: String,
    date: String,
    category: String,
    reminderType: String,
    isEnabled: Boolean,
    onToggle: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = date,
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = "$category - $reminderType",
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Checkbox(
                checked = isEnabled,
                onCheckedChange = onToggle
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddReminderDialog(
    onDismiss: () -> Unit,
    onConfirm: (title: String, category: String, reminderType: String, date: String, time: String, notes: String) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var reminderType by remember { mutableStateOf("ONCE") }
    var notes by remember { mutableStateOf("") }

    val currentDate = remember { SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date()) }
    val currentTime = remember { SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date()) }
    var date by remember { mutableStateOf(currentDate) }
    var time by remember { mutableStateOf(currentTime) }
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        datePickerState.selectedDateMillis?.let {
                            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                            date = sdf.format(Date(it))
                        }
                        showDatePicker = false
                    }
                ) {
                    Text("确认")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text("取消")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    androidx.compose.material3.AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add Reminder") },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                androidx.compose.material3.TextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") },
                    modifier = Modifier.fillMaxWidth()
                )
                // Date Picker Button
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { showDatePicker = true },
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = "Date",
                                style = MaterialTheme.typography.labelSmall
                            )
                            Text(
                                text = date,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
                androidx.compose.material3.TextField(
                    value = time,
                    onValueChange = { newTime ->
                        // 确保格式为 HH:mm:ss
                        time = if (newTime.length <= 5) {
                            newTime
                        } else {
                            newTime
                        }
                    },
                    label = { Text("Time (HH:mm:ss)") },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("e.g., 14:30:00") }
                )
                androidx.compose.material3.TextField(
                    value = category,
                    onValueChange = { category = it },
                    label = { Text("Category (Vaccination/Checkup/etc)") },
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "Reminder Type: $reminderType",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    listOf("ONCE", "MONTHLY", "YEARLY").forEach { type ->
                        androidx.compose.material3.Button(
                            onClick = { reminderType = type },
                            modifier = Modifier
                                .weight(1f)
                                .height(36.dp)
                        ) {
                            Text(type, style = MaterialTheme.typography.labelSmall)
                        }
                    }
                }
                androidx.compose.material3.TextField(
                    value = notes,
                    onValueChange = { notes = it },
                    label = { Text("Notes") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            androidx.compose.material3.TextButton(
                onClick = { onConfirm(title, category, reminderType, date, time, notes) }
            ) {
                Text("Add")
            }
        },
        dismissButton = {
            androidx.compose.material3.TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
