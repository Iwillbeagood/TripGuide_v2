package com.jun.tripguide_v2.core.data.mapper

import com.jun.tripguide_v2.core.data.api.model.areaCode.AreaCodeItem
import com.jun.tripguide_v2.core.model.AreaCode
import com.jun.tripguide_v2.core.model.Travel
import com.jun.tripguide_v2_core.database.TravelEntity

fun Travel.toData(): TravelEntity = TravelEntity(
    travelId = this.travelId,
    title = this.title,
    destination = this.destination,
    startingPoint = this.startingPoint,
    startDate = this.startDate,
    endDate = this.endDate,
    startTime = this.startTime,
    endTime = this.endTime,
    meansType = this.meansType,
    startMeansLocation = this.startMeansLocation,
    endMeansLocation = this.startMeansLocation
)

fun TravelEntity.toData(): Travel = Travel(
    travelId = this.travelId,
    title = this.title,
    destination = this.destination,
    startingPoint = this.startingPoint,
    startDate = this.startDate,
    endDate = this.endDate,
    startTime = this.startTime,
    endTime = this.endTime,
    meansType = this.meansType,
    startMeansLocation = this.startMeansLocation,
    endMeansLocation = this.startMeansLocation
)