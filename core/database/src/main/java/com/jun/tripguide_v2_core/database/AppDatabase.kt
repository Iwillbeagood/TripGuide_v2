package com.jun.tripguide_v2_core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jun.tripguide_v2_core.database.util.LocalTimeConverter

@Database(
    entities = [TravelEntity::class],
    version = 1
)
@TypeConverters(
    value = [
        LocalTimeConverter::class
    ]
)
abstract class AppDatabase : RoomDatabase() {

    abstract val travelDao: TravelDao

    companion object {
        @Volatile
        private var database: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return database ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                database = instance
                instance
            }
        }
    }
}