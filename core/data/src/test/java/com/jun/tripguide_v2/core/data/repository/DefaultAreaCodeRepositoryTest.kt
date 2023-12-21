package com.jun.tripguide_v2.core.data.repository

import com.jun.tripguide_v2.core.data.api.FakeTourAreaCodeApi
import com.jun.tripguide_v2.core.data.repository.Temp.queryParams
import com.jun.tripguide_v2.core.data.repository.tourapi.AreaCodeRepository
import com.jun.tripguide_v2.core.data.repository.tourapi.AreaCodeRepositoryImpl
import com.jun.tripguide_v2.core.model.tourApi.AreaCode
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DefaultAreaCodeRepositoryTest : StringSpec() {

    init {
        val repository: AreaCodeRepository = AreaCodeRepositoryImpl(
            tourAreaCodeApi = FakeTourAreaCodeApi()
        )

        "기본 지역 코드 역직렬화 테스트" {
            val expected = AreaCode(
                code = "1",
                name = "서울"
            )

            val actual = repository.getDefaultAreaCode(queryParams)
            actual.first() shouldBe expected
        }

        "지역 코드 역직렬화 테스트" {
            val expected = AreaCode(
                code = "1",
                name = "강남구"
            )

            val actual = repository.getAreaCode(queryParams, "1")
            actual.first() shouldBe expected
        }
    }
}

object Temp {

    const val TOUR_API_API_KEY = "LUjHE2JtNIM0j7H1yjIJnSkVhIS6p6I6R0y5F235iEiBQL9it8MXwm6mjNUFYGbnDpVFsqLgeYnIqcMNF83ilg%3D%3D"

    val queryParams = mutableMapOf(
        "numOfRows" to "30",
        "MobileOS" to "AND",
        "MobileApp" to "TripGuide_v2",
        "serviceKey" to TOUR_API_API_KEY,
        "_type" to "JSON"
    )
}