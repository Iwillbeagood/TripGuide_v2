package com.jun.tripguide_v2.core.data.api.kakaoapi.model.kakaoroute

import kotlinx.serialization.Serializable

@Serializable
data class KakaoRouteResponse(
    val routes: List<Route>,
    val trans_id: String
)