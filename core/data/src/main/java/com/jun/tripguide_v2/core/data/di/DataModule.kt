package com.jun.tripguide_v2.core.data.di

import com.jun.tripguide_v2.core.data.repository.kakao.KakaoKeywordRepositoryImpl
import com.jun.tripguide_v2.core.data.repository.kakao.KakaoRouteRepositoryImpl
import com.jun.tripguide_v2.core.data.repository.kakao.KakaoKeywordRepository
import com.jun.tripguide_v2.core.data.repository.kakao.KakaoRouteRepository
import com.jun.tripguide_v2.core.data.repository.room.RouteRepositoryImpl
import com.jun.tripguide_v2.core.data.repository.room.TravelRepositoryImpl
import com.jun.tripguide_v2.core.data.repository.room.RouteRepository
import com.jun.tripguide_v2.core.data.repository.room.TravelRepository
import com.jun.tripguide_v2.core.data.repository.tourapi.TouristsRepository
import com.jun.tripguide_v2.core.data.repository.tourapi.AreaCodeRepository
import com.jun.tripguide_v2.core.data.repository.tourapi.CommonInfoRepository
import com.jun.tripguide_v2.core.data.repository.tourapi.CommonInfoRepositoryImpl
import com.jun.tripguide_v2.core.data.repository.tourapi.TouristsRepositoryImpl
import com.jun.tripguide_v2.core.data.repository.tourapi.AreaCodeRepositoryImpl
import com.jun.tripguide_v2.core.data.repository.tourapi.KeywordRepositoryImpl
import com.jun.tripguide_v2.core.data.repository.tourapi.DetailIntroRepository
import com.jun.tripguide_v2.core.data.repository.tourapi.DetailIntroRepositoryImpl
import com.jun.tripguide_v2.core.data.repository.tourapi.KeywordRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataModule {

    @Binds
    abstract fun bindsAreaCodeRepository(
        repository: AreaCodeRepositoryImpl
    ): AreaCodeRepository

    @Binds
    abstract fun bindsAreaBaseListRepository(
        repository: TouristsRepositoryImpl
    ): TouristsRepository

    @Binds
    abstract fun bindsKeywordRepository(
        repository: KeywordRepositoryImpl
    ): KeywordRepository

    @Binds
    abstract fun bindsCommonInfoRepository(
        repository: CommonInfoRepositoryImpl
    ): CommonInfoRepository

    @Binds
    abstract fun bindsDetailIntroRepository(
        repository: DetailIntroRepositoryImpl
    ): DetailIntroRepository

    @Binds
    abstract fun bindsTravelRepository(
        travelRepository: TravelRepositoryImpl
    ): TravelRepository

    @Binds
    abstract fun bindsRouteRepository(
        routeRepository: RouteRepositoryImpl
    ): RouteRepository

    @Binds
    abstract fun bindsKakaoLocalKeywordRepository(
        kakaoLocalKeywordRepository: KakaoKeywordRepositoryImpl
    ): KakaoKeywordRepository

    @Binds
    abstract fun bindsKakaoRouteRepository(
        kakaoRouteRepository: KakaoRouteRepositoryImpl
    ): KakaoRouteRepository
}