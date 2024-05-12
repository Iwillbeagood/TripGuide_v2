package com.jun.tripguide_v2_core.database.converter

import androidx.room.TypeConverter
import java.time.LocalTime

class LocalTimeConverter {
    @TypeConverter
    fun fromLocalTime(value: LocalTime): Long {
        return value.toSecondOfDay().toLong()
    }

    @TypeConverter
    fun toLocalTime(value: Long): LocalTime {
        return LocalTime.ofSecondOfDay(value)
    }
}