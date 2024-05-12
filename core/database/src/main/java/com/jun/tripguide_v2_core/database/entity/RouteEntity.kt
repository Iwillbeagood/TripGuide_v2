package com.jun.tripguide_v2_core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import java.time.LocalTime

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
    @PrimaryKey(autoGenerate = true) val routeId: Long? = 0,
    val orderNum: Int,
    @ColumnInfo(name = "parentId") val parentId: String,
    val title: String,
    val address: String,
    val typeId: String,
    val firstImage: String,
    val mapX: Double,
    val mapY: Double,
    val time: LocalTime,
    val day: Int,
    val isFirst: Boolean,
    val isLast: Boolean
)