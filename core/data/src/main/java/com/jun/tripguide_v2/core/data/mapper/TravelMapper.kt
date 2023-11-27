package com.jun.tripguide_v2.core.data.mapper

import com.jun.tripguide_v2.core.model.DestinationCode
import com.jun.tripguide_v2.core.model.MeansType
import com.jun.tripguide_v2.core.model.StartingPoint
import com.jun.tripguide_v2.core.model.Travel
import com.jun.tripguide_v2_core.database.entity.TravelEntity

fun Travel.toData(): TravelEntity = TravelEntity(
    travelId = this.travelId,
    title = this.title,
    destination = this.destination.destinationString,
    areaCode = this.destination.areaCode,
    sigunguCode = this.destination.sigunguCode,
    startingPoint = this.startingPoint.name,
    startingX = this.startingPoint.x,
    startingY = this.startingPoint.y,
    startDate = this.startDate,
    endDate = this.endDate,
    startTime = this.startTime,
    meansType = this.meansType.value,
    startMeansLocation = this.startMeansLocation,
    endMeansLocation = this.startMeansLocation,
    isOrdered = this.isOrdered
)

fun TravelEntity.toData(): Travel = Travel(
    travelId = this.travelId,
    title = this.title,
    destination = DestinationCode(this.areaCode, this.sigunguCode, this.destination),
    startingPoint = StartingPoint(this.startingPoint, this.startingX, this.startingY),
    startDate = this.startDate,
    endDate = this.endDate,
    startTime = this.startTime,
    meansType = MeansType.values().find { it.value == this.meansType } ?: MeansType.CAR,
    startMeansLocation = this.startMeansLocation,
    endMeansLocation = this.startMeansLocation,
    isOrdered = this.isOrdered
)