package com.example.pethealthtracker.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\tJ\u0016\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\rJ\u0016\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\tJ\u0018\u0010\u000f\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000b\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\rJ\u001a\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00120\u00112\u0006\u0010\u000b\u001a\u00020\fJ\u0016\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/example/pethealthtracker/data/repository/HealthRecordRepository;", "", "healthRecordDao", "Lcom/example/pethealthtracker/data/dao/HealthRecordDao;", "(Lcom/example/pethealthtracker/data/dao/HealthRecordDao;)V", "addRecord", "", "record", "Lcom/example/pethealthtracker/data/entity/HealthRecord;", "(Lcom/example/pethealthtracker/data/entity/HealthRecord;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteByPetId", "petId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteRecord", "getLatestRecord", "getRecordsByPetId", "Lkotlinx/coroutines/flow/Flow;", "", "updateRecord", "app_debug"})
public final class HealthRecordRepository {
    @org.jetbrains.annotations.NotNull
    private final com.example.pethealthtracker.data.dao.HealthRecordDao healthRecordDao = null;
    
    public HealthRecordRepository(@org.jetbrains.annotations.NotNull
    com.example.pethealthtracker.data.dao.HealthRecordDao healthRecordDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.pethealthtracker.data.entity.HealthRecord>> getRecordsByPetId(@org.jetbrains.annotations.NotNull
    java.lang.String petId) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getLatestRecord(@org.jetbrains.annotations.NotNull
    java.lang.String petId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.pethealthtracker.data.entity.HealthRecord> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object addRecord(@org.jetbrains.annotations.NotNull
    com.example.pethealthtracker.data.entity.HealthRecord record, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object updateRecord(@org.jetbrains.annotations.NotNull
    com.example.pethealthtracker.data.entity.HealthRecord record, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object deleteRecord(@org.jetbrains.annotations.NotNull
    com.example.pethealthtracker.data.entity.HealthRecord record, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object deleteByPetId(@org.jetbrains.annotations.NotNull
    java.lang.String petId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}