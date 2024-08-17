package com.jun.tripguide_v2.core.data.mapper

import com.jun.tripguide_v2.core.model.Travel
import com.jun.tripguide_v2_core.database.entity.TravelEntity

fun Travel.toData(): TravelEntity = TravelEntity(
    title = this.title,
    startPlace = this.startPlace,
    startDate = this.startDate,
    endDate = this.endDate,
    places = this.places
)

fun TravelEntity.toData(): Travel = Travel(
    travelId = this.travelId,
    title = this.title,
    startPlace = this.startPlace,
    startDate = this.startDate,
    endDate = this.endDate,
    places = this.places
)