package com.jun.tripguide_v2_core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "routes",
    foreignKeys = [
        ForeignKey(
            entity = TravelEntity::class,
            parentColumns = arrayOf("travelId"),
            childColumns = arrayOf("parentId"),
            onDelete = CASCADE
        )
    ]
)
data class RouteEntity(
    @PrimaryKey(autoGenerate = true) val routeId: Int = 0,
    val plusCode: String,
    val orderNum: Int,
    @ColumnInfo(name = "parentId") val parentId: String,
    val title: String,
    val address: String,
    val typeId: String,
    val firstImage: String,
    val mapX: String,
    val mapY: String,
    val startTime: Long,
    val endTime: Long
)