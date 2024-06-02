package com.jun.tripguide_v2_core.database.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jun.tripguide_v2.core.model.Tourist

@ProvidedTypeConverter
class TouristConverter(private val gson: Gson) {

    @TypeConverter
    fun touristToJson(value: Tourist): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun jsonToTourist(value: String): Tourist {
        return gson.fromJson(value, Tourist::class.java)
    }


    @TypeConverter
    fun listToJson(value: List<Tourist>): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<Tourist> {
        val listType = object : TypeToken<List<Tourist>>() {}.type
        return gson.fromJson(value, listType)
    }
}
