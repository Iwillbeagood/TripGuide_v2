package com.jun.tripguide_v2.core.domain.usecase.tourapi

import com.jun.tripguide_v2.core.data.repository.tourapi.CommonInfoRepository
import com.jun.tripguide_v2.core.data.repository.tourapi.DetailIntroRepository
import com.jun.tripguide_v2.core.model.tourApi.CommonInfo
import com.jun.tripguide_v2.core.model.tourApi.DetailIntro
import javax.inject.Inject

class GetDetailInfoUsecase @Inject constructor(
    private val commonInfoRepository: CommonInfoRepository,
    private val detailIntroRepository: DetailIntroRepository
) {
    
    suspend operator fun invoke(contentId: String): Pair<CommonInfo, List<DetailIntro>> {
        val commonInfo = commonInfoRepository.getCommonInfo(contentId)
        val detailIntro = detailIntroRepository.getDetailIntro(contentId, commonInfo.contenttypeid)
        return commonInfo to detailIntro
    }
}