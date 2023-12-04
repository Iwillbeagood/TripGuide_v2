package com.jun.tripguide_v2.core.data.repository.kakao

import com.jun.tripguide_v2.core.data.api.kakaoapi.KakaoKeywordApi
import com.jun.tripguide_v2.core.data.mapper.toData
import com.jun.tripguide_v2.core.model.Address
import javax.inject.Inject

class KakaoKeywordRepositoryImpl @Inject constructor(
    private val keywordApi: KakaoKeywordApi
): KakaoKeywordRepository {

    override suspend fun getAddressByKeyword(key: String, keyword: String): List<Address> {
        return keywordApi.getKakaoLocalByKeyword(key, keyword).documents.map {
            it.toData()
        }
    }
}