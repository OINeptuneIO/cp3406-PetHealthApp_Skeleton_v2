package com.example.pethealthtracker.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pethealthtracker.data.entity.DietRecord
import com.example.pethealthtracker.data.repository.DietRecordRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DietViewModel(private val repository: DietRecordRepository) : ViewModel() {
    fun getRecordsByPetId(petId: String): StateFlow<List<DietRecord>> =
        repository.getRecordsByPetId(petId)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addRecord(record: DietRecord) {
        viewModelScope.launch {
            repository.addRecord(record)
        }
    }

    fun updateRecord(record: DietRecord) {
        viewModelScope.launch {
            repository.updateRecord(record)
        }
    }

    fun deleteRecord(record: DietRecord) {
        viewModelScope.launch {
            repository.deleteRecord(record)
        }
    }
}
