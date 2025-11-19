package com.example.pethealthtracker.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(
    tableName = "health_records",
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
data class HealthRecord(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val petId: String,
    val weight: Double,
    val energyExpenditure: Double,
    val notes: String = "",
    val recordDate: Long = System.currentTimeMillis(),
    val createdAt: Long = System.currentTimeMillis()
)
