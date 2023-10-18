package com.jun.tripguide_v2.core.data.repository;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\'\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\f"}, d2 = {"Lcom/jun/tripguide_v2/core/data/repository/DefaultKakaoKeywordRepository;", "Lcom/jun/tripguide_v2/core/data/repository/KakaoKeywordRepository;", "keywordApi", "Lcom/jun/tripguide_v2/core/data/api/KakaoKeywordApi;", "(Lcom/jun/tripguide_v2/core/data/api/KakaoKeywordApi;)V", "getAddressByKeyword", "", "Lcom/jun/tripguide_v2/core/model/Address;", "key", "", "keyword", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "data_debug"})
public final class DefaultKakaoKeywordRepository implements com.jun.tripguide_v2.core.data.repository.KakaoKeywordRepository {
    @org.jetbrains.annotations.NotNull
    private final com.jun.tripguide_v2.core.data.api.KakaoKeywordApi keywordApi = null;
    
    @javax.inject.Inject
    public DefaultKakaoKeywordRepository(@org.jetbrains.annotations.NotNull
    com.jun.tripguide_v2.core.data.api.KakaoKeywordApi keywordApi) {
        super();
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object getAddressByKeyword(@org.jetbrains.annotations.NotNull
    java.lang.String key, @org.jetbrains.annotations.NotNull
    java.lang.String keyword, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.jun.tripguide_v2.core.model.Address>> $completion) {
        return null;
    }
}