package com.example.pethealthtracker.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pethealthtracker.data.entity.Pet
import com.example.pethealthtracker.data.repository.PetRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PetViewModel(private val petRepository: PetRepository) : ViewModel() {
    val pets: StateFlow<List<Pet>> = petRepository.getAllPets()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addPet(pet: Pet) {
        viewModelScope.launch {
            petRepository.addPet(pet)
        }
    }

    fun updatePet(pet: Pet) {
        viewModelScope.launch {
            petRepository.updatePet(pet)
        }
    }

    fun deletePet(pet: Pet) {
        viewModelScope.launch {
            petRepository.deletePet(pet)
        }
    }

    suspend fun getPetById(id: String): Pet? = petRepository.getPetById(id)
}
