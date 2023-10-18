package com.jun.tripguide_v2.core.data.di;

@dagger.Module
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b!\u0018\u00002\u00020\u0001:\u0001\u0007B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\'\u00a8\u0006\b"}, d2 = {"Lcom/jun/tripguide_v2/core/data/di/DataModule;", "", "()V", "bindsTravelRepository", "Lcom/jun/tripguide_v2/core/data/repository/TravelRepository;", "travelRepository", "Lcom/jun/tripguide_v2/core/data/repository/DefaultTravelRepository;", "FakeModule", "data_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public abstract class DataModule {
    
    public DataModule() {
        super();
    }
    
    @dagger.Binds
    @org.jetbrains.annotations.NotNull
    public abstract com.jun.tripguide_v2.core.data.repository.TravelRepository bindsTravelRepository(@org.jetbrains.annotations.NotNull
    com.jun.tripguide_v2.core.data.repository.DefaultTravelRepository travelRepository);
    
    @dagger.Module
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c1\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0012\u0010\u0007\u001a\u00020\b2\b\b\u0001\u0010\t\u001a\u00020\nH\u0007\u00a8\u0006\u000b"}, d2 = {"Lcom/jun/tripguide_v2/core/data/di/DataModule$FakeModule;", "", "()V", "provideAreaCodeRepository", "Lcom/jun/tripguide_v2/core/data/repository/AreaCodeRepository;", "tourAreaCodeApi", "Lcom/jun/tripguide_v2/core/data/api/TourAreaCodeApi;", "provideTourAreaCodeApi", "Lcom/jun/tripguide_v2/core/data/api/fake/AssetsTourAreaCodeApi;", "context", "Landroid/content/Context;", "data_debug"})
    @dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
    public static final class FakeModule {
        @org.jetbrains.annotations.NotNull
        public static final com.jun.tripguide_v2.core.data.di.DataModule.FakeModule INSTANCE = null;
        
        private FakeModule() {
            super();
        }
        
        @dagger.Provides
        @javax.inject.Singleton
        @org.jetbrains.annotations.NotNull
        public final com.jun.tripguide_v2.core.data.repository.AreaCodeRepository provideAreaCodeRepository(@org.jetbrains.annotations.NotNull
        com.jun.tripguide_v2.core.data.api.TourAreaCodeApi tourAreaCodeApi) {
            return null;
        }
        
        @dagger.Provides
        @javax.inject.Singleton
        @org.jetbrains.annotations.NotNull
        public final com.jun.tripguide_v2.core.data.api.fake.AssetsTourAreaCodeApi provideTourAreaCodeApi(@dagger.hilt.android.qualifiers.ApplicationContext
        @org.jetbrains.annotations.NotNull
        android.content.Context context) {
            return null;
        }
    }
}