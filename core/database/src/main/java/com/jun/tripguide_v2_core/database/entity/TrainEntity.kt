package com.jun.tripguide_v2_core.database.entity

data class TrainEntity(
    val trainType: String,
    val trainNo: Int,
    val depPlanedTime: String,
    val depPlaceName: String,
    val arrPlanedTime: String,
    val arrPlaceName: String
)