package com.jun.tripguide_v2.core.data.api.tourapi.model.detailIntro

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("12")
data class TouristSpotItem(
    val parking: String,
    val usetime: String,
    val expguide: String,
    val infocenter: String,
): ItemTemplate

@Serializable
@SerialName("14")
data class CulturalFacilityItem(
    val infocenterculture: String,
    val parkingculture: String,
    val usetimeculture: String,
    val spendtime: String
): ItemTemplate

@Serializable
@SerialName("15")
data class EventFestivalItem(
    val eventstartdate: String,
    val eventenddate: String,
    val eventplace: String,
    val playtime: String,
    val program: String,
    val spendtimefestival: String,
    val usetimefestival: String
): ItemTemplate

@Serializable
@SerialName("25")
data class TravelCourseItem(
    val distance: String,
    val infocentertourcourse: String,
    val schedule: String,
    val taketime: String
): ItemTemplate

@Serializable
@SerialName("28")
data class RecreationItem(
    val infocenterleports: String,
    val parkingleports: String,
    val reservation: String,
    val usefeeleports: String,
    val usetimeleports: String,
): ItemTemplate

@Serializable
@SerialName("32")
data class AccommodationItem(
    val checkintime: String,
    val checkouttime: String,
    val parkinglodging: String,
    val infocenterlodging: String,
    val reservationlodging: String
): ItemTemplate

@Serializable
@SerialName("38")
data class ShoppingItem(
    val infocentershopping: String,
    val opentime: String,
    val parkingshopping: String,
    val shopguide: String,
): ItemTemplate


@Serializable
@SerialName("39")
data class RestaurantItem(
    val firstmenu: String,
    val infocenterfood: String,
    val opentimefood: String,
    val parkingfood: String
): ItemTemplate