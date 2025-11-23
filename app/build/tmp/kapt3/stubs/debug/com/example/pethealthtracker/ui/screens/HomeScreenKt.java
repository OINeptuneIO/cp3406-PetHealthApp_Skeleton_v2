package com.example.pethealthtracker.ui.screens;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u00008\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\u001a*\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u001a,\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\t2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0007\u001a2\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u00062\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u001a\u0010\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000eH\u0007\u001a\u0018\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0011H\u0007\u001a\u001e\u0010\u0012\u001a\u00020\u00012\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00060\u00142\u0006\u0010\u0010\u001a\u00020\u0011H\u0007\u00a8\u0006\u0015"}, d2 = {"AddPetDialog", "", "onDismiss", "Lkotlin/Function0;", "onConfirm", "Lkotlin/Function1;", "Lcom/example/pethealthtracker/data/entity/Pet;", "DeleteConfirmDialog", "petName", "", "EditPetDialog", "pet", "HomeScreen", "navController", "Landroidx/navigation/NavController;", "PetCard", "petViewModel", "Lcom/example/pethealthtracker/ui/viewmodel/PetViewModel;", "PetList", "pets", "", "app_debug"})
public final class HomeScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable
    public static final void HomeScreen(@org.jetbrains.annotations.NotNull
    androidx.navigation.NavController navController) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void PetList(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.pethealthtracker.data.entity.Pet> pets, @org.jetbrains.annotations.NotNull
    com.example.pethealthtracker.ui.viewmodel.PetViewModel petViewModel) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void PetCard(@org.jetbrains.annotations.NotNull
    com.example.pethealthtracker.data.entity.Pet pet, @org.jetbrains.annotations.NotNull
    com.example.pethealthtracker.ui.viewmodel.PetViewModel petViewModel) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.foundation.layout.ExperimentalLayoutApi.class, androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable
    public static final void AddPetDialog(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super com.example.pethealthtracker.data.entity.Pet, kotlin.Unit> onConfirm) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.foundation.layout.ExperimentalLayoutApi.class, androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable
    public static final void EditPetDialog(@org.jetbrains.annotations.NotNull
    com.example.pethealthtracker.data.entity.Pet pet, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super com.example.pethealthtracker.data.entity.Pet, kotlin.Unit> onConfirm) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void DeleteConfirmDialog(@org.jetbrains.annotations.NotNull
    java.lang.String petName, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onConfirm, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss) {
    }
}