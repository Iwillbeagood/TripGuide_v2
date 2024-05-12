package com.jun.tripguide_v2_core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.jun.tripguide_v2_core.database.entity.RouteEntity

@Dao
interface RouteDao {

    @Insert
    suspend fun insertRouteAll(vararg routeEntity: RouteEntity)

    @Update
    suspend fun upDateRoute(vararg routeEntity: RouteEntity)

    @Query("SELECT * FROM routes WHERE parentId = :id")
    suspend fun getTravelRoute(id: String): List<RouteEntity>

    @Delete
    suspend fun deleteRoute(vararg routeEntity: RouteEntity)

    @Query("DELETE FROM routes WHERE parentId = :id")
    suspend fun deleteAllRoute(id: String)
}