package com.example.pethealthtracker.ui.components;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u00006\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a^\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u000326\u0010\u0005\u001a2\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u00062\b\b\u0002\u0010\u000b\u001a\u00020\fH\u0007\u001aZ\u0010\r\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000326\u0010\u0005\u001a2\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u00062\b\b\u0002\u0010\u000b\u001a\u00020\fH\u0007\u001a<\u0010\u000e\u001a\u00020\u00012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0012\u001a\u00020\u00032\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00142\b\b\u0002\u0010\u000b\u001a\u00020\fH\u0003\u00a8\u0006\u0015"}, d2 = {"IOSTimePicker", "", "initialHour", "", "initialMinute", "onTimeSelected", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "hour", "minute", "modifier", "Landroidx/compose/ui/Modifier;", "TimeInputField", "TimePickerWheel", "items", "", "", "selectedIndex", "onItemSelected", "Lkotlin/Function1;", "app_debug"})
public final class IOSTimePickerKt {
    
    /**
     * iOS-style time picker
     * Supports selecting hours and minutes
     */
    @androidx.compose.runtime.Composable
    public static final void IOSTimePicker(int initialHour, int initialMinute, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function2<? super java.lang.Integer, ? super java.lang.Integer, kotlin.Unit> onTimeSelected, @org.jetbrains.annotations.NotNull
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable
    private static final void TimePickerWheel(java.util.List<java.lang.String> items, int selectedIndex, kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onItemSelected, androidx.compose.ui.Modifier modifier) {
    }
    
    /**
     * Simplified time input component
     * Displays current time, can be clicked to open time picker
     */
    @androidx.compose.runtime.Composable
    public static final void TimeInputField(int hour, int minute, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function2<? super java.lang.Integer, ? super java.lang.Integer, kotlin.Unit> onTimeSelected, @org.jetbrains.annotations.NotNull
    androidx.compose.ui.Modifier modifier) {
    }
}