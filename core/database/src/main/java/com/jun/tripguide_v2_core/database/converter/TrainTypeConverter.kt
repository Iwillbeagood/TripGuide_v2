package com.jun.tripguide_v2_core.database.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.jun.tripguide_v2_core.database.entity.TrainEntity

@ProvidedTypeConverter
class TrainTypeConverter(private val gson: Gson) {

    @TypeConverter
    fun listToJson(value: TrainEntity): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): TrainEntity {
        return gson.fromJson(value, TrainEntity::class.java)
    }
}
