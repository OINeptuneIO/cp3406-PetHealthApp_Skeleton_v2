package com.example.pethealthtracker.data.repository

import com.example.pethealthtracker.data.dao.DietRecordDao
import com.example.pethealthtracker.data.entity.DietRecord
import kotlinx.coroutines.flow.Flow

class DietRecordRepository(private val dietRecordDao: DietRecordDao) {
    fun getRecordsByPetId(petId: String): Flow<List<DietRecord>> =
        dietRecordDao.getRecordsByPetId(petId)

    suspend fun addRecord(record: DietRecord) =
        dietRecordDao.insert(record)

    suspend fun updateRecord(record: DietRecord) =
        dietRecordDao.update(record)

    suspend fun deleteRecord(record: DietRecord) =
        dietRecordDao.delete(record)

    suspend fun deleteByPetId(petId: String) =
        dietRecordDao.deleteByPetId(petId)
}
