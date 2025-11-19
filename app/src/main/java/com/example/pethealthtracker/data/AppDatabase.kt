package com.example.pethealthtracker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pethealthtracker.data.dao.DietRecordDao
import com.example.pethealthtracker.data.dao.ExerciseRecordDao
import com.example.pethealthtracker.data.dao.HealthRecordDao
import com.example.pethealthtracker.data.dao.PetDao
import com.example.pethealthtracker.data.dao.ReminderDao
import com.example.pethealthtracker.data.entity.DietRecord
import com.example.pethealthtracker.data.entity.ExerciseRecord
import com.example.pethealthtracker.data.entity.HealthRecord
import com.example.pethealthtracker.data.entity.Pet
import com.example.pethealthtracker.data.entity.Reminder

@Database(
    entities = [Pet::class, HealthRecord::class, DietRecord::class, ExerciseRecord::class, Reminder::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun petDao(): PetDao
    abstract fun healthRecordDao(): HealthRecordDao
    abstract fun dietRecordDao(): DietRecordDao
    abstract fun exerciseRecordDao(): ExerciseRecordDao
    abstract fun reminderDao(): ReminderDao

    companion object {
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "pet_health_db"
                ).build().also { instance = it }
            }
        }
    }
}
