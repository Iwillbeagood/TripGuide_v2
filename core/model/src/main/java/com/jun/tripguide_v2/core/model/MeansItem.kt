package com.jun.tripguide_v2.core.model

data class MeansItem(
    val icon: Int,
    val selectedIcon: Int,
    val type: MeansType,
    val isSelected: Boolean = false
)