package com.jun.tripguide_v2.core.data.mapper

import com.jun.tripguide_v2.core.model.DestinationCode
import com.jun.tripguide_v2.core.model.TrainInfo
import com.jun.tripguide_v2.core.model.TrainType
import com.jun.tripguide_v2.core.model.Travel
import com.jun.tripguide_v2_core.database.entity.TrainEntity
import com.jun.tripguide_v2_core.database.entity.TravelEntity

fun Travel.toData(): TravelEntity = TravelEntity(
    title = this.destination.destinationString + "여행",
    startPlace = this.startPlace,
    startDate = this.startDate,
    endPlace = this.endPlace,
    endDate = this.endDate,
    areaCode = this.destination.areaCode,
    sigunguCode = this.destination.sigunguCode,
    trainEntity = TrainEntity(
        trainType = trainInfo.trainName,
        trainNo = trainInfo.trainNo,
        depPlanedTime = trainInfo.depPlanedTime,
        depPlaceName = trainInfo.depPlaceName,
        arrPlanedTime = trainInfo.arrPlanedTime,
        arrPlaceName = trainInfo.arrPlaceName
    ),
    returnTrainEntity = TrainEntity(
        trainType = returnTrainInfo.trainName,
        trainNo = trainInfo.trainNo,
        depPlanedTime = returnTrainInfo.depPlanedTime,
        depPlaceName = returnTrainInfo.depPlaceName,
        arrPlanedTime = returnTrainInfo.arrPlanedTime,
        arrPlaceName = returnTrainInfo.arrPlaceName
    ),
    places = this.places
)

fun TravelEntity.toData(): Travel = Travel(
    travelId = this.travelId,
    destination = DestinationCode(this.areaCode, this.sigunguCode),
    startPlace = this.startPlace,
    startDate = this.startDate,
    endPlace = this.endPlace,
    endDate = this.endDate,
    trainInfo = TrainInfo(
        trainType = if (trainEntity.trainType.contains("KTX-산천"))
            TrainType.KtxSancheon
        else if (trainEntity.trainType.contains("KTX"))
            TrainType.Ktx
        else
            TrainType.Etc(trainEntity.trainType),
        trainNo = trainEntity.trainNo,
        depPlanedTime = trainEntity.depPlanedTime,
        depPlaceName = trainEntity.depPlaceName,
        arrPlanedTime = trainEntity.arrPlanedTime,
        arrPlaceName = trainEntity.arrPlaceName
    ),
    returnTrainInfo = TrainInfo(
        trainType = if (returnTrainEntity.trainType.contains("KTX-산천"))
            TrainType.KtxSancheon
        else if (returnTrainEntity.trainType.contains("KTX"))
            TrainType.Ktx
        else
            TrainType.Etc(returnTrainEntity.trainType),
        trainNo = returnTrainEntity.trainNo,
        depPlanedTime = returnTrainEntity.depPlanedTime,
        depPlaceName = returnTrainEntity.depPlaceName,
        arrPlanedTime = returnTrainEntity.arrPlanedTime,
        arrPlaceName = returnTrainEntity.arrPlaceName
    ),
    places = this.places
)