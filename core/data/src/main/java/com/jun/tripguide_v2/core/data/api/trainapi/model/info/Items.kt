package com.jun.tripguide_v2.core.data.api.trainapi.model.info

import kotlinx.serialization.Serializable

@Serializable
data class Items(
    val item: List<TrainInfoItem>
)