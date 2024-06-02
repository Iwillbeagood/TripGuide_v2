package com.jun.tripguide_v2_core.database.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.jun.tripguide_v2.core.model.tourApi.AreaCode

@ProvidedTypeConverter
class AreaCodeConverter(private val gson: Gson) {

    @TypeConverter
    fun listToJson(value: AreaCode): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): AreaCode {
        return gson.fromJson(value, AreaCode::class.java)
    }
}
