package com.jun.tripguide_v2.feature.recommend.utils

fun metersToKilometersString(meters: Double): String {
    val kilometers = meters / 1000.0
    return "약 %.1f km".format(kilometers)
}