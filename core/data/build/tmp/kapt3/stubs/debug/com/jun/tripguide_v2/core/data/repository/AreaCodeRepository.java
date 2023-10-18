package com.jun.tripguide_v2.core.data.repository;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J3\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\u0007H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ+\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0006H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000b\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\f"}, d2 = {"Lcom/jun/tripguide_v2/core/data/repository/AreaCodeRepository;", "", "getAreaCode", "", "Lcom/jun/tripguide_v2/core/model/AreaCode;", "queryParams", "", "", "areaCode", "(Ljava/util/Map;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDefaultAreaCode", "(Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "data_debug"})
public abstract interface AreaCodeRepository {
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getAreaCode(@org.jetbrains.annotations.NotNull
    java.util.Map<java.lang.String, java.lang.String> queryParams, @org.jetbrains.annotations.NotNull
    java.lang.String areaCode, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.jun.tripguide_v2.core.model.AreaCode>> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getDefaultAreaCode(@org.jetbrains.annotations.NotNull
    java.util.Map<java.lang.String, java.lang.String> queryParams, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.jun.tripguide_v2.core.model.AreaCode>> $completion);
}