package com.jun.tripguide_v2_core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.jun.tripguide_v2_core.database.converter.AreaCodeConverter
import com.jun.tripguide_v2_core.database.converter.LocalDateTimeConverter
import com.jun.tripguide_v2_core.database.dao.RouteDao
import com.jun.tripguide_v2_core.database.dao.TravelDao
import com.jun.tripguide_v2_core.database.entity.RouteEntity
import com.jun.tripguide_v2_core.database.converter.TrainEntityConverter
import com.jun.tripguide_v2_core.database.entity.TravelEntity
import com.jun.tripguide_v2_core.database.converter.LocalTimeConverter
import com.jun.tripguide_v2_core.database.converter.TouristConverter

@Database(
    entities = [
        TravelEntity::class,
        RouteEntity::class
    ],
    version = 3
)
@TypeConverters(
    value = [
        LocalTimeConverter::class,
        LocalDateTimeConverter::class,
        TrainEntityConverter::class,
        AreaCodeConverter::class,
        TouristConverter::class,
    ]
)
abstract class AppDatabase : RoomDatabase() {

    abstract val travelDao: TravelDao
    abstract val routeDao: RouteDao

    companion object {
        @Volatile
        private var database: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return database ?: synchronized(this) {
                val instance = Room
                    .databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app_database"
                    )
                    .addTypeConverter(TrainEntityConverter(Gson()))
                    .addTypeConverter(AreaCodeConverter(Gson()))
                    .addTypeConverter(TouristConverter(Gson()))
                    .fallbackToDestructiveMigration()
                    .build()
                database = instance
                instance
            }
        }
    }
}