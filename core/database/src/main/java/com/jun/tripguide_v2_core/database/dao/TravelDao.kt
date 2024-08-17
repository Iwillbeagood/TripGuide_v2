package com.jun.tripguide_v2_core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.jun.tripguide_v2_core.database.entity.TravelEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TravelDao {

    @Insert
    suspend fun insertTravel(travelEntity: TravelEntity): Long

    @Update
    suspend fun upDateTravel(travelEntity: TravelEntity)

    @Query("SELECT * FROM travels WHERE travelId = :id")
    suspend fun getTravelById(id: String): TravelEntity

    @Query("SELECT * FROM travels")
    fun getTravelsFlow(): Flow<List<TravelEntity>>

    @Query("DELETE FROM travels WHERE travelId = :travelId")
    suspend fun deleteTravelById(travelId: Long)
}