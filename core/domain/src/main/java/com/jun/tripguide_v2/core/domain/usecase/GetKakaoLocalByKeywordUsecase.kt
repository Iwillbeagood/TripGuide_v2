package com.jun.tripguide_v2.core.domain.usecase

import com.jun.tripguide_v2.core.data_api.repository.kakao.KakaoKeywordRepository
import com.jun.tripguide_v2.core.model.Address
import javax.inject.Inject

class GetKakaoLocalByKeywordUsecase @Inject constructor(
    private val kakaoLocalKeywordRepository: KakaoKeywordRepository
) {

    suspend operator fun invoke(keyword: String): List<Address> {
        return kakaoLocalKeywordRepository.getAddressByKeyword(keyword)
    }
}