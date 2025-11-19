package com.example.pethealthtracker.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pethealthtracker.data.entity.HealthRecord
import com.example.pethealthtracker.data.repository.HealthRecordRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HealthViewModel(private val repository: HealthRecordRepository) : ViewModel() {
    fun getRecordsByPetId(petId: String): StateFlow<List<HealthRecord>> =
        repository.getRecordsByPetId(petId)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addRecord(record: HealthRecord) {
        viewModelScope.launch {
            repository.addRecord(record)
        }
    }

    fun updateRecord(record: HealthRecord) {
        viewModelScope.launch {
            repository.updateRecord(record)
        }
    }

    fun deleteRecord(record: HealthRecord) {
        viewModelScope.launch {
            repository.deleteRecord(record)
        }
    }

    suspend fun getLatestRecord(petId: String): HealthRecord? =
        repository.getLatestRecord(petId)
}
