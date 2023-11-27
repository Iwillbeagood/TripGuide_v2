package com.jun.tripguide_v2.feature.travelInit.util

import android.annotation.SuppressLint
import com.jun.tripguide_v2.core.model.DateDuration
import java.text.SimpleDateFormat
import java.util.Calendar

@SuppressLint("SimpleDateFormat")
fun Long.getFormattedDate(): String {
    val calender = Calendar.getInstance()
    calender.timeInMillis = this
    val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    return dateFormat.format(calender.timeInMillis)
}

fun DateDuration.toStringType(): String {
    return startDate.getFormattedDate() + " ~ " + endDate.getFormattedDate()
}