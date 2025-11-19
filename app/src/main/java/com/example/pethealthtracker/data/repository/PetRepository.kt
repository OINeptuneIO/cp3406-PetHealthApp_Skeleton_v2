package com.example.pethealthtracker.data.repository

import com.example.pethealthtracker.data.dao.PetDao
import com.example.pethealthtracker.data.entity.Pet
import kotlinx.coroutines.flow.Flow

class PetRepository(private val petDao: PetDao) {
    fun getAllPets(): Flow<List<Pet>> = petDao.getAllPets()

    suspend fun getPetById(id: String): Pet? = petDao.getPetById(id)

    suspend fun addPet(pet: Pet) = petDao.insert(pet)

    suspend fun updatePet(pet: Pet) = petDao.update(pet)

    suspend fun deletePet(pet: Pet) = petDao.delete(pet)

    suspend fun deletePetById(id: String) = petDao.deleteById(id)
}
