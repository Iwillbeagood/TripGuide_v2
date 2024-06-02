package com.jun.tripguide_v2_core.database.converter

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.time.LocalTime

class LocalTimeConverter {
    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun fromLocalTime(value: LocalTime): Long {
        return value.toSecondOfDay().toLong()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun toLocalTime(value: Long): LocalTime {
        return LocalTime.ofSecondOfDay(value)
    }
}