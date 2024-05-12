package com.jun.tripguide_v2.core.data.mapper

import com.jun.tripguide_v2_core.database.entity.RouteEntity
import com.jun.tripguide_v2.core.model.Route

fun RouteEntity.toData(): Route = Route(
    id = this.routeId,
    orderNum = this.orderNum,
    travelId = this.parentId,
    title = this.title,
    address = this.address,
    type = this.typeId,
    firstImage = this.firstImage,
    mapX = this.mapX,
    mapY = this.mapY,
    time = this.time,
    day = this.day,
    isFirst = this.isFirst,
    isLast = this.isLast
)

fun Route.toData(): RouteEntity = RouteEntity(
    routeId = this.id,
    orderNum = this.orderNum,
    parentId = this.travelId,
    title = this.title,
    address = this.address,
    typeId = this.type,
    firstImage = this.firstImage,
    mapX = this.mapX,
    mapY = this.mapY,
    time = this.time,
    day = this.day,
    isFirst = this.isFirst,
    isLast = this.isLast
)