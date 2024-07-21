package com.jun.tripguide_v2.core.domain.usecase.tourapi

import com.jun.tripguide_v2.core.data_api.repository.tourapi.TouristsRepository
import com.jun.tripguide_v2.core.domain.usecase.room.GetTravelByIdUsecase
import com.jun.tripguide_v2.core.model.Tourist
import javax.inject.Inject

class GetTouristsUsecase @Inject constructor(
    private val touristsRepository: TouristsRepository,
    private val getTravelByIdUsecase: GetTravelByIdUsecase
) {

    suspend operator fun invoke(
        travelId: String,
        pageNo: Int = 1,
        arrange: String? = "P",
        contentType: String? = null
    ): List<Tourist> {
        val travel = getTravelByIdUsecase(travelId)
        val places = travel.places

        val areaCode = travel.destination.areaCode
        val sigunguCode = travel.destination.sigunguCode

        return touristsRepository.getTouristsApi(
            pageNo = pageNo.toString(),
            arrange = arrange ?: "P",
            areaCode = areaCode.code,
            sigunguCode = if (sigunguCode.code != "0") sigunguCode.code else null,
            contentType = contentType
        ).filter { tourist -> tourist.id !in places.map { it.id } }
    }
}