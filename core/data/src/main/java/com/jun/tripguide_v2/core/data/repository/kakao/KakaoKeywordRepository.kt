package com.jun.tripguide_v2.core.data.repository.kakao

import com.jun.tripguide_v2.core.model.Address

interface KakaoKeywordRepository {

    suspend fun getAddressByKeyword(key: String, keyword: String) : List<Address>
}