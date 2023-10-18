package com.jun.tripguide_v2.core.domain.usecase

import com.jun.tripguide_v2.core.model.AreaCode
import com.jun.tripguide_v2.core.data.repository.AreaCodeRepository
import com.jun.tripguide_v2.core.domain.TempConst
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDefaultAreaCodeUsecase @Inject constructor(
    private val areaCodeRepository: AreaCodeRepository
) {

    suspend operator fun invoke(): List<AreaCode> {
        return areaCodeRepository.getDefaultAreaCode(TempConst.queryParams)
    }
}