package com.jun.tripguide_v2.core.data.api;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J+\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\t"}, d2 = {"Lcom/jun/tripguide_v2/core/data/api/KakaoAddressApi;", "", "getKakaoAddress", "", "Lcom/jun/tripguide_v2/core/data/api/model/KakaoAddressResponse;", "key", "", "address", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "data_debug"})
public abstract interface KakaoAddressApi {
    
    @retrofit2.http.GET(value = "v2/local/search/address.json")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getKakaoAddress(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull
    java.lang.String key, @retrofit2.http.Query(value = "query")
    @org.jetbrains.annotations.NotNull
    java.lang.String address, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.jun.tripguide_v2.core.data.api.model.KakaoAddressResponse>> $completion);
}