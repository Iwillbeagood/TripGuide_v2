package com.jun.tripguide_v2.core.data.di

import com.jun.tripguide_v2.core.data.repository.AreaCodeRepository
import com.jun.tripguide_v2.core.data.repository.DefaultAreaCodeRepository
import com.jun.tripguide_v2.core.data.repository.DefaultKakaoKeywordRepository
import com.jun.tripguide_v2.core.data.repository.DefaultTravelRepository
import com.jun.tripguide_v2.core.data.repository.KakaoKeywordRepository
import com.jun.tripguide_v2.core.data.repository.TravelRepository
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
    abstract fun bindsTravelRepository(
        travelRepository: DefaultTravelRepository
    ): TravelRepository

    @Binds
    abstract fun bindsKakaoLocalKeywordRepository(
        kakaoLocalKeywordRepository: DefaultKakaoKeywordRepository
    ): KakaoKeywordRepository
}