package com.jun.tripguide_v2.core.domain.usecase.tourapi

import com.jun.tripguide_v2.core.data_api.repository.tourapi.StayRepository
import com.jun.tripguide_v2.core.model.tourApi.Stay
import javax.inject.Inject

class GetStayUsecase @Inject constructor(
    private val stayRepository: StayRepository
) {
    
    suspend operator fun invoke(pageNo: Int = 1): List<Stay> {
        return stayRepository.getStays(pageNo)
    }
}