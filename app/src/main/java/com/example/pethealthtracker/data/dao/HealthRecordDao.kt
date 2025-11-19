package com.example.pethealthtracker.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.pethealthtracker.data.entity.HealthRecord
import kotlinx.coroutines.flow.Flow

@Dao
interface HealthRecordDao {
    @Insert
    suspend fun insert(record: HealthRecord)

    @Update
    suspend fun update(record: HealthRecord)

    @Delete
    suspend fun delete(record: HealthRecord)

    @Query("SELECT * FROM health_records WHERE id = :id")
    suspend fun getRecordById(id: String): HealthRecord?

    @Query("SELECT * FROM health_records WHERE petId = :petId ORDER BY recordDate DESC")
    fun getRecordsByPetId(petId: String): Flow<List<HealthRecord>>

    @Query("SELECT * FROM health_records WHERE petId = :petId ORDER BY recordDate DESC LIMIT 1")
    suspend fun getLatestRecord(petId: String): HealthRecord?

    @Query("DELETE FROM health_records WHERE petId = :petId")
    suspend fun deleteByPetId(petId: String)
}
