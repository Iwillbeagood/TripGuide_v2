package com.jun.tripguide_v2.core.data.di

import com.jun.tripguide_v2.core.data.api.kakaoapi.KakaoKeywordApi
import com.jun.tripguide_v2.core.data.api.kakaoapi.KakaoRouteAPI
import com.jun.tripguide_v2.core.data.api.tourapi.OpenTouristsApi
import com.jun.tripguide_v2.core.data.api.tourapi.TourAreaCodeApi
import com.jun.tripguide_v2.core.data.api.tourapi.TourCommonInfoApi
import com.jun.tripguide_v2.core.data.api.tourapi.TourDetailIntroApi
import com.jun.tripguide_v2.core.data.api.tourapi.TourFestivalApi
import com.jun.tripguide_v2.core.data.api.tourapi.TourLocationTouristApi
import com.jun.tripguide_v2.core.data.api.tourapi.TourSearchKeywordApi
import com.jun.tripguide_v2.core.data.api.tourapi.TourStayApi
import com.jun.tripguide_v2.core.data.repository.kakao.KakaoKeywordRepositoryImpl
import com.jun.tripguide_v2.core.data.repository.kakao.KakaoRouteRepositoryImpl
import com.jun.tripguide_v2.core.data.repository.room.TravelRepositoryImpl
import com.jun.tripguide_v2.core.data.repository.tourapi.AreaCodeRepositoryImpl
import com.jun.tripguide_v2.core.data.repository.tourapi.CommonInfoRepositoryImpl
import com.jun.tripguide_v2.core.data.repository.tourapi.DetailIntroRepositoryImpl
import com.jun.tripguide_v2.core.data.repository.tourapi.FestivalRepositoryImpl
import com.jun.tripguide_v2.core.data.repository.tourapi.KeywordRepositoryImpl
import com.jun.tripguide_v2.core.data.repository.tourapi.LocationTouristRepositoryImpl
import com.jun.tripguide_v2.core.data.repository.tourapi.StayRepositoryImpl
import com.jun.tripguide_v2.core.data.repository.tourapi.TouristsRepositoryImpl
import com.jun.tripguide_v2.core.data_api.repository.kakao.KakaoKeywordRepository
import com.jun.tripguide_v2.core.data_api.repository.kakao.KakaoRouteRepository
import com.jun.tripguide_v2.core.data_api.repository.room.TravelRepository
import com.jun.tripguide_v2.core.data_api.repository.tourapi.AreaCodeRepository
import com.jun.tripguide_v2.core.data_api.repository.tourapi.CommonInfoRepository
import com.jun.tripguide_v2.core.data_api.repository.tourapi.DetailIntroRepository
import com.jun.tripguide_v2.core.data_api.repository.tourapi.FestivalRepository
import com.jun.tripguide_v2.core.data_api.repository.tourapi.KeywordRepository
import com.jun.tripguide_v2.core.data_api.repository.tourapi.LocationTouristRepository
import com.jun.tripguide_v2.core.data_api.repository.tourapi.StayRepository
import com.jun.tripguide_v2.core.data_api.repository.tourapi.TouristsRepository
import com.jun.tripguide_v2_core.database.dao.TravelDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal object DataModule {

    @Provides
    @Singleton
    fun bindsAreaCodeRepository(
        tourAreaCodeApi: TourAreaCodeApi
    ): AreaCodeRepository = AreaCodeRepositoryImpl(tourAreaCodeApi)

    @Provides
    @Singleton
    fun bindsAreaBaseListRepository(
        openTouristsApi: OpenTouristsApi
    ): TouristsRepository = TouristsRepositoryImpl(openTouristsApi)

    @Provides
    @Singleton
    fun bindsKeywordRepository(
        keywordApi: TourSearchKeywordApi
    ): KeywordRepository = KeywordRepositoryImpl(keywordApi)

    @Provides
    @Singleton
    fun bindsCommonInfoRepository(
        commonInfoApi: TourCommonInfoApi
    ): CommonInfoRepository = CommonInfoRepositoryImpl(commonInfoApi)

    @Provides
    @Singleton
    fun bindsDetailIntroRepository(
        detailIntroApi: TourDetailIntroApi
    ): DetailIntroRepository = DetailIntroRepositoryImpl(detailIntroApi)

    @Provides
    @Singleton
    fun bindsLocationTouristRepository(
        locationTouristApi: TourLocationTouristApi
    ): LocationTouristRepository = LocationTouristRepositoryImpl(locationTouristApi)

    @Provides
    @Singleton
    fun bindsTravelRepository(
        travelDao: TravelDao
    ): TravelRepository = TravelRepositoryImpl(travelDao)

    @Provides
    @Singleton
    fun bindsFestivalRepository(
        tourFestivalApi: TourFestivalApi
    ): FestivalRepository = FestivalRepositoryImpl(tourFestivalApi)

    @Provides
    @Singleton
    fun bindsStayRepository(
        tourStayApi: TourStayApi
    ): StayRepository = StayRepositoryImpl(tourStayApi)

    @Provides
    @Singleton
    fun bindsKakaoLocalKeywordRepository(
        keywordApi: KakaoKeywordApi
    ): KakaoKeywordRepository = KakaoKeywordRepositoryImpl(keywordApi)

    @Provides
    @Singleton
    fun bindsKakaoRouteRepository(
        kakaoRouteAPI: KakaoRouteAPI
    ): KakaoRouteRepository = KakaoRouteRepositoryImpl(kakaoRouteAPI)
}