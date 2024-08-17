package com.jun.tripguide_v2.core.data_api.repository.room

import com.jun.tripguide_v2.core.model.Travel
import kotlinx.coroutines.flow.Flow

interface TravelRepository {

    suspend fun insertTravel(travel: Travel)

    suspend fun updateTravel(travel: Travel)

    suspend fun getTravelById(id: String): Travel

    fun getTravelsFlow(): Flow<List<Travel>>

    suspend fun deleteTravel(travelId: Long)
}