package com.jun.tripguide_v2.feature.mytravelPlan.util

fun durationFormatter(duration: Int) = Pair(
    duration / 3600,
    duration / 60 -  duration / 3600 * 60
)