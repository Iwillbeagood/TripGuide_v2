package com.jun.tripguide_v2.core.data.api.tourapi.model.detailIntro

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

@Serializable
data class Items(
    val item: List<ItemTemplate>
)

@OptIn(ExperimentalSerializationApi::class)
@Serializable
@JsonClassDiscriminator("contenttypeid")
sealed interface ItemTemplate