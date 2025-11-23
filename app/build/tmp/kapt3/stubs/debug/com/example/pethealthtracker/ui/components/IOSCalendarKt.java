package com.example.pethealthtracker.ui.components;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000F\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\u001a.\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\bH\u0003\u001aB\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u00032\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0011H\u0003\u001a4\u0010\u0012\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00032\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00010\bH\u0003\u001a6\u0010\u0015\u001a\u00020\u00012\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00010\u00112\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\b\b\u0002\u0010\u0016\u001a\u00020\u0017H\u0007\u001a\b\u0010\u0018\u001a\u00020\u0001H\u0003\u001a&\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0018\u0010\u001a\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u000f0\u001c0\u001b\u00a8\u0006\u001e"}, d2 = {"CalendarDayCell", "", "day", "", "isSelected", "", "isHighlighted", "onClick", "Lkotlin/Function0;", "CalendarGrid", "year", "month", "selectedDay", "highlightedDates", "", "", "onDateSelected", "Lkotlin/Function1;", "CalendarHeader", "onPreviousMonth", "onNextMonth", "IOSCalendar", "modifier", "Landroidx/compose/ui/Modifier;", "WeekdayHeader", "getHighlightedDatesFromReminders", "reminders", "", "Lkotlin/Pair;", "", "app_debug"})
public final class IOSCalendarKt {
    
    /**
     * iOS-style calendar component
     * Supports date selection and highlighting dates with reminders
     */
    @androidx.compose.runtime.Composable
    public static final void IOSCalendar(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.lang.Long, kotlin.Unit> onDateSelected, @org.jetbrains.annotations.NotNull
    java.util.Set<java.lang.Long> highlightedDates, @org.jetbrains.annotations.NotNull
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable
    private static final void CalendarHeader(int year, int month, kotlin.jvm.functions.Function0<kotlin.Unit> onPreviousMonth, kotlin.jvm.functions.Function0<kotlin.Unit> onNextMonth) {
    }
    
    @androidx.compose.runtime.Composable
    private static final void WeekdayHeader() {
    }
    
    @androidx.compose.runtime.Composable
    private static final void CalendarGrid(int year, int month, int selectedDay, java.util.Set<java.lang.Long> highlightedDates, kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onDateSelected) {
    }
    
    @androidx.compose.runtime.Composable
    private static final void CalendarDayCell(int day, boolean isSelected, boolean isHighlighted, kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    @org.jetbrains.annotations.NotNull
    public static final java.util.Set<java.lang.Long> getHighlightedDatesFromReminders(@org.jetbrains.annotations.NotNull
    java.util.List<kotlin.Pair<java.lang.String, java.lang.Long>> reminders) {
        return null;
    }
}