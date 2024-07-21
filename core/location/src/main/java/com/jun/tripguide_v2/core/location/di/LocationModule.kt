package com.jun.tripguide_v2.core.location.di

import android.content.Context
import com.google.android.gms.location.LocationServices
import com.jun.tripguide_v2.core.location.LocationService
import com.jun.tripguide_v2.core.location.LocationServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocationModule {

    @Singleton
    @Provides
    fun provideLocationClient(
        @ApplicationContext context: Context
    ): LocationService =
        LocationServiceImpl(
            context,
            LocationServices.getFusedLocationProviderClient(context)
        )
}