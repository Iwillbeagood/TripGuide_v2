package com.jun.tripguide_v2_core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.jun.tripguide_v2_core.database.entity.RouteEntity

@Dao
interface RouteDao {

    @Insert
    suspend fun insertRouteAll(routeEntity: List<RouteEntity>)

    @Update
    suspend fun upDateRoute(routeEntity: List<RouteEntity>)

    @Query("SELECT * FROM routes WHERE parentId = :id")
    suspend fun getTravelRoute(id: String): List<RouteEntity>
}