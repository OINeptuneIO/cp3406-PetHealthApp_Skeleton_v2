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
import com.example.pethealthtracker.data.entity.DietRecord
import com.example.pethealthtracker.data.repository.DietRecordRepository
import com.example.pethealthtracker.data.repository.PetRepository
import com.example.pethealthtracker.ui.viewmodel.DietViewModel
import kotlinx.coroutines.flow.flowOf
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun DietScreen(navController: NavController) {
    var showAddDietDialog by remember { mutableStateOf(false) }
    var selectedPetId by remember { mutableStateOf("") }

    // Create repositories and ViewModel
    val appDatabase = remember {
        AppDatabase.getInstance(navController.context)
    }

    val dietViewModel = remember {
        val dietRepository = DietRecordRepository(appDatabase.dietRecordDao())
        DietViewModel(dietRepository)
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

    // Get diet records for the selected pet - use remember to avoid recreating the flow
    val dietRecordsFlow = remember(selectedPetId) {
        if (selectedPetId.isNotEmpty()) {
            dietViewModel.getRecordsByPetId(selectedPetId)
        } else {
            flowOf(emptyList())
        }
    }
    val dietRecords by dietRecordsFlow.collectAsState(emptyList())

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Diet Tracking") })
        },
        floatingActionButton = {
            if (selectedPetId.isNotEmpty()) {
                FloatingActionButton(
                    onClick = { showAddDietDialog = true }
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add Diet Record")
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
                text = "Diet Records",
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

                if (dietRecords.isEmpty()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(vertical = 32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "No diet records yet",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            text = "Tap + to add a diet record",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                } else {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(dietRecords.size) { index ->
                            val record = dietRecords[index]
                            DietRecordCard(
                                foodType = record.foodType,
                                amount = record.amount,
                                calories = record.calories,
                                date = formatDateTime(record.recordDate)
                            )
                        }
                    }
                }
            }
        }
    }

    if (showAddDietDialog && selectedPetId.isNotEmpty()) {
        AddDietRecordDialog(
            onDismiss = { showAddDietDialog = false },
            onConfirm = { foodType, amount, calories, date, time ->
                val recordDate = parseDateTime(date, time)
                val record = DietRecord(
                    petId = selectedPetId,
                    foodType = foodType,
                    amount = amount.toDoubleOrNull() ?: 0.0,
                    calories = calories.toDoubleOrNull() ?: 0.0,
                    recordDate = recordDate
                )
                dietViewModel.addRecord(record)
                showAddDietDialog = false
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
fun DietRecordCard(
    foodType: String,
    amount: Double,
    calories: Double,
    date: String
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
                        text = foodType,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "$amount g",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "$calories kcal",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = date, style = MaterialTheme.typography.bodySmall)
        }
    }
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AddDietRecordDialog(
    onDismiss: () -> Unit,
    onConfirm: (foodType: String, amount: String, calories: String, date: String, time: String) -> Unit
) {
    val foodTypes = listOf(
        "Dry Food",
        "Wet Food",
        "Fresh Food",
        "Treats",
        "Vegetables",
        "Meat",
        "Fish",
        "Chicken",
        "Rice",
        "Eggs"
    )

    var foodType by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var calories by remember { mutableStateOf("") }

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
        title = { Text("Add Diet Record") },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Select Food Type:",
                    style = MaterialTheme.typography.bodySmall
                )
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    foodTypes.forEach { type ->
                        androidx.compose.material3.FilterChip(
                            selected = foodType == type,
                            onClick = { foodType = type },
                            label = { Text(type) }
                        )
                    }
                }
                androidx.compose.material3.TextField(
                    value = amount,
                    onValueChange = { amount = it },
                    label = { Text("Amount (g)") },
                    modifier = Modifier.fillMaxWidth()
                )
                androidx.compose.material3.TextField(
                    value = calories,
                    onValueChange = { calories = it },
                    label = { Text("Calories (kcal)") },
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
                onClick = { onConfirm(foodType, amount, calories, date, time) }
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
