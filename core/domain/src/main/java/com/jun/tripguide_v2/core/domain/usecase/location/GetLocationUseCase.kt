package com.jun.tripguide_v2.core.domain.usecase.location

import com.google.android.gms.maps.model.LatLng
import com.jun.tripguide_v2.core.location.LocationService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocationUseCase @Inject constructor(
    private val locationService: LocationService
) {
    operator fun invoke(): Flow<LatLng?> = locationService.requestLocationUpdates()
}