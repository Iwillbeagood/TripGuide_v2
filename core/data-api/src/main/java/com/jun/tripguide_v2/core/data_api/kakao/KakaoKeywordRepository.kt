package com.jun.tripguide_v2.core.data_api.kakao

import com.jun.tripguide_v2.core.model.Address

interface KakaoKeywordRepository {

    suspend fun getAddressByKeyword(keyword: String) : List<Address>
}