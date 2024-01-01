package com.jun.tripguide_v2.core.data.api.tourapi.model.location_tourist

import kotlinx.serialization.Serializable

@Serializable
data class LocationTouristItem(
    val dist: Double,
    val addr1: String,
    val areacode: String,
    val booktour: String,
    val cat1: String,
    val cat2: String,
    val cat3: String,
    val contentid: String,
    val contenttypeid: String,
    val cpyrhtDivCd: String,
    val createdtime: String,
    val firstimage: String,
    val firstimage2: String,
    val mapx: Double,
    val mapy: Double,
    val mlevel: String,
    val modifiedtime: String,
    val sigungucode: String,
    val tel: String,
    val title: String
)