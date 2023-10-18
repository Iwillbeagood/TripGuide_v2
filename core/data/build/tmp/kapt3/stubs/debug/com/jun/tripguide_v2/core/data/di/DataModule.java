package com.jun.tripguide_v2.core.data.di;

@dagger.Module
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b!\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\'J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\'J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\'\u00a8\u0006\u000f"}, d2 = {"Lcom/jun/tripguide_v2/core/data/di/DataModule;", "", "()V", "bindsAreaCodeRepository", "Lcom/jun/tripguide_v2/core/data/repository/AreaCodeRepository;", "repository", "Lcom/jun/tripguide_v2/core/data/repository/DefaultAreaCodeRepository;", "bindsKakaoLocalKeywordRepository", "Lcom/jun/tripguide_v2/core/data/repository/KakaoKeywordRepository;", "kakaoLocalKeywordRepository", "Lcom/jun/tripguide_v2/core/data/repository/DefaultKakaoKeywordRepository;", "bindsTravelRepository", "Lcom/jun/tripguide_v2/core/data/repository/TravelRepository;", "travelRepository", "Lcom/jun/tripguide_v2/core/data/repository/DefaultTravelRepository;", "data_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public abstract class DataModule {
    
    public DataModule() {
        super();
    }
    
    @dagger.Binds
    @org.jetbrains.annotations.NotNull
    public abstract com.jun.tripguide_v2.core.data.repository.AreaCodeRepository bindsAreaCodeRepository(@org.jetbrains.annotations.NotNull
    com.jun.tripguide_v2.core.data.repository.DefaultAreaCodeRepository repository);
    
    @dagger.Binds
    @org.jetbrains.annotations.NotNull
    public abstract com.jun.tripguide_v2.core.data.repository.TravelRepository bindsTravelRepository(@org.jetbrains.annotations.NotNull
    com.jun.tripguide_v2.core.data.repository.DefaultTravelRepository travelRepository);
    
    @dagger.Binds
    @org.jetbrains.annotations.NotNull
    public abstract com.jun.tripguide_v2.core.data.repository.KakaoKeywordRepository bindsKakaoLocalKeywordRepository(@org.jetbrains.annotations.NotNull
    com.jun.tripguide_v2.core.data.repository.DefaultKakaoKeywordRepository kakaoLocalKeywordRepository);
}