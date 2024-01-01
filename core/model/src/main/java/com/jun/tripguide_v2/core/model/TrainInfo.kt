package com.jun.tripguide_v2.core.model

data class TrainInfo(
    val trainName: String,
    val depPlanedTime: String,
    val arrPlanedTime: String,
    val isSelected: Boolean = false
)
