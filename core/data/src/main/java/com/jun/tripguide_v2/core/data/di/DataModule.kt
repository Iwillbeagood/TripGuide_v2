package com.jun.tripguide_v2.core.data.di

import com.jun.tripguide_v2.core.data.repository.kakao.DefaultKakaoKeywordRepository
import com.jun.tripguide_v2.core.data.repository.kakao.DefaultKakaoRouteRepository
import com.jun.tripguide_v2.core.data.repository.kakao.KakaoKeywordRepository
import com.jun.tripguide_v2.core.data.repository.kakao.KakaoRouteRepository
import com.jun.tripguide_v2.core.data.repository.room.DefaultRouteRepository
import com.jun.tripguide_v2.core.data.repository.room.DefaultTravelRepository
import com.jun.tripguide_v2.core.data.repository.room.RouteRepository
import com.jun.tripguide_v2.core.data.repository.room.TravelRepository
import com.jun.tripguide_v2.core.data.repository.tourapi.TouristsRepository
import com.jun.tripguide_v2.core.data.repository.tourapi.AreaCodeRepository
import com.jun.tripguide_v2.core.data.repository.tourapi.DefaultTouristsRepository
import com.jun.tripguide_v2.core.data.repository.tourapi.DefaultAreaCodeRepository
import com.jun.tripguide_v2.core.data.repository.tourapi.DefaultKeywordRepository
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
        repository: DefaultAreaCodeRepository
    ): AreaCodeRepository

    @Binds
    abstract fun bindsAreaBaseListRepository(
        repository: DefaultTouristsRepository
    ): TouristsRepository

    @Binds
    abstract fun bindsKeywordRepository(
        repository: DefaultKeywordRepository
    ): KeywordRepository

    @Binds
    abstract fun bindsTravelRepository(
        travelRepository: DefaultTravelRepository
    ): TravelRepository

    @Binds
    abstract fun bindsRouteRepository(
        routeRepository: DefaultRouteRepository
    ): RouteRepository

    @Binds
    abstract fun bindsKakaoLocalKeywordRepository(
        kakaoLocalKeywordRepository: DefaultKakaoKeywordRepository
    ): KakaoKeywordRepository

    @Binds
    abstract fun bindsKakaoRouteRepository(
        kakaoRouteRepository: DefaultKakaoRouteRepository
    ): KakaoRouteRepository
}