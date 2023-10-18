package com.jun.tripguide_v2.core.data.api

import com.jun.tripguide_v2.core.data.api.model.areaCode.AreaCodeResponse
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.File

@OptIn(ExperimentalSerializationApi::class)
class FakeTourAreaCodeApi(
    private val json: Json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }
) : TourAreaCodeApi {

    private val areaCodes = File("src/main/assets/areaCodes.json")
    private val defaultAreaCodes = File("src/main/assets/defaultAreaCodes.json")

    override suspend fun getAreaCode(
        queryParams: Map<String, String>,
        areaCode: String
    ): AreaCodeResponse {
        return json.decodeFromStream(areaCodes.inputStream())
    }

    override suspend fun getDefaultAreaCode(queryParams: Map<String, String>): AreaCodeResponse {
        return json.decodeFromStream(defaultAreaCodes.inputStream())
    }
}