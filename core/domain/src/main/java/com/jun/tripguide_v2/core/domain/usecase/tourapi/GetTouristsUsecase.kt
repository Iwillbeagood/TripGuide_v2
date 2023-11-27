package com.jun.tripguide_v2.core.domain.usecase.tourapi

import com.jun.tripguide_v2.core.data.repository.tourapi.TouristsRepository
import com.jun.tripguide_v2.core.domain.TempConst.queryParams
import com.jun.tripguide_v2.core.domain.usecase.room.GetRoutesUsecase
import com.jun.tripguide_v2.core.domain.usecase.room.GetTravelByIdUsecase
import com.jun.tripguide_v2.core.model.Tourist
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class GetTouristsUsecase @Inject constructor(
    private val touristsRepository: TouristsRepository,
    private val getTravelByIdUsecase: GetTravelByIdUsecase,
    private val getRoutesUsecase: GetRoutesUsecase
) {

    suspend operator fun invoke(
        travelId: String,
        pageNo: String = "1",
        arrange: String = "P",
        contentType: String? = null
    ): Flow<List<Tourist>> {
        val travel = getTravelByIdUsecase(travelId)
        val routes = getRoutesUsecase(travelId)

        val areaCode = travel.destination.areaCode
        val sigunguCode = travel.destination.sigunguCode

        return flow {
            emit(touristsRepository.getTouristsApi(
                queryParams = queryParams,
                pageNo = pageNo,
                arrange = arrange,
                areaCode = areaCode,
                sigunguCode = if (sigunguCode != "0") sigunguCode else null,
                contentType = contentType
            ).filter { tourist -> tourist.title !in routes.map { it.title } })
        }

    }
}