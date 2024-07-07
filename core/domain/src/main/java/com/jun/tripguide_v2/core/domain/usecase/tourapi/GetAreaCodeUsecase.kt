package com.jun.tripguide_v2.core.domain.usecase.tourapi

import com.jun.tripguide_v2.core.data.repository.tourapi.AreaCodeRepository
import com.jun.tripguide_v2.core.model.tourApi.AreaCode
import javax.inject.Inject

class GetAreaCodeUsecase @Inject constructor(
    private val areaCodeRepository: AreaCodeRepository
) {

    suspend operator fun invoke(
        areaCode: String? = null
    ): List<AreaCode> {
        return areaCodeRepository.getAreaCode(areaCode)
    }
}