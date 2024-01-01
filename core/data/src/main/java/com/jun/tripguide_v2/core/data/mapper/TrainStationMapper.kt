package com.jun.tripguide_v2.core.data.mapper

import com.jun.tripguide_v2.core.data.api.trainapi.model.station.StationItem
import com.jun.tripguide_v2.core.model.TrainStation

fun StationItem.toTrainStation() = TrainStation(nodeid, nodename)