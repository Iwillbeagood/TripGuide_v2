package com.jun.tripguide_v2.core.data.repository.kakao

import com.jun.tripguide_v2.core.data.api.kakaoapi.KakaoKeywordApi
import com.jun.tripguide_v2.core.data.mapper.toData
import com.jun.tripguide_v2.core.data_api.repository.kakao.KakaoKeywordRepository
import com.jun.tripguide_v2.core.model.Address
import javax.inject.Inject

class KakaoKeywordRepositoryImpl @Inject constructor(
    private val keywordApi: KakaoKeywordApi
): KakaoKeywordRepository {

    override suspend fun getAddressByKeyword(keyword: String): List<Address> {
        return keywordApi.getKakaoLocalByKeyword(address = keyword).documents.map {
            it.toData()
        }
    }
}