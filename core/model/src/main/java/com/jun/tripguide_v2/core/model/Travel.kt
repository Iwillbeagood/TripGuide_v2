package com.jun.tripguide_v2.core.model

data class Travel(
    val travelId: Long = 0,
    val startPlace: Tourist,
    val startDate: Long = 0L,
    val endPlace: Tourist = Tourist(),
    val endDate: Long = 0L,
    val trainInfo: TrainInfo = TrainInfo(),
    val returnTrainInfo: TrainInfo = TrainInfo(),
    val places: List<Tourist> = emptyList(),
    val title: String
) {
    val images get() = places.map {
        it.firstImage
    }
}
