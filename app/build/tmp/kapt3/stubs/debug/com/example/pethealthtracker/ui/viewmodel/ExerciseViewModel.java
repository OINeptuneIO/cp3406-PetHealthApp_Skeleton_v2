package com.example.pethealthtracker.ui.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u001a\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\f0\u000b2\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/example/pethealthtracker/ui/viewmodel/ExerciseViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/example/pethealthtracker/data/repository/ExerciseRecordRepository;", "(Lcom/example/pethealthtracker/data/repository/ExerciseRecordRepository;)V", "addRecord", "", "record", "Lcom/example/pethealthtracker/data/entity/ExerciseRecord;", "deleteRecord", "getRecordsByPetId", "Lkotlinx/coroutines/flow/StateFlow;", "", "petId", "", "updateRecord", "app_debug"})
public final class ExerciseViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final com.example.pethealthtracker.data.repository.ExerciseRecordRepository repository = null;
    
    public ExerciseViewModel(@org.jetbrains.annotations.NotNull
    com.example.pethealthtracker.data.repository.ExerciseRecordRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.pethealthtracker.data.entity.ExerciseRecord>> getRecordsByPetId(@org.jetbrains.annotations.NotNull
    java.lang.String petId) {
        return null;
    }
    
    public final void addRecord(@org.jetbrains.annotations.NotNull
    com.example.pethealthtracker.data.entity.ExerciseRecord record) {
    }
    
    public final void updateRecord(@org.jetbrains.annotations.NotNull
    com.example.pethealthtracker.data.entity.ExerciseRecord record) {
    }
    
    public final void deleteRecord(@org.jetbrains.annotations.NotNull
    com.example.pethealthtracker.data.entity.ExerciseRecord record) {
    }
}