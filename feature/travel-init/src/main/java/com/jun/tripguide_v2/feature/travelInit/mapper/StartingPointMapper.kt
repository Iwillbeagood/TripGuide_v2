package com.jun.tripguide_v2.feature.travelInit.mapper

import com.jun.tripguide_v2.core.model.StartingPoint

fun String?.toStartingPoint(): StartingPoint? {
    if (isNullOrBlank()) return null
    val (name, address, x, y) = split("/")
    return StartingPoint(name, x.toDouble(), y.toDouble(), address)
}