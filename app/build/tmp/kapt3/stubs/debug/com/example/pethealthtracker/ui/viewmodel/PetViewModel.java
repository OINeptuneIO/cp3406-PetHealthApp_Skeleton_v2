package com.example.pethealthtracker.ui.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bJ\u000e\u0010\u000e\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bJ\u0018\u0010\u000f\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@\u00a2\u0006\u0002\u0010\u0012J\u000e\u0010\u0013\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0014"}, d2 = {"Lcom/example/pethealthtracker/ui/viewmodel/PetViewModel;", "Landroidx/lifecycle/ViewModel;", "petRepository", "Lcom/example/pethealthtracker/data/repository/PetRepository;", "(Lcom/example/pethealthtracker/data/repository/PetRepository;)V", "pets", "Lkotlinx/coroutines/flow/StateFlow;", "", "Lcom/example/pethealthtracker/data/entity/Pet;", "getPets", "()Lkotlinx/coroutines/flow/StateFlow;", "addPet", "", "pet", "deletePet", "getPetById", "id", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updatePet", "app_debug"})
public final class PetViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final com.example.pethealthtracker.data.repository.PetRepository petRepository = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.pethealthtracker.data.entity.Pet>> pets = null;
    
    public PetViewModel(@org.jetbrains.annotations.NotNull
    com.example.pethealthtracker.data.repository.PetRepository petRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.pethealthtracker.data.entity.Pet>> getPets() {
        return null;
    }
    
    public final void addPet(@org.jetbrains.annotations.NotNull
    com.example.pethealthtracker.data.entity.Pet pet) {
    }
    
    public final void updatePet(@org.jetbrains.annotations.NotNull
    com.example.pethealthtracker.data.entity.Pet pet) {
    }
    
    public final void deletePet(@org.jetbrains.annotations.NotNull
    com.example.pethealthtracker.data.entity.Pet pet) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getPetById(@org.jetbrains.annotations.NotNull
    java.lang.String id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.pethealthtracker.data.entity.Pet> $completion) {
        return null;
    }
}