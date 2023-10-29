package com.jun.tripguide_v2.core.model

import java.time.LocalTime

data class Travel(
    val travelId: String,
    val destination: DestinationCode,
    val title: String = "$destination 여행",
    val startingPoint: String = "출발 장소",
    val startDate: Long = 0L,
    val endDate: Long = 0L,
    val startTime: LocalTime,
    val endTime: LocalTime,
    val meansType: MeansType = MeansType.CAR,
    val startMeansLocation: String = "",
    val endMeansLocation: String = ""
)
