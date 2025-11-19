package com.example.pethealthtracker.data.repository

import com.example.pethealthtracker.data.dao.ExerciseRecordDao
import com.example.pethealthtracker.data.entity.ExerciseRecord
import kotlinx.coroutines.flow.Flow

class ExerciseRecordRepository(private val exerciseRecordDao: ExerciseRecordDao) {
    fun getRecordsByPetId(petId: String): Flow<List<ExerciseRecord>> =
        exerciseRecordDao.getRecordsByPetId(petId)

    suspend fun addRecord(record: ExerciseRecord) =
        exerciseRecordDao.insert(record)

    suspend fun updateRecord(record: ExerciseRecord) =
        exerciseRecordDao.update(record)

    suspend fun deleteRecord(record: ExerciseRecord) =
        exerciseRecordDao.delete(record)

    suspend fun deleteByPetId(petId: String) =
        exerciseRecordDao.deleteByPetId(petId)
}
