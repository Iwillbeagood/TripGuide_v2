package com.jun.tripguide_v2_core.database.di

import android.content.Context
import com.jun.tripguide_v2_core.database.AppDatabase
import com.jun.tripguide_v2_core.database.dao.TravelDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = AppDatabase.getDatabase(context)


    @Singleton
    @Provides
    fun provideTravelDaoRepository(database: AppDatabase
    ): TravelDao = database.travelDao
}