package com.jun.tripguide_v2.core.data.repository

import com.jun.tripguide_v2.core.model.Travel

interface TravelRepository {

    suspend fun insertTravel(travel: Travel)

    suspend fun updateTravel(travel: Travel)

    suspend fun getTravelById(id: Int): Travel
}