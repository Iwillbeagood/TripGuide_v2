package com.jun.tripguide_v2.core.data.di

import com.jun.tripguide_v2.core.data.repository.tourapi.AreaCodeRepository
import com.jun.tripguide_v2.core.data.repository.tourapi.DefaultAreaCodeRepository
import com.jun.tripguide_v2.core.data.repository.DefaultKakaoKeywordRepository
import com.jun.tripguide_v2.core.data.repository.DefaultTravelRepository
import com.jun.tripguide_v2.core.data.repository.KakaoKeywordRepository
import com.jun.tripguide_v2.core.data.repository.TravelRepository
import com.jun.tripguide_v2.core.data.repository.tourapi.AreaBaseListRepository
import com.jun.tripguide_v2.core.data.repository.tourapi.DefaultAreaBaseListRepository
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
        repository: DefaultAreaBaseListRepository
    ): AreaBaseListRepository

    @Binds
    abstract fun bindsTravelRepository(
        travelRepository: DefaultTravelRepository
    ): TravelRepository

    @Binds
    abstract fun bindsKakaoLocalKeywordRepository(
        kakaoLocalKeywordRepository: DefaultKakaoKeywordRepository
    ): KakaoKeywordRepository
}