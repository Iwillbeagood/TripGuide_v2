package com.jun.tripguide_v2_core.database.util

import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneOffset

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