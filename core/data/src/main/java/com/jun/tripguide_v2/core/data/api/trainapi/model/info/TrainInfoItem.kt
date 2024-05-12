package com.jun.tripguide_v2.core.data.api.trainapi.model.info

import kotlinx.serialization.Serializable

@Serializable
data class TrainInfoItem(
    val arrplacename: String,
    val arrplandtime: Long,
    val depplacename: String,
    val depplandtime: Long,
    val traingradename: String,
    val trainno: Int
)