package com.example.pethealthtracker.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.pethealthtracker.data.entity.Pet
import kotlinx.coroutines.flow.Flow

@Dao
interface PetDao {
    @Insert
    suspend fun insert(pet: Pet)

    @Update
    suspend fun update(pet: Pet)

    @Delete
    suspend fun delete(pet: Pet)

    @Query("SELECT * FROM pets WHERE id = :id")
    suspend fun getPetById(id: String): Pet?

    @Query("SELECT * FROM pets ORDER BY createdAt DESC")
    fun getAllPets(): Flow<List<Pet>>

    @Query("DELETE FROM pets WHERE id = :id")
    suspend fun deleteById(id: String)
}
