package com.example.pethealthtracker.data.repository

import com.example.pethealthtracker.data.dao.HealthRecordDao
import com.example.pethealthtracker.data.entity.HealthRecord
import kotlinx.coroutines.flow.Flow

class HealthRecordRepository(private val healthRecordDao: HealthRecordDao) {
    fun getRecordsByPetId(petId: String): Flow<List<HealthRecord>> =
        healthRecordDao.getRecordsByPetId(petId)

    suspend fun getLatestRecord(petId: String): HealthRecord? =
        healthRecordDao.getLatestRecord(petId)

    suspend fun addRecord(record: HealthRecord) =
        healthRecordDao.insert(record)

    suspend fun updateRecord(record: HealthRecord) =
        healthRecordDao.update(record)

    suspend fun deleteRecord(record: HealthRecord) =
        healthRecordDao.delete(record)

    suspend fun deleteByPetId(petId: String) =
        healthRecordDao.deleteByPetId(petId)
}
