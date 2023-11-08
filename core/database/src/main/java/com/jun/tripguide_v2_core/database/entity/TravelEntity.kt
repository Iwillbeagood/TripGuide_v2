package com.jun.tripguide_v2_core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jun.tripguide_v2.core.model.MeansType
import java.time.LocalTime

@Entity(tableName = "travels")
data class TravelEntity(
    @ColumnInfo(name = "travelId")
    @PrimaryKey
    val travelId: String,

    val title: String,
    val destination: String,
    val areaCode: String,
    val sigunguCode: String,
    val startingPoint: String,
    val startingX: String,
    val startingY: String,
    val startDate: Long,
    val endDate: Long,
    val startTime: LocalTime,
    val endTime: LocalTime,
    val meansType: String,
    val startMeansLocation: String,
    val endMeansLocation: String
)