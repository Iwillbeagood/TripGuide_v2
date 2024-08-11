package com.jun.tripguide_v2.core.domain.usecase.tourapi

import com.jun.tripguide_v2.core.data_api.repository.tourapi.TouristsRepository
import com.jun.tripguide_v2.core.model.DestinationCode
import com.jun.tripguide_v2.core.model.Tourist
import javax.inject.Inject

class GetTouristsUsecase @Inject constructor(
    private val touristsRepository: TouristsRepository,
) {

    suspend operator fun invoke(
        destinationCode: DestinationCode,
        pageNo: Int = 1,
        arrange: String? = "P",
        contentType: String? = null
    ): List<Tourist> {

        val areaCode = destinationCode.areaCode
        val sigunguCode = destinationCode.sigunguCode

        return touristsRepository.getTouristsApi(
            pageNo = pageNo.toString(),
            arrange = arrange ?: "P",
            areaCode = areaCode.code,
            sigunguCode = if (sigunguCode.code != "0") sigunguCode.code else null,
            contentType = contentType
        )
    }
}