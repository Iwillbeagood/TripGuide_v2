package com.jun.tripguide_v2.core.domain.usecase.tourapi

import com.jun.tripguide_v2.core.data.repository.tourapi.AreaBaseListRepository
import com.jun.tripguide_v2.core.domain.TempConst.queryParams
import com.jun.tripguide_v2.core.model.Tourist
import javax.inject.Inject

class GetAreaBaseListWithTypeUsecase @Inject constructor(
    private val areaBaseListRepository: AreaBaseListRepository
) {

    suspend operator fun invoke(
        pageNo: String,
        arrange: String,
        areaCode: String,
        contentType: String
    ): List<Tourist> {
        return areaBaseListRepository.getAreaBaseListWithType(
            queryParams = queryParams,
            pageNo = pageNo,
            arrange = arrange,
            areaCode = areaCode,
            contentType = contentType
        )
    }
}