package com.jun.tripguide_v2.core.data.repository;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0019\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ\u0019\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0006H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\rJ\u0019\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0006H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000f"}, d2 = {"Lcom/jun/tripguide_v2/core/data/repository/DefaultTravelRepository;", "Lcom/jun/tripguide_v2/core/data/repository/TravelRepository;", "travelDao", "Lcom/jun/tripguide_v2_core/database/TravelDao;", "(Lcom/jun/tripguide_v2_core/database/TravelDao;)V", "getTravelById", "Lcom/jun/tripguide_v2/core/model/Travel;", "id", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertTravel", "", "travel", "(Lcom/jun/tripguide_v2/core/model/Travel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateTravel", "data_debug"})
public final class DefaultTravelRepository implements com.jun.tripguide_v2.core.data.repository.TravelRepository {
    @org.jetbrains.annotations.NotNull
    private final com.jun.tripguide_v2_core.database.TravelDao travelDao = null;
    
    @javax.inject.Inject
    public DefaultTravelRepository(@org.jetbrains.annotations.NotNull
    com.jun.tripguide_v2_core.database.TravelDao travelDao) {
        super();
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object insertTravel(@org.jetbrains.annotations.NotNull
    com.jun.tripguide_v2.core.model.Travel travel, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object updateTravel(@org.jetbrains.annotations.NotNull
    com.jun.tripguide_v2.core.model.Travel travel, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object getTravelById(int id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.jun.tripguide_v2.core.model.Travel> $completion) {
        return null;
    }
}