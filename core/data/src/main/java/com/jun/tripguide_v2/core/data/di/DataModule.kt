package com.jun.tripguide_v2.core.data.di

import android.content.Context
import com.jun.tripguide_v2.core.data.api.TourAreaCodeApi
import com.jun.tripguide_v2.core.data.api.fake.AssetsTourAreaCodeApi
import com.jun.tripguide_v2.core.data.repository.AreaCodeRepository
import com.jun.tripguide_v2.core.data.repository.DefaultAreaCodeRepository
import com.jun.tripguide_v2.core.data.repository.DefaultTravelRepository
import com.jun.tripguide_v2.core.data.repository.TravelRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataModule {

//    @Binds
//    abstract fun bindsAreaCodeRepository(
//        repository: DefaultAreaCodeRepository
//    ): AreaCodeRepository

    @Binds
    abstract fun bindsTravelRepository(
        travelRepository: DefaultTravelRepository
    ): TravelRepository


    @Module
    @InstallIn(SingletonComponent::class)
    internal object FakeModule {

        @Provides
        @Singleton
        fun provideAreaCodeRepository(
            tourAreaCodeApi: TourAreaCodeApi
        ): AreaCodeRepository = DefaultAreaCodeRepository(tourAreaCodeApi)

        @Provides
        @Singleton
        fun provideTourAreaCodeApi(
            @ApplicationContext context: Context
        ): AssetsTourAreaCodeApi = AssetsTourAreaCodeApi(context)
    }
}