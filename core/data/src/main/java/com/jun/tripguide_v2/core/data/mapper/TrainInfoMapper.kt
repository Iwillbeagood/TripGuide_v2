package com.jun.tripguide_v2.core.data.mapper

import com.jun.tripguide_v2.core.data.api.trainapi.model.info.TrainInfoItem
import com.jun.tripguide_v2.core.model.TrainInfo
import com.jun.tripguide_v2.core.model.TrainType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun TrainInfoItem.toTrainInfo() = TrainInfo(
    trainType = if (traingradename.contains("KTX-산천"))
        TrainType.KtxSancheon
    else if (traingradename.contains("KTX"))
        TrainType.Ktx
    else
        TrainType.Etc(traingradename),
    trainNo = trainno,
    depPlanedTime = longToTimeString(depplandtime),
    depPlaceName = depplacename,
    arrPlanedTime = longToTimeString(arrplandtime),
    arrPlaceName = arrplacename
)

private fun longToTimeString(longValue: Long): String {
    val dateTimeString = longValue.toString()
    val formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss")
    val localDateTime = LocalDateTime.parse(dateTimeString, formatter)

    return localDateTime.format(DateTimeFormatter.ofPattern("HH:mm"))
}