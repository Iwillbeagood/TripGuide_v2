package com.jun.tripguide_v2.core.model

data class FilterValue(
    val title: String,
    val value: String,
    val isSelected: Boolean = false
)

enum class Arrange(val filterValue: FilterValue) {
    Name(FilterValue("제목", "O")), View(FilterValue("조회수", "P", true)),
    Change(FilterValue("수정일", "Q")), Created(FilterValue("생성일", "R"));

    companion object {
        fun getValues(): List<FilterValue> = values().map {
            it.filterValue
        }
    }
}

enum class ContentType(val filterValue: FilterValue) {
    All(FilterValue("전체", "", true)),
    TouristSpot(FilterValue("여행지", "12")),
    CulturalFacility(FilterValue("문화시설", "14")),
    EventFestival(FilterValue("행시/공연/축제", "15")),
    TravelCourse(FilterValue("여행코스", "25")),
    Recreation(FilterValue("레포츠", "28")),
    Accommodation(FilterValue("숙소", "32")),
    Shopping(FilterValue("쇼핑", "38")),
    Restaurant(FilterValue("음식점", "39"));

    companion object {
        fun findByType(type: String): ContentType = values().find { it.filterValue.value == type }!!

        fun getValues(): List<FilterValue> = ContentType.values().map {
            it.filterValue
        }
    }
}