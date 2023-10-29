package com.jun.tripguide_v2.core.domain.usecase.tourapi

import com.jun.tripguide_v2.core.data.repository.tourapi.AreaBaseListRepository
import com.jun.tripguide_v2.core.domain.TempConst.baseListQueryParams
import com.jun.tripguide_v2.core.model.Tourist
import javax.inject.Inject

class GetSigunguBaseListByTypeUsecase @Inject constructor(
    private val areaBaseListRepository: AreaBaseListRepository
) {

    suspend operator fun invoke(
        pageNo: String,
        arrange: String,
        areaCode: String,
        sigunguCode: String,
        contentType: String
    ): List<Tourist> {
        return areaBaseListRepository.getSigunguBaseListWithType(
            queryParams = baseListQueryParams,
            pageNo = pageNo,
            arrange = arrange,
            areaCode = areaCode,
            sigunguCode = sigunguCode,
            contentType = contentType
        )
    }
}