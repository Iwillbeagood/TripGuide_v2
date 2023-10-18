package com.jun.tripguide_v2.core.data.api.model

import com.example.tripguide.model.kakao.DocumentX
import com.example.tripguide.model.kakao.MetaX

data class KakaoKeywordResponse(
    val documents: List<DocumentX>,
    val meta: MetaX
)