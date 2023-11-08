package com.jun.tripguide_v2.feature.mytravel.util

import android.annotation.SuppressLint
import com.jun.tripguide_v2.core.model.Duration
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@SuppressLint("SimpleDateFormat")
private fun Long.getFormattedDate(): String {
    val calender = Calendar.getInstance()
    calender.timeInMillis = this
    val dateFormat = SimpleDateFormat("MM월 dd일 E요일", Locale.KOREA)
    return dateFormat.format(calender.timeInMillis)
}

internal fun Long.getFormattedYear(): String {
    val calender = Calendar.getInstance()
    calender.timeInMillis = this
    val yearFormat = SimpleDateFormat("yyyy년 ", Locale.KOREA)
    return yearFormat.format(calender.timeInMillis)
}

fun Duration.toDateStringType(): String {
    return startDate.getFormattedDate() + " ~ " + endDate.getFormattedDate()
}

fun Duration.toYearStringType(): String {
    return startDate.getFormattedYear()
}