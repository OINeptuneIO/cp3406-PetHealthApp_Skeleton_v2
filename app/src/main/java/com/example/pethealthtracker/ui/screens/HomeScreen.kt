package com.example.pethealthtracker.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import com.example.pethealthtracker.data.entity.Pet
import com.example.pethealthtracker.data.repository.PetRepository
import com.example.pethealthtracker.ui.viewmodel.PetViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    var showAddPetDialog by remember { mutableStateOf(false) }

    // Create PetViewModel instance
    val petViewModel = remember {
        val appDatabase = AppDatabase.getInstance(navController.context)
        val petRepository = PetRepository(appDatabase.petDao())
        PetViewModel(petRepository)
    }

    // Observe pets from ViewModel
    val pets by petViewModel.pets.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Pet Health Tracker") })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showAddPetDialog = true }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Pet")
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
                text = "My Pets",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            PetList(pets)
        }
    }

    if (showAddPetDialog) {
        AddPetDialog(
            onDismiss = { showAddPetDialog = false },
            onConfirm = { pet ->
                // Add pet to database via ViewModel
                petViewModel.addPet(pet)
                showAddPetDialog = false
            }
        )
    }
}

@Composable
fun PetList(pets: List<Pet>) {
    if (pets.isEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "No pets yet",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Tap + to add your first pet",
                style = MaterialTheme.typography.bodySmall
            )
        }
    } else {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(pets) { pet ->
                PetCard(pet)
            }
        }
    }
}

@Composable
fun PetCard(pet: Pet) {
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
            Text(
                text = pet.name,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${pet.type} - ${pet.breed}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Age: ${pet.age} | Weight: ${pet.weight} kg",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AddPetDialog(
    onDismiss: () -> Unit,
    onConfirm: (Pet) -> Unit
) {
    val petTypes = listOf(
        "Dog",
        "Cat",
        "Bird",
        "Rabbit",
        "Hamster",
        "Fish",
        "Lizard",
        "Snake",
        "Turtle",
        "Guinea Pig"
    )

    var petName by remember { mutableStateOf("") }
    var petType by remember { mutableStateOf("") }
    var petBreed by remember { mutableStateOf("") }
    var petAge by remember { mutableStateOf("") }
    var petWeight by remember { mutableStateOf("") }

    androidx.compose.material3.AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add New Pet") },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                androidx.compose.material3.TextField(
                    value = petName,
                    onValueChange = { petName = it },
                    label = { Text("Pet Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "Select Pet Type:",
                    style = MaterialTheme.typography.bodySmall
                )
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    petTypes.forEach { type ->
                        androidx.compose.material3.FilterChip(
                            selected = petType == type,
                            onClick = { petType = type },
                            label = { Text(type) }
                        )
                    }
                }
                androidx.compose.material3.TextField(
                    value = petBreed,
                    onValueChange = { petBreed = it },
                    label = { Text("Breed") },
                    modifier = Modifier.fillMaxWidth()
                )
                androidx.compose.material3.TextField(
                    value = petAge,
                    onValueChange = { petAge = it },
                    label = { Text("Age") },
                    modifier = Modifier.fillMaxWidth()
                )
                androidx.compose.material3.TextField(
                    value = petWeight,
                    onValueChange = { petWeight = it },
                    label = { Text("Weight (kg)") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            androidx.compose.material3.TextButton(
                onClick = {
                    val pet = Pet(
                        name = petName,
                        type = petType,
                        breed = petBreed,
                        age = petAge.toIntOrNull() ?: 0,
                        weight = petWeight.toDoubleOrNull() ?: 0.0
                    )
                    onConfirm(pet)
                }
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
