package com.jun.tripguide_v2_core.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jun.tripguide_v2.core.model.MeansType

@Entity(tableName = "travels")
data class TravelEntity(
    @PrimaryKey(autoGenerate = true) val travelId: Int,
    val title: String,
    val destination: String,
    val startingPoint: String,
    val startDate: Long,
    val endDate: Long,
    val startTime: Int,
    val endTime: Int,
    val meansType: MeansType,
    val startMeansLocation: String,
    val endMeansLocation: String
)