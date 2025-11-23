package com.example.pethealthtracker.ui.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bJ\u000e\u0010\u000e\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bJ\u000e\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0011J\u001a\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00062\u0006\u0010\u0013\u001a\u00020\u0011J\u001c\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00062\b\b\u0002\u0010\u0015\u001a\u00020\u0016J\u001c\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00062\b\b\u0002\u0010\u0018\u001a\u00020\u0016J\u000e\u0010\u0019\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bJ\u0016\u0010\u001a\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u001dR\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lcom/example/pethealthtracker/ui/viewmodel/ReminderViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/example/pethealthtracker/data/repository/ReminderRepository;", "(Lcom/example/pethealthtracker/data/repository/ReminderRepository;)V", "enabledReminders", "Lkotlinx/coroutines/flow/StateFlow;", "", "Lcom/example/pethealthtracker/data/entity/Reminder;", "getEnabledReminders", "()Lkotlinx/coroutines/flow/StateFlow;", "addReminder", "", "reminder", "deleteReminder", "deleteReminderById", "reminderId", "", "getRemindersByPetId", "petId", "getUpcomingReminders", "withinMillis", "", "getUpcomingRemindersForNotification", "withinMinutes", "updateReminder", "updateReminderStatus", "id", "enabled", "", "app_debug"})
public final class ReminderViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final com.example.pethealthtracker.data.repository.ReminderRepository repository = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.pethealthtracker.data.entity.Reminder>> enabledReminders = null;
    
    public ReminderViewModel(@org.jetbrains.annotations.NotNull
    com.example.pethealthtracker.data.repository.ReminderRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.pethealthtracker.data.entity.Reminder>> getRemindersByPetId(@org.jetbrains.annotations.NotNull
    java.lang.String petId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.pethealthtracker.data.entity.Reminder>> getEnabledReminders() {
        return null;
    }
    
    public final void addReminder(@org.jetbrains.annotations.NotNull
    com.example.pethealthtracker.data.entity.Reminder reminder) {
    }
    
    public final void updateReminder(@org.jetbrains.annotations.NotNull
    com.example.pethealthtracker.data.entity.Reminder reminder) {
    }
    
    public final void deleteReminder(@org.jetbrains.annotations.NotNull
    com.example.pethealthtracker.data.entity.Reminder reminder) {
    }
    
    public final void deleteReminderById(@org.jetbrains.annotations.NotNull
    java.lang.String reminderId) {
    }
    
    public final void updateReminderStatus(@org.jetbrains.annotations.NotNull
    java.lang.String id, boolean enabled) {
    }
    
    /**
     * 获取即将到来的提醒（在提醒时间点正好到达时）
     * @param withinMillis 时间范围（毫秒），默认500ms（前后各容错）
     */
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.pethealthtracker.data.entity.Reminder>> getUpcomingReminders(long withinMillis) {
        return null;
    }
    
    /**
     * 获取即将到来的提醒用于通知栏显示（在当前时间之后）
     * @param withinMinutes 时间范围（分钟），默认24小时
     */
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.pethealthtracker.data.entity.Reminder>> getUpcomingRemindersForNotification(long withinMinutes) {
        return null;
    }
}