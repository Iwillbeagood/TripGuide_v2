package com.jun.tripguide_v2_core.database.converter

import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.ZoneOffset

class LocalDateTimeConverter {
    @TypeConverter
    fun fromLocalDateTime(value: LocalDateTime): Long {
        return value.toEpochSecond(ZoneOffset.UTC)
    }

    @TypeConverter
    fun toLocalDateTime(value: Long): LocalDateTime {
        return LocalDateTime.ofEpochSecond(value, 0, ZoneOffset.UTC)
    }
}