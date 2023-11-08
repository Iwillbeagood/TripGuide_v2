package com.jun.tripguide_v2.core.domain.usecase.tourapi

import com.jun.tripguide_v2.core.data.repository.tourapi.AreaBaseListRepository
import com.jun.tripguide_v2.core.domain.TempConst.queryParams
import com.jun.tripguide_v2.core.domain.usecase.room.GetTravelByIdUsecase
import com.jun.tripguide_v2.core.model.Tourist
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class GetTouristsUsecase @Inject constructor(
    private val areaBaseListRepository: AreaBaseListRepository,
    private val getTravelByIdUsecase: GetTravelByIdUsecase
) {

    suspend operator fun invoke(
        travelId: String,
        pageNo: String = "1",
        arrange: String = "P",
        contentType: String = ""
    ): Flow<List<Tourist>> {
        return flow {
            emit(getTravelByIdUsecase(travelId))
        }.flatMapConcat { travel ->
            flow {
                emit(
                    if (travel.destination.sigunguCode == "0") {
                        areaBaseListRepository.getAreaBaseListWithType(
                            queryParams = queryParams,
                            pageNo = pageNo,
                            arrange = arrange,
                            areaCode = travel.destination.areaCode,
                            contentType = contentType
                        )
                    } else {
                        areaBaseListRepository.getSigunguBaseListWithType(
                            queryParams = queryParams,
                            pageNo = pageNo,
                            arrange = arrange,
                            areaCode = travel.destination.areaCode,
                            sigunguCode = travel.destination.sigunguCode,
                            contentType = contentType
                        )
                    }
                )
            }
        }
    }
}