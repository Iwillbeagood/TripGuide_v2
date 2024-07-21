package com.jun.tripguide_v2.core.data_api.room

import com.jun.tripguide_v2.core.model.Travel

interface TravelRepository {

    suspend fun insertTravel(travel: Travel): Long

    suspend fun updateTravel(travel: Travel)

    suspend fun getTravelById(id: String): Travel

    suspend fun getTravels(): List<Travel>

    suspend fun deleteTravel(travel: Travel)
}