package com.jun.tripguide_v2.core.domain.usecase.kakao

import com.jun.tripguide_v2.core.data.repository.kakao.KakaoKeywordRepository
import com.jun.tripguide_v2.core.domain.BuildConfig
import com.jun.tripguide_v2.core.domain.TempConst
import com.jun.tripguide_v2.core.model.Address
import javax.inject.Inject

class GetKakaoLocalByKeywordUsecase @Inject constructor(
    private val kakaoLocalKeywordRepository: KakaoKeywordRepository
) {

    suspend operator fun invoke(keyword: String): List<Address> {
        return kakaoLocalKeywordRepository.getAddressByKeyword(BuildConfig.KAKAO_API_KEY, keyword)
    }
}