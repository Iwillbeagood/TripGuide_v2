package com.jun.tripguide_v2.core.model

data class Travel(
    val travelId: Int,
    val destination: String = "목적지",
    val title: String = "$destination 여행",
    val startingPoint: String = "출발 장소",
    val startDate: Long = 0L,
    val endDate: Long = 0L,
    val startTime: Int = 0,
    val endTime: Int = 0,
    val meansType: MeansType = MeansType.CAR,
    val startMeansLocation: String = "",
    val endMeansLocation: String = ""
)
