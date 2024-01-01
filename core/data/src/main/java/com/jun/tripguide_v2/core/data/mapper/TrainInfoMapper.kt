package com.jun.tripguide_v2.core.data.mapper

import com.jun.tripguide_v2.core.data.api.trainapi.model.info.TrainInfoItem
import com.jun.tripguide_v2.core.model.TrainInfo

fun TrainInfoItem.toTrainInfo() = TrainInfo("$traingradename $trainno", depplacename, depplandtime)