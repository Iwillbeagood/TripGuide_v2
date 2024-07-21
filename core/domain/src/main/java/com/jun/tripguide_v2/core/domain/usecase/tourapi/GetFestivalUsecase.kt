package com.jun.tripguide_v2.core.domain.usecase.tourapi

import com.jun.tripguide_v2.core.data_api.repository.tourapi.FestivalRepository
import com.jun.tripguide_v2.core.model.tourApi.Festival
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class GetFestivalUsecase @Inject constructor(
    private val festivalRepository: FestivalRepository
) {

    suspend operator fun invoke(
        pageNo: Int = 1
    ): List<Festival> {
        val today = LocalDate.now()
        val dateFormatter = DateTimeFormatter.ofPattern("YYYYMMdd")
        return festivalRepository.getFestivals(pageNo, today.format(dateFormatter))
    }
}