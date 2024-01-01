package com.jun.tripguide_v2.core.data.mapper

import com.jun.tripguide_v2.core.model.AirplaneSchedule
import com.jun.tripguide_v2.core.data.api.airplaneapi.model.AirplaneScheduleItem
import java.time.LocalTime
import java.time.format.DateTimeFormatter

fun AirplaneScheduleItem.toAirplaneSchedule() = AirplaneSchedule(
    startcity,
    domesticStartTime.toLocalTime(),
    arrivalcity,
    domesticArrivalTime.toLocalTime()
)

private fun String.toLocalTime() =
    LocalTime.parse(this, DateTimeFormatter.ofPattern("HHmm"))