package com.jun.tripguide_v2.core.domain.usecase.tourapi

import android.os.Build
import androidx.annotation.RequiresApi
import com.jun.tripguide_v2.core.data.repository.tourapi.LocationTouristRepository
import com.jun.tripguide_v2.core.domain.Const
import com.jun.tripguide_v2.core.domain.usecase.location.GetLocationUseCase
import com.jun.tripguide_v2.core.model.tourApi.LocationBasedTourist
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.S)
class GetTouristByCurLocationUsecase @Inject constructor(
    private val getLocationUseCase: GetLocationUseCase,
    private val locationTouristRepository: LocationTouristRepository
) {

    suspend operator fun invoke(pageNo: Int = 1): Flow<List<LocationBasedTourist>> {
        return getLocationUseCase().map {
            locationTouristRepository.getLocationBasedTourists(
                Const.queryParams,
                pageNo,
                it?.longitude ?: 0.0,
                it?.latitude ?: 0.0
            )
        }
    }
}