package com.example.pethealthtracker.data.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\fH\u00a7@\u00a2\u0006\u0002\u0010\rJ\u0014\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\f0\u000fH\'J\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0011\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u001c\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\f0\u000f2\u0006\u0010\b\u001a\u00020\tH\'J\u0016\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001e\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u0017H\u00a7@\u00a2\u0006\u0002\u0010\u0018\u00a8\u0006\u0019"}, d2 = {"Lcom/example/pethealthtracker/data/dao/ReminderDao;", "", "delete", "", "reminder", "Lcom/example/pethealthtracker/data/entity/Reminder;", "(Lcom/example/pethealthtracker/data/entity/Reminder;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteByPetId", "petId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllReminders", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getEnabledReminders", "Lkotlinx/coroutines/flow/Flow;", "getReminderById", "id", "getRemindersByPetId", "insert", "update", "updateReminderStatus", "enabled", "", "(Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao
public abstract interface ReminderDao {
    
    @androidx.room.Insert
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull
    com.example.pethealthtracker.data.entity.Reminder reminder, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Update
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object update(@org.jetbrains.annotations.NotNull
    com.example.pethealthtracker.data.entity.Reminder reminder, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object delete(@org.jetbrains.annotations.NotNull
    com.example.pethealthtracker.data.entity.Reminder reminder, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM reminders WHERE id = :id")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getReminderById(@org.jetbrains.annotations.NotNull
    java.lang.String id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.pethealthtracker.data.entity.Reminder> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM reminders WHERE petId = :petId ORDER BY reminderDate ASC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.pethealthtracker.data.entity.Reminder>> getRemindersByPetId(@org.jetbrains.annotations.NotNull
    java.lang.String petId);
    
    @androidx.room.Query(value = "SELECT * FROM reminders WHERE isEnabled = 1 ORDER BY reminderDate ASC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.pethealthtracker.data.entity.Reminder>> getEnabledReminders();
    
    @androidx.room.Query(value = "SELECT * FROM reminders ORDER BY reminderDate ASC")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getAllReminders(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.pethealthtracker.data.entity.Reminder>> $completion);
    
    @androidx.room.Query(value = "UPDATE reminders SET isEnabled = :enabled WHERE id = :id")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateReminderStatus(@org.jetbrains.annotations.NotNull
    java.lang.String id, boolean enabled, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM reminders WHERE petId = :petId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteByPetId(@org.jetbrains.annotations.NotNull
    java.lang.String petId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}