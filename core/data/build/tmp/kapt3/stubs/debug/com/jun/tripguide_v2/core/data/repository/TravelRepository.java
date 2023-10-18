package com.jun.tripguide_v2.core.data.repository;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0003H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u0019\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0003H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\f"}, d2 = {"Lcom/jun/tripguide_v2/core/data/repository/TravelRepository;", "", "getTravelById", "Lcom/jun/tripguide_v2/core/model/Travel;", "id", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertTravel", "", "travel", "(Lcom/jun/tripguide_v2/core/model/Travel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateTravel", "data_debug"})
public abstract interface TravelRepository {
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertTravel(@org.jetbrains.annotations.NotNull
    com.jun.tripguide_v2.core.model.Travel travel, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateTravel(@org.jetbrains.annotations.NotNull
    com.jun.tripguide_v2.core.model.Travel travel, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getTravelById(int id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.jun.tripguide_v2.core.model.Travel> $completion);
}