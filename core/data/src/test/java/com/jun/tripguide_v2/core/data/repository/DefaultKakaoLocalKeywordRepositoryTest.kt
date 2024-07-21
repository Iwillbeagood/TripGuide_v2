package com.jun.tripguide_v2.core.data.repository

import com.jun.tripguide_v2.core.data.api.FakeKakaoLocalKeywordApi
import com.jun.tripguide_v2.core.data.repository.kakao.KakaoKeywordRepositoryImpl
import com.jun.tripguide_v2.core.model.Address
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DefaultKakaoLocalKeywordRepositoryTest : StringSpec() {

    init {
        val repository: KakaoKeywordRepository = KakaoKeywordRepositoryImpl(
            keywordApi = FakeKakaoLocalKeywordApi()
        )

        "키워드 주소 검색 역직렬화 테스트" {
            val expected = Address(
                id = 26338954,
                name = "카카오프렌즈 코엑스점",
                address = "서울 강남구 영동대로 513",
                x = "127.05902969025047",
                y = "37.51207412593136"
            )

            val actual = repository.getAddressByKeyword(KAKAO_API_KEY, "카카오프렌즈")
            actual.first() shouldBe expected
        }
    }
}

const val KAKAO_API_KEY = "KakaoAK 666dd8d84bdf766d402bd0f500e08a4d"
