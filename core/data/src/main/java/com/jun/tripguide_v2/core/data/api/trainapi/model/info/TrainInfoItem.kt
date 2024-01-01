package com.jun.tripguide_v2.core.data.api.trainapi.model.info

import kotlinx.serialization.Serializable

@Serializable
data class TrainInfoItem(
    val adultcharge: Int,
    val arrplacename: String,
    val arrplandtime: String,
    val depplacename: String,
    val depplandtime: String,
    val traingradename: String,
    val trainno: Int
)