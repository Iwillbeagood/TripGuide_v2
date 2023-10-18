package com.jun.tripguide_v2.core.model

data class MeansItems(
    val drawable: Int,
    val type: MeansType,
    val isSelected: Boolean = false
)