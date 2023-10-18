package com.jun.tripguide_v2.core.data.api.fake

import android.content.Context
import com.jun.tripguide_v2.core.data.api.TourAreaCodeApi
import com.jun.tripguide_v2.core.data.api.model.areaCode.AreaCodeResponse
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream

@OptIn(ExperimentalSerializationApi::class)
internal class AssetsTourAreaCodeApi(
    context: Context,
    private val json: Json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    },
) : TourAreaCodeApi {

    private val areaCodes = context.assets.open("areaCodes.json")
    private val defaultAreaCodes = context.assets.open("defaultAreaCodes.json")

    override suspend fun getAreaCode(
        queryParams: Map<String, String>,
        areaCode: String
    ): AreaCodeResponse {
        return json.decodeFromStream(areaCodes)
    }

    override suspend fun getDefaultAreaCode(queryParams: Map<String, String>): AreaCodeResponse {
        return json.decodeFromStream(defaultAreaCodes)
    }
}
