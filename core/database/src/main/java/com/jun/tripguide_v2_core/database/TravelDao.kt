package com.jun.tripguide_v2_core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TravelDao {

    @Insert
    suspend fun insertTravel(travelEntity: TravelEntity)

    @Update
    suspend fun upDateTravel(travelEntity: TravelEntity)

    @Query("SELECT * FROM travels WHERE travelId = :id")
    suspend fun getTravelById(id: Int): TravelEntity


}