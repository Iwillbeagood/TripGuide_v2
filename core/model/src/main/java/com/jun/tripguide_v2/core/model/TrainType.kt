package com.jun.tripguide_v2.core.model

sealed interface TrainType {
    object Ktx: TrainType
    object KtxSancheon: TrainType
    data class Etc(val type: String): TrainType
}
