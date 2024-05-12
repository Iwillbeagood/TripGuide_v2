package com.jun.tripguide_v2.core.model

data class TrainInfo(
    val trainType: TrainType = TrainType.Etc(""),
    val trainNo: Int = 0,
    val depPlanedTime: String = "",
    val depPlaceName: String = "",
    val arrPlanedTime: String = "",
    val arrPlaceName: String = "",
    val isSelected: Boolean = false
) {
    private val trainTypeName
        get() = when (trainType) {
            is TrainType.Etc -> trainType.type
            TrainType.Ktx -> "KTX"
            TrainType.KtxSancheon -> "KTX 산천"
        }

    val trainName get() = "$trainTypeName $trainNo"
}