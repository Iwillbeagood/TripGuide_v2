package com.jun.tripguide_v2.core.data.mapper

import com.jun.tripguide_v2_core.database.entity.RouteEntity
import com.jun.tripguide_v2.core.model.Route

fun RouteEntity.toData(): Route = Route(
    orderNum = this.orderNum,
    travelId = this.parentId,
    title = this.title,
    address = this.address,
    typeId = this.typeId,
    firstImage = this.firstImage,
    mapX = this.mapX,
    mapY = this.mapY,
    startTime = this.startTime,
    endTime = this.endTime
)

fun Route.toData(): RouteEntity = RouteEntity(
    orderNum = this.orderNum,
    plusCode = this.plusCode,
    parentId = this.travelId,
    title = this.title,
    address = this.address,
    typeId = this.typeId,
    firstImage = this.firstImage,
    mapX = this.mapX,
    mapY = this.mapY,
    startTime = this.startTime,
    endTime = this.endTime
)