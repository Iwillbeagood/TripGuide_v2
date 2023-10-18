package com.jun.tripguide_v2.core.data.api;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J%\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\b"}, d2 = {"Lcom/jun/tripguide_v2/core/data/api/KakaoKeywordApi;", "", "getKakaoLocalByKeyword", "Lcom/jun/tripguide_v2/core/data/api/model/kakaokeyword/KakaoKeywordResponse;", "key", "", "address", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "data_debug"})
public abstract interface KakaoKeywordApi {
    
    @retrofit2.http.GET(value = "/v2/local/search/keyword.json")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getKakaoLocalByKeyword(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull
    java.lang.String key, @retrofit2.http.Query(value = "query")
    @org.jetbrains.annotations.NotNull
    java.lang.String address, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.jun.tripguide_v2.core.data.api.model.kakaokeyword.KakaoKeywordResponse> $completion);
}