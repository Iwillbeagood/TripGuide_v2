package com.jun.tripguide_v2_core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.jun.tripguide_v2_core.database.entity.TravelEntity

@Dao
interface TravelDao {

    @Insert
    suspend fun insertTravel(travelEntity: TravelEntity): Long

    @Update
    suspend fun upDateTravel(travelEntity: TravelEntity)

    @Query("SELECT * FROM travels WHERE travelId = :id")
    suspend fun getTravelById(id: String): TravelEntity

    @Query("SELECT * FROM travels")
    suspend fun getTravels(): List<TravelEntity>

    @Delete
    suspend fun deleteTravel(travelEntity: TravelEntity)
}