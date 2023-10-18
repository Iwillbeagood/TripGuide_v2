package com.jun.tripguide_v2.core.data.api.model

import com.example.tripguide.model.kakao.Document
import com.example.tripguide.model.kakao.Meta

data class KakaoAddressResponse(
    val documents: List<Document>,
//    val addresses: List<Address>,
    val meta: Meta
)