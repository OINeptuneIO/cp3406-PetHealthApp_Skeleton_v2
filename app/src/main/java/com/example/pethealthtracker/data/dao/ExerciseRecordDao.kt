package com.example.pethealthtracker.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.pethealthtracker.data.entity.ExerciseRecord
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseRecordDao {
    @Insert
    suspend fun insert(record: ExerciseRecord)

    @Update
    suspend fun update(record: ExerciseRecord)

    @Delete
    suspend fun delete(record: ExerciseRecord)

    @Query("SELECT * FROM exercise_records WHERE id = :id")
    suspend fun getRecordById(id: String): ExerciseRecord?

    @Query("SELECT * FROM exercise_records WHERE petId = :petId ORDER BY recordDate DESC")
    fun getRecordsByPetId(petId: String): Flow<List<ExerciseRecord>>

    @Query("DELETE FROM exercise_records WHERE petId = :petId")
    suspend fun deleteByPetId(petId: String)
}
