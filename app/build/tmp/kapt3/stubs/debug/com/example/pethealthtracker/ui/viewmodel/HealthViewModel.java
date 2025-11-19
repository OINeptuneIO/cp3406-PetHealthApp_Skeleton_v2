package com.example.pethealthtracker.ui.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0018\u0010\n\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000b\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\rJ\u001a\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00100\u000f2\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/example/pethealthtracker/ui/viewmodel/HealthViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/example/pethealthtracker/data/repository/HealthRecordRepository;", "(Lcom/example/pethealthtracker/data/repository/HealthRecordRepository;)V", "addRecord", "", "record", "Lcom/example/pethealthtracker/data/entity/HealthRecord;", "deleteRecord", "getLatestRecord", "petId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getRecordsByPetId", "Lkotlinx/coroutines/flow/StateFlow;", "", "updateRecord", "app_debug"})
public final class HealthViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final com.example.pethealthtracker.data.repository.HealthRecordRepository repository = null;
    
    public HealthViewModel(@org.jetbrains.annotations.NotNull
    com.example.pethealthtracker.data.repository.HealthRecordRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.pethealthtracker.data.entity.HealthRecord>> getRecordsByPetId(@org.jetbrains.annotations.NotNull
    java.lang.String petId) {
        return null;
    }
    
    public final void addRecord(@org.jetbrains.annotations.NotNull
    com.example.pethealthtracker.data.entity.HealthRecord record) {
    }
    
    public final void updateRecord(@org.jetbrains.annotations.NotNull
    com.example.pethealthtracker.data.entity.HealthRecord record) {
    }
    
    public final void deleteRecord(@org.jetbrains.annotations.NotNull
    com.example.pethealthtracker.data.entity.HealthRecord record) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getLatestRecord(@org.jetbrains.annotations.NotNull
    java.lang.String petId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.pethealthtracker.data.entity.HealthRecord> $completion) {
        return null;
    }
}