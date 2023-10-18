package com.jun.tripguide_v2.core.data.api.model.kakaokeyword

import kotlinx.serialization.Serializable

@Serializable
data class KakaoKeywordResponse(
    val documents: List<Document>,
)