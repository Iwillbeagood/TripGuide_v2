package com.jun.tripguide_v2.core.data.api.tourapi.model.stay

import kotlinx.serialization.Serializable

@Serializable
data class StayItem(
    val addr1: String,
    val addr2: String,
    val areacode: String,
    val benikia: String,
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
    val goodstay: String,
    val hanok: String,
    val mapx: String,
    val mapy: String,
    val mlevel: String,
    val modifiedtime: String,
    val sigungucode: String,
    val tel: String,
    val title: String
)