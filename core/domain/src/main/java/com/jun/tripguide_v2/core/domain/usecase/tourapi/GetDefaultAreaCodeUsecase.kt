package com.jun.tripguide_v2.core.domain.usecase.tourapi

import com.jun.tripguide_v2.core.model.AreaCode
import com.jun.tripguide_v2.core.data.repository.tourapi.AreaCodeRepository
import com.jun.tripguide_v2.core.domain.TempConst
import javax.inject.Inject

class GetDefaultAreaCodeUsecase @Inject constructor(
    private val areaCodeRepository: AreaCodeRepository
) {

    suspend operator fun invoke(): List<AreaCode> {
        return areaCodeRepository.getDefaultAreaCode(TempConst.queryParams)
    }
}