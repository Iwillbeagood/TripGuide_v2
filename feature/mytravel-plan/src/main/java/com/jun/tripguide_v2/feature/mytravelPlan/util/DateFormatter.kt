package com.jun.tripguide_v2.feature.mytravelPlan.util

import android.annotation.SuppressLint
import com.jun.tripguide_v2.core.model.TravelDay
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

@SuppressLint("SimpleDateFormat")
fun getFormattedDate(startDate: Long, endDate: Long): List<TravelDay> {
    val dateFormat = SimpleDateFormat("MM월 dd일", Locale.KOREA)
    val travelDays = mutableListOf<TravelDay>()
    val calender = Calendar.getInstance()
    var index = 1

    calender.timeInMillis = startDate
    travelDays.add(TravelDay(index, dateFormat.format(calender.timeInMillis), true))


    while (calender.timeInMillis < endDate) {
        calender.add(Calendar.DAY_OF_MONTH, 1)
        index++
        travelDays.add(TravelDay(index, dateFormat.format(calender.timeInMillis)))
    }

    return travelDays
}

fun LocalTime.dateTimeFormatter(): String {
    val formatter = DateTimeFormatter.ofPattern("hh:mm a")
    return format(formatter)
}