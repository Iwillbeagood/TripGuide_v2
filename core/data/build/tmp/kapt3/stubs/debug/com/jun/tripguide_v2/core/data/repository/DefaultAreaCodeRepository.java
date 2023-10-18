package com.jun.tripguide_v2.core.data.repository;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J3\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\nH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fJ+\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\tH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000f"}, d2 = {"Lcom/jun/tripguide_v2/core/data/repository/DefaultAreaCodeRepository;", "Lcom/jun/tripguide_v2/core/data/repository/AreaCodeRepository;", "tourAreaCodeApi", "Lcom/jun/tripguide_v2/core/data/api/TourAreaCodeApi;", "(Lcom/jun/tripguide_v2/core/data/api/TourAreaCodeApi;)V", "getAreaCode", "", "Lcom/jun/tripguide_v2/core/model/AreaCode;", "queryParams", "", "", "areaCode", "(Ljava/util/Map;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDefaultAreaCode", "(Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "data_debug"})
public final class DefaultAreaCodeRepository implements com.jun.tripguide_v2.core.data.repository.AreaCodeRepository {
    @org.jetbrains.annotations.NotNull
    private final com.jun.tripguide_v2.core.data.api.TourAreaCodeApi tourAreaCodeApi = null;
    
    @javax.inject.Inject
    public DefaultAreaCodeRepository(@org.jetbrains.annotations.NotNull
    com.jun.tripguide_v2.core.data.api.TourAreaCodeApi tourAreaCodeApi) {
        super();
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object getAreaCode(@org.jetbrains.annotations.NotNull
    java.util.Map<java.lang.String, java.lang.String> queryParams, @org.jetbrains.annotations.NotNull
    java.lang.String areaCode, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.jun.tripguide_v2.core.model.AreaCode>> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object getDefaultAreaCode(@org.jetbrains.annotations.NotNull
    java.util.Map<java.lang.String, java.lang.String> queryParams, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.jun.tripguide_v2.core.model.AreaCode>> $completion) {
        return null;
    }
}