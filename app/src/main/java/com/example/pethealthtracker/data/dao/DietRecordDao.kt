package com.example.pethealthtracker.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.pethealthtracker.data.entity.DietRecord
import kotlinx.coroutines.flow.Flow

@Dao
interface DietRecordDao {
    @Insert
    suspend fun insert(record: DietRecord)

    @Update
    suspend fun update(record: DietRecord)

    @Delete
    suspend fun delete(record: DietRecord)

    @Query("SELECT * FROM diet_records WHERE id = :id")
    suspend fun getRecordById(id: String): DietRecord?

    @Query("SELECT * FROM diet_records WHERE petId = :petId ORDER BY recordDate DESC")
    fun getRecordsByPetId(petId: String): Flow<List<DietRecord>>

    @Query("DELETE FROM diet_records WHERE petId = :petId")
    suspend fun deleteByPetId(petId: String)
}
