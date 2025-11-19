package com.example.pethealthtracker.ui.screens

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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.navigation.NavController
import com.example.pethealthtracker.data.AppDatabase
import com.example.pethealthtracker.data.entity.HealthRecord
import com.example.pethealthtracker.data.repository.HealthRecordRepository
import com.example.pethealthtracker.data.repository.PetRepository
import com.example.pethealthtracker.ui.viewmodel.HealthViewModel
import kotlinx.coroutines.flow.flowOf
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun HealthScreen(navController: NavController) {
    var showAddHealthDialog by remember { mutableStateOf(false) }
    var selectedPetId by remember { mutableStateOf("") }

    // Create repositories and ViewModel
    val appDatabase = remember {
        AppDatabase.getInstance(navController.context)
    }

    val healthViewModel = remember {
        val healthRepository = HealthRecordRepository(appDatabase.healthRecordDao())
        HealthViewModel(healthRepository)
    }

    val petRepository = remember {
        PetRepository(appDatabase.petDao())
    }

    // Get all pets
    val pets by petRepository.getAllPets().collectAsState(emptyList())

    // Initialize selectedPetId with the first pet if empty
    if (selectedPetId.isEmpty() && pets.isNotEmpty()) {
        selectedPetId = pets.first().id
    }

    // Get health records for the selected pet - use remember to avoid recreating the flow
    val healthRecordsFlow = remember(selectedPetId) {
        if (selectedPetId.isNotEmpty()) {
            healthViewModel.getRecordsByPetId(selectedPetId)
        } else {
            flowOf(emptyList())
        }
    }
    val healthRecords by healthRecordsFlow.collectAsState(emptyList())

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Health Tracking") })
        },
        floatingActionButton = {
            if (selectedPetId.isNotEmpty()) {
                FloatingActionButton(
                    onClick = { showAddHealthDialog = true }
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add Health Record")
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
            Text(
                text = "Health Records",
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

                if (healthRecords.isEmpty()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(vertical = 32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "No health records yet",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            text = "Tap + to add a health record",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                } else {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(healthRecords.size) { index ->
                            val record = healthRecords[index]
                            HealthRecordCard(
                                weight = record.weight,
                                energyExpenditure = record.energyExpenditure,
                                date = formatDateTime(record.recordDate),
                                notes = record.notes
                            )
                        }
                    }
                }
            }
        }
    }

    if (showAddHealthDialog && selectedPetId.isNotEmpty()) {
        AddHealthRecordDialog(
            onDismiss = { showAddHealthDialog = false },
            onConfirm = { weight, energy, notes, date, time ->
                val recordDate = parseDateTime(date, time)
                val record = HealthRecord(
                    petId = selectedPetId,
                    weight = weight.toDoubleOrNull() ?: 0.0,
                    energyExpenditure = energy.toDoubleOrNull() ?: 0.0,
                    notes = notes,
                    recordDate = recordDate
                )
                healthViewModel.addRecord(record)
                showAddHealthDialog = false
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
        val dateTimeString = "$date $time"
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        val parsedDate = sdf.parse(dateTimeString)
        parsedDate?.time ?: System.currentTimeMillis()
    } catch (e: Exception) {
        System.currentTimeMillis()
    }
}

@Composable
fun HealthRecordCard(
    weight: Double,
    energyExpenditure: Double,
    date: String,
    notes: String
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
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Weight",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = "$weight kg",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                Column {
                    Text(
                        text = "Energy Expenditure",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = "$energyExpenditure kcal",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = date, style = MaterialTheme.typography.bodySmall)
            Text(text = notes, style = MaterialTheme.typography.bodySmall)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddHealthRecordDialog(
    onDismiss: () -> Unit,
    onConfirm: (weight: String, energy: String, notes: String, date: String, time: String) -> Unit
) {
    var weight by remember { mutableStateOf("") }
    var energy by remember { mutableStateOf("") }
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
        title = { Text("Add Health Record") },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                androidx.compose.material3.TextField(
                    value = weight,
                    onValueChange = { weight = it },
                    label = { Text("Weight (kg)") },
                    modifier = Modifier.fillMaxWidth()
                )
                androidx.compose.material3.TextField(
                    value = energy,
                    onValueChange = { energy = it },
                    label = { Text("Energy Expenditure (kcal)") },
                    modifier = Modifier.fillMaxWidth()
                )
                androidx.compose.material3.TextField(
                    value = notes,
                    onValueChange = { notes = it },
                    label = { Text("Notes") },
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
                    onValueChange = { time = it },
                    label = { Text("Time (HH:mm)") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            androidx.compose.material3.TextButton(
                onClick = { onConfirm(weight, energy, notes, date, time) }
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
