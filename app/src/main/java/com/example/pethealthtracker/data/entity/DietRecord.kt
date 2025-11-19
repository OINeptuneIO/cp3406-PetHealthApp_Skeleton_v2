package com.example.pethealthtracker.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(
    tableName = "diet_records",
    foreignKeys = [
        ForeignKey(
            entity = Pet::class,
            parentColumns = ["id"],
            childColumns = ["petId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("petId")]
)
data class DietRecord(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val petId: String,
    val foodType: String,
    val amount: Double,
    val calories: Double = 0.0,
    val notes: String = "",
    val recordDate: Long = System.currentTimeMillis(),
    val createdAt: Long = System.currentTimeMillis()
)
