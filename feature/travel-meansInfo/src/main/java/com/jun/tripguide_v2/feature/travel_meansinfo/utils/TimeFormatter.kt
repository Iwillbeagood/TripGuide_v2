package com.jun.tripguide_v2.feature.travel_meansinfo.utils

import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

fun epochTimeToString(epochTime: Long): String {
    val instant = Instant.ofEpochMilli(epochTime)

    val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")
        .withZone(ZoneId.systemDefault())
    return formatter.format(instant)
}

fun longToTimeString(longValue: Long): String {
    val dateTimeString = longValue.toString()
    val formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss")
    val localDateTime = LocalDateTime.parse(dateTimeString, formatter)

    return localDateTime.format(DateTimeFormatter.ofPattern("HH:mm"))
}

fun longToTrainDate(milliseconds: Long): String {
    val sdf = SimpleDateFormat("yyyy.MM.dd (E)", Locale.getDefault())
    val date = Date(milliseconds)
    return sdf.format(date)
}