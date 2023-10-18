package com.jun.tripguide_v2.core.data.repository

import com.jun.tripguide_v2.core.data.mapper.toData
import com.jun.tripguide_v2.core.model.Travel
import com.jun.tripguide_v2_core.database.TravelDao
import javax.inject.Inject

class DefaultTravelRepository @Inject constructor(
    private val travelDao: TravelDao
) : TravelRepository {

    override suspend fun insertTravel(travel: Travel) {
        travelDao.insertTravel(travel.toData())
    }

    override suspend fun updateTravel(travel: Travel) {
        travelDao.upDateTravel(travel.toData())
    }

    override suspend fun getTravelById(id: Int): Travel {
        return travelDao.getTravelById(id).toData()
    }
}