package com.jun.tripguide_v2.core.model

data class TravelDay(
    val day: Int,
    val detailsDay: String,
    val isSelected: Boolean = false
)
