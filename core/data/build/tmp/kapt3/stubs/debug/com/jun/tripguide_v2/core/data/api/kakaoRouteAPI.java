package com.jun.tripguide_v2.core.data.api;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001JI\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\u00062\b\b\u0001\u0010\b\u001a\u00020\u00062\b\b\u0001\u0010\t\u001a\u00020\u00062\b\b\u0001\u0010\n\u001a\u00020\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\f\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\r"}, d2 = {"Lcom/jun/tripguide_v2/core/data/api/kakaoRouteAPI;", "", "getKakaoRoute", "Lretrofit2/Response;", "Lcom/jun/tripguide_v2/core/data/api/model/kakaoroute/KakaoRoute;", "key", "", "origin", "destination", "priority", "summary", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "data_debug"})
public abstract interface kakaoRouteAPI {
    
    @retrofit2.http.GET(value = "v1/directions")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getKakaoRoute(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull
    java.lang.String key, @retrofit2.http.Query(value = "origin")
    @org.jetbrains.annotations.NotNull
    java.lang.String origin, @retrofit2.http.Query(value = "destination")
    @org.jetbrains.annotations.NotNull
    java.lang.String destination, @retrofit2.http.Query(value = "priority")
    @org.jetbrains.annotations.NotNull
    java.lang.String priority, @retrofit2.http.Query(value = "summary")
    boolean summary, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.jun.tripguide_v2.core.data.api.model.kakaoroute.KakaoRoute>> $completion);
}