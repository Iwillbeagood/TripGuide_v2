package com.jun.tripguide_v2.core.data.api.kakaoapi.model.kakaoroute

import com.example.tripguide.model.kakaoroute.Route

data class KakaoRoute(
    val routes: List<Route>,
    val trans_id: String
)