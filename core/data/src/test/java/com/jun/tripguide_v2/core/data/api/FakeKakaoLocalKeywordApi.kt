package com.jun.tripguide_v2.core.data.api

import com.jun.tripguide_v2.core.data.api.kakaoapi.KakaoKeywordApi
import com.jun.tripguide_v2.core.data.api.kakaoapi.model.kakaokeyword.KakaoKeywordResponse
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.File

@OptIn(ExperimentalSerializationApi::class)
class FakeKakaoLocalKeywordApi(
    private val json: Json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }
) : KakaoKeywordApi {

    private val addresses = File("src/main/assets/addresses.json")

    override suspend fun getKakaoLocalByKeyword(
        key: String,
        address: String
    ): KakaoKeywordResponse {
        return json.decodeFromStream(addresses.inputStream())
    }
}