package com.jun.tripguide_v2.core.data.mapper

import com.jun.tripguide_v2.core.data.api.tourapi.model.detailIntro.AccommodationItem
import com.jun.tripguide_v2.core.data.api.tourapi.model.detailIntro.CulturalFacilityItem
import com.jun.tripguide_v2.core.data.api.tourapi.model.detailIntro.EventFestivalItem
import com.jun.tripguide_v2.core.data.api.tourapi.model.detailIntro.ItemTemplate
import com.jun.tripguide_v2.core.data.api.tourapi.model.detailIntro.RecreationItem
import com.jun.tripguide_v2.core.data.api.tourapi.model.detailIntro.RestaurantItem
import com.jun.tripguide_v2.core.data.api.tourapi.model.detailIntro.ShoppingItem
import com.jun.tripguide_v2.core.data.api.tourapi.model.detailIntro.TouristSpotItem
import com.jun.tripguide_v2.core.data.api.tourapi.model.detailIntro.TravelCourseItem
import com.jun.tripguide_v2.core.model.tourApi.DetailIntro
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun ItemTemplate.toDetails(): List<DetailIntro> {
    return when(this) {
        is TouristSpotItem -> listOf(
            infocenter.toDetail("문의 및 안내"),
            usetime.toDetail("이용 시간"),
            expguide.toDetail("체험 안내"),
            parking.toDetail("주차 시설")
        )
        is CulturalFacilityItem -> listOf(
            infocenterculture.toDetail("문의 및 안내"),
            parkingculture.toDetail("주차 시설"),
            usetimeculture.toDetail("이용 시간"),
            spendtime.toDetail("관람 소요 시간")
        )
        is EventFestivalItem -> listOf(
            program.toDetail("행사 프로그램"),
            eventstartdate.dateFormatter().toDetail("행사 시작일"),
            eventenddate.dateFormatter().toDetail("행사 종료일"),
            eventplace.toDetail("행사 장소"),
            playtime.toDetail("공연 시간"),
            spendtimefestival.toDetail("관람 소요 시간"),
            usetimefestival.toDetail("이용 요금")
        )
        is TravelCourseItem -> listOf(
            infocentertourcourse.toDetail("문의 및 안내"),
            distance.toDetail("코스 총 거리"),
            schedule.toDetail("코스 일정"),
            taketime.toDetail("코스 총 소요 시간")
        )
        is RecreationItem -> listOf(
            infocenterleports.toDetail("문의 및 안내"),
            parkingleports.toDetail("주차 시설"),
            reservation.toDetail("예약 안내"),
            usefeeleports.toDetail("입장료"),
            usetimeleports.toDetail("이용 시간")
        )
        is AccommodationItem -> listOf(
            infocenterlodging.toDetail("문의 및 안내"),
            checkintime.toDetail("체크인 시간"),
            checkouttime.toDetail("체크아웃 시간"),
            reservationlodging.toDetail("예약 안내"),
            parkinglodging.toDetail("주차 시설")
        )
        is ShoppingItem -> listOf(
            infocentershopping.toDetail("문의 및 안내"),
            opentime.toDetail("영업 시간"),
            parkingshopping.toDetail("주차 시설"),
            shopguide.toDetail("매장 안내")
        )
        is RestaurantItem -> listOf(
            infocenterfood.toDetail("문의 및 안내"),
            firstmenu.toDetail("대표 메뉴"),
            opentimefood.toDetail("영업 시간"),
            parkingfood.toDetail("주차 시설")
        )
    }
}

fun String?.toDetail(title: String) = DetailIntro(title, this?.htmlToString().orEmpty())

private fun String.dateFormatter(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")
    val date = LocalDate.parse(this, formatter)
    return date.format(DateTimeFormatter.ofPattern("yyyy년 M월 d일"))
}