package com.example.pethealthtracker.ui.components;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000J\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a:\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\nH\u0003\u001a\u00b7\u0001\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\u00102\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00130\u00122\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00010\n2\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\u000726\u0010\u0016\u001a2\u0012\u0013\u0012\u00110\u0005\u00a2\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\u0013\u0012\u00110\b\u00a2\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\u00010\u00172!\u0010\u001c\u001a\u001d\u0012\u0013\u0012\u00110\u0005\u00a2\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u00010\u0007H\u0007\u001a\u0010\u0010\u001d\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020\u000eH\u0002\u00a8\u0006\u001f"}, d2 = {"DateReminderItem", "", "reminder", "Lcom/example/pethealthtracker/data/entity/Reminder;", "petName", "", "onToggle", "Lkotlin/Function1;", "", "onDelete", "Lkotlin/Function0;", "DateRemindersBottomSheet", "isVisible", "selectedDateMillis", "", "reminders", "", "petMap", "", "Lcom/example/pethealthtracker/data/entity/Pet;", "onDismiss", "onAddReminder", "onToggleReminder", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "reminderId", "enabled", "onDeleteReminder", "formatReminderTime", "timestamp", "app_debug"})
public final class DateRemindersBottomSheetKt {
    
    /**
     * iOS-style date reminder list bottom sheet
     * Displays all reminders for a date and allows adding new reminders
     */
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable
    public static final void DateRemindersBottomSheet(boolean isVisible, long selectedDateMillis, @org.jetbrains.annotations.NotNull
    java.util.List<com.example.pethealthtracker.data.entity.Reminder> reminders, @org.jetbrains.annotations.NotNull
    java.util.Map<java.lang.String, com.example.pethealthtracker.data.entity.Pet> petMap, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.lang.Long, kotlin.Unit> onAddReminder, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function2<? super java.lang.String, ? super java.lang.Boolean, kotlin.Unit> onToggleReminder, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onDeleteReminder) {
    }
    
    @androidx.compose.runtime.Composable
    private static final void DateReminderItem(com.example.pethealthtracker.data.entity.Reminder reminder, java.lang.String petName, kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> onToggle, kotlin.jvm.functions.Function0<kotlin.Unit> onDelete) {
    }
    
    private static final java.lang.String formatReminderTime(long timestamp) {
        return null;
    }
}