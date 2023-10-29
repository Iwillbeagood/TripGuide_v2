package com.jun.tripguide_v2.core.data.mapper

import com.jun.tripguide_v2.core.model.DestinationCode
import com.jun.tripguide_v2.core.model.MeansType
import com.jun.tripguide_v2.core.model.Travel
import com.jun.tripguide_v2_core.database.TravelEntity

fun Travel.toData(): TravelEntity = TravelEntity(
    travelId = this.travelId,
    title = this.title,
    destination = this.destination.destinationString,
    areaCode = this.destination.areaCode,
    sigunguCode = this.destination.sigunguCode,
    startingPoint = this.startingPoint,
    startDate = this.startDate,
    endDate = this.endDate,
    startTime = this.startTime,
    endTime = this.endTime,
    meansType = this.meansType.value,
    startMeansLocation = this.startMeansLocation,
    endMeansLocation = this.startMeansLocation
)

fun TravelEntity.toData(): Travel = Travel(
    travelId = this.travelId,
    title = this.title,
    destination = DestinationCode(this.areaCode, this.sigunguCode, this.destination),
    startingPoint = this.startingPoint,
    startDate = this.startDate,
    endDate = this.endDate,
    startTime = this.startTime,
    endTime = this.endTime,
    meansType = MeansType.values().find { it.value == this.meansType } ?: MeansType.CAR,
    startMeansLocation = this.startMeansLocation,
    endMeansLocation = this.startMeansLocation
)