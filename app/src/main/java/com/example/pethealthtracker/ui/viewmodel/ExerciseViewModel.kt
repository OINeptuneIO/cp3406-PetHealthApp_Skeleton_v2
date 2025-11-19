package com.example.pethealthtracker.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pethealthtracker.data.entity.ExerciseRecord
import com.example.pethealthtracker.data.repository.ExerciseRecordRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ExerciseViewModel(private val repository: ExerciseRecordRepository) : ViewModel() {
    fun getRecordsByPetId(petId: String): StateFlow<List<ExerciseRecord>> =
        repository.getRecordsByPetId(petId)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addRecord(record: ExerciseRecord) {
        viewModelScope.launch {
            repository.addRecord(record)
        }
    }

    fun updateRecord(record: ExerciseRecord) {
        viewModelScope.launch {
            repository.updateRecord(record)
        }
    }

    fun deleteRecord(record: ExerciseRecord) {
        viewModelScope.launch {
            repository.deleteRecord(record)
        }
    }
}
