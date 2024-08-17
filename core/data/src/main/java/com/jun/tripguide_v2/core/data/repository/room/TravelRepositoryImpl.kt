package com.jun.tripguide_v2.core.data.repository.room

import com.jun.tripguide_v2.core.data.mapper.toData
import com.jun.tripguide_v2.core.data_api.repository.room.TravelRepository
import com.jun.tripguide_v2.core.model.Travel
import com.jun.tripguide_v2_core.database.dao.TravelDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TravelRepositoryImpl @Inject constructor(
    private val travelDao: TravelDao
) : TravelRepository {

    override suspend fun insertTravel(travel: Travel): Long {
        return travelDao.insertTravel(travel.toData())
    }

    override suspend fun updateTravel(travel: Travel) {
        travelDao.upDateTravel(travel.toData())
    }

    override suspend fun getTravelById(id: String): Travel {
        return travelDao.getTravelById(id).toData()
    }

    override fun getTravelsFlow(): Flow<List<Travel>> {
        return travelDao.getTravelsFlow().map {
            it.map { it.toData() }
        }
    }

    override suspend fun deleteTravel(travelId: Long) {
        travelDao.deleteTravelById(travelId)
    }
}