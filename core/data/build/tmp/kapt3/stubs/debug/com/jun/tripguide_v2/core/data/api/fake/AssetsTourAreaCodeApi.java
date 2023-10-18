package com.jun.tripguide_v2.core.data.api.fake;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J-\u0010\n\u001a\u00020\u000b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u000f\u001a\u00020\u000eH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010J%\u0010\u0011\u001a\u00020\u000b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e0\rH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0013"}, d2 = {"Lcom/jun/tripguide_v2/core/data/api/fake/AssetsTourAreaCodeApi;", "Lcom/jun/tripguide_v2/core/data/api/TourAreaCodeApi;", "context", "Landroid/content/Context;", "json", "Lkotlinx/serialization/json/Json;", "(Landroid/content/Context;Lkotlinx/serialization/json/Json;)V", "areaCodes", "Ljava/io/InputStream;", "defaultAreaCodes", "getAreaCode", "Lcom/jun/tripguide_v2/core/data/api/model/areaCode/AreaCodeResponse;", "queryParams", "", "", "areaCode", "(Ljava/util/Map;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDefaultAreaCode", "(Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "data_debug"})
@kotlin.OptIn(markerClass = {kotlinx.serialization.ExperimentalSerializationApi.class})
public final class AssetsTourAreaCodeApi implements com.jun.tripguide_v2.core.data.api.TourAreaCodeApi {
    @org.jetbrains.annotations.NotNull
    private final kotlinx.serialization.json.Json json = null;
    @org.jetbrains.annotations.NotNull
    private final java.io.InputStream areaCodes = null;
    @org.jetbrains.annotations.NotNull
    private final java.io.InputStream defaultAreaCodes = null;
    
    public AssetsTourAreaCodeApi(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    kotlinx.serialization.json.Json json) {
        super();
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object getAreaCode(@org.jetbrains.annotations.NotNull
    java.util.Map<java.lang.String, java.lang.String> queryParams, @org.jetbrains.annotations.NotNull
    java.lang.String areaCode, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.jun.tripguide_v2.core.data.api.model.areaCode.AreaCodeResponse> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object getDefaultAreaCode(@org.jetbrains.annotations.NotNull
    java.util.Map<java.lang.String, java.lang.String> queryParams, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.jun.tripguide_v2.core.data.api.model.areaCode.AreaCodeResponse> $completion) {
        return null;
    }
}