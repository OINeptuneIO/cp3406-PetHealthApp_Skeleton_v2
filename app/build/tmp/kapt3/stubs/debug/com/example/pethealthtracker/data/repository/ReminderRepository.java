package com.example.pethealthtracker.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\tJ\u0016\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\rJ\u0016\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\tJ\u0016\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\rJ\u0012\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00130\u0012J\u001a\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00130\u00122\u0006\u0010\u000b\u001a\u00020\fJ\u0016\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\tJ\u001e\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u0018H\u0086@\u00a2\u0006\u0002\u0010\u0019R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/example/pethealthtracker/data/repository/ReminderRepository;", "", "reminderDao", "Lcom/example/pethealthtracker/data/dao/ReminderDao;", "(Lcom/example/pethealthtracker/data/dao/ReminderDao;)V", "addReminder", "", "reminder", "Lcom/example/pethealthtracker/data/entity/Reminder;", "(Lcom/example/pethealthtracker/data/entity/Reminder;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteByPetId", "petId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteReminder", "deleteReminderById", "id", "getEnabledReminders", "Lkotlinx/coroutines/flow/Flow;", "", "getRemindersByPetId", "updateReminder", "updateReminderStatus", "enabled", "", "(Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class ReminderRepository {
    @org.jetbrains.annotations.NotNull
    private final com.example.pethealthtracker.data.dao.ReminderDao reminderDao = null;
    
    public ReminderRepository(@org.jetbrains.annotations.NotNull
    com.example.pethealthtracker.data.dao.ReminderDao reminderDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.pethealthtracker.data.entity.Reminder>> getRemindersByPetId(@org.jetbrains.annotations.NotNull
    java.lang.String petId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.pethealthtracker.data.entity.Reminder>> getEnabledReminders() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object addReminder(@org.jetbrains.annotations.NotNull
    com.example.pethealthtracker.data.entity.Reminder reminder, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object updateReminder(@org.jetbrains.annotations.NotNull
    com.example.pethealthtracker.data.entity.Reminder reminder, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object deleteReminder(@org.jetbrains.annotations.NotNull
    com.example.pethealthtracker.data.entity.Reminder reminder, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object updateReminderStatus(@org.jetbrains.annotations.NotNull
    java.lang.String id, boolean enabled, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object deleteByPetId(@org.jetbrains.annotations.NotNull
    java.lang.String petId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object deleteReminderById(@org.jetbrains.annotations.NotNull
    java.lang.String id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}