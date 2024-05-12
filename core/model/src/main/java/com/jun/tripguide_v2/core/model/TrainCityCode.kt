package com.jun.tripguide_v2.core.model

enum class TrainCityCode(val cityName: String, val code: Int, val cityName2: String = "없음") {
    SEOUL("서울", 11),
    SEJONG("세종", 12),
    BUSAN("부산", 21),
    DAEGU("대구", 22),
    INCHEON("인천", 23),
    GWANGJU("광주", 24),
    DAEJEON("대전", 25),
    ULSAN("울산", 26),
    GYEONGGI("경기", 31),
    GANGWON("강원", 32),
    CHUNGBUK("충청북도", 33, "충북"),
    CHUNGNAM("충청남도", 34, "충남"),
    JEONBUK("전라북도", 35, "전북"),
    JEONNAM("전라남도", 36, "전남"),
    GYEONGBUK("경상북도", 37, "경북"),
    GYEONGNAM("경상남도", 38, "경남"),
    NOTHING("없음", 0);

    companion object {
        fun findCityCode(name: String): TrainCityCode =
            TrainCityCode.values().find { name.contains(it.cityName) || name.contains(it.cityName2) } ?: NOTHING
    }
}
