package com.jun.tripguide_v2_core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jun.tripguide_v2.core.model.Tourist
import com.jun.tripguide_v2.core.model.tourApi.AreaCode
import java.time.LocalDateTime
import java.time.LocalTime

@Entity(tableName = "travels")
data class TravelEntity(
    @ColumnInfo(name = "travelId")
    @PrimaryKey(autoGenerate = true) val travelId: Long = 0,
    val title: String,
    val startPlace: Tourist,
    val startDate: Long,
    val endPlace: Tourist,
    val endDate: Long,
    val trainEntity: TrainEntity,
    val returnTrainEntity: TrainEntity,
    val places: List<Tourist>,
    val areaCode: AreaCode,
    val sigunguCode: AreaCode
)
