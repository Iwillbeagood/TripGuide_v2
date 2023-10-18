package com.jun.tripguide_v2.core.data.api;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J1\u0010\u0002\u001a\u00020\u00032\u0014\b\u0001\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0001\u0010\u0007\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ\'\u0010\t\u001a\u00020\u00032\u0014\b\u0001\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000b"}, d2 = {"Lcom/jun/tripguide_v2/core/data/api/TourAreaCodeApi;", "", "getAreaCode", "Lcom/jun/tripguide_v2/core/data/api/model/areacode/AreaCodeResponse;", "queryParams", "", "", "areaCode", "(Ljava/util/Map;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDefaultAreaCode", "(Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "data_debug"})
public abstract interface TourAreaCodeApi {
    
    @retrofit2.http.GET(value = "areaCode1")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getAreaCode(@retrofit2.http.QueryMap
    @org.jetbrains.annotations.NotNull
    java.util.Map<java.lang.String, java.lang.String> queryParams, @retrofit2.http.Query(value = "areaCode")
    @org.jetbrains.annotations.NotNull
    java.lang.String areaCode, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.jun.tripguide_v2.core.data.api.model.areacode.AreaCodeResponse> $completion);
    
    @retrofit2.http.GET(value = "areaCode1")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getDefaultAreaCode(@retrofit2.http.QueryMap
    @org.jetbrains.annotations.NotNull
    java.util.Map<java.lang.String, java.lang.String> queryParams, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.jun.tripguide_v2.core.data.api.model.areacode.AreaCodeResponse> $completion);
}