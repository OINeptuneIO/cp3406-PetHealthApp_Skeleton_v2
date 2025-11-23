package com.example.pethealthtracker.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Calendar
import java.util.Date

/**
 * iOS-style calendar component
 * Supports date selection and highlighting dates with reminders
 */
@Composable
fun IOSCalendar(
    onDateSelected: (Long) -> Unit,
    highlightedDates: Set<Long> = emptySet(),
    modifier: Modifier = Modifier
) {
    val calendar = Calendar.getInstance()
    var selectedYear by remember { mutableIntStateOf(calendar.get(Calendar.YEAR)) }
    var selectedMonth by remember { mutableIntStateOf(calendar.get(Calendar.MONTH)) }
    var selectedDay by remember { mutableIntStateOf(calendar.get(Calendar.DAY_OF_MONTH)) }

    Column(modifier = modifier.fillMaxWidth()) {
        // Month and year navigation
        CalendarHeader(
            year = selectedYear,
            month = selectedMonth,
            onPreviousMonth = {
                if (selectedMonth == 0) {
                    selectedMonth = 11
                    selectedYear--
                } else {
                    selectedMonth--
                }
            },
            onNextMonth = {
                if (selectedMonth == 11) {
                    selectedMonth = 0
                    selectedYear++
                } else {
                    selectedMonth++
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Weekday header
        WeekdayHeader()

        Spacer(modifier = Modifier.height(8.dp))

        // Calendar grid
        CalendarGrid(
            year = selectedYear,
            month = selectedMonth,
            selectedDay = selectedDay,
            highlightedDates = highlightedDates,
            onDateSelected = { day ->
                selectedDay = day
                val cal = Calendar.getInstance()
                cal.set(selectedYear, selectedMonth, day, 0, 0, 0)
                onDateSelected(cal.timeInMillis)
            }
        )
    }
}

@Composable
private fun CalendarHeader(
    year: Int,
    month: Int,
    onPreviousMonth: () -> Unit,
    onNextMonth: () -> Unit
) {
    val monthNames = listOf(
        "Jan", "Feb", "Mar", "Apr", "May", "Jun",
        "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = onPreviousMonth) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Previous month"
            )
        }

        Text(
            text = "${monthNames[month]} $year",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        IconButton(onClick = onNextMonth) {
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Next month"
            )
        }
    }
}

@Composable
private fun WeekdayHeader() {
    val weekdays = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        weekdays.forEach { day ->
            Box(
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = day,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun CalendarGrid(
    year: Int,
    month: Int,
    selectedDay: Int,
    highlightedDates: Set<Long>,
    onDateSelected: (Int) -> Unit
) {
    val calendar = Calendar.getInstance()
    calendar.set(year, month, 1)

    // Get which day of the week the first day is
    val firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)

    // Get the number of days in this month
    val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

    // Create date list (including end of previous month for padding)
    val datesList = mutableListOf<Int?>()

    // Add end of previous month
    val previousMonth = Calendar.getInstance()
    previousMonth.set(year, month - 1, 1)
    val daysInPreviousMonth = previousMonth.getActualMaximum(Calendar.DAY_OF_MONTH)

    for (i in 1 until firstDayOfWeek) {
        datesList.add(null)  // Blank date
    }

    // Add dates of this month
    for (i in 1..daysInMonth) {
        datesList.add(i)
    }

    // Split list into weeks (7 days per week)
    val weeks = datesList.chunked(7)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        weeks.forEach { week ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                repeat(week.size) { index ->
                    val day = week[index]
                    if (day == null) {
                        Spacer(modifier = Modifier.width(40.dp))
                    } else {
                        val isSelected = day == selectedDay
                        val isHighlighted = highlightedDates.any { timestamp ->
                            val highlightedCal = Calendar.getInstance()
                            highlightedCal.timeInMillis = timestamp
                            highlightedCal.get(Calendar.YEAR) == year &&
                            highlightedCal.get(Calendar.MONTH) == month &&
                            highlightedCal.get(Calendar.DAY_OF_MONTH) == day
                        }

                        CalendarDayCell(
                            day = day,
                            isSelected = isSelected,
                            isHighlighted = isHighlighted,
                            onClick = { onDateSelected(day) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun CalendarDayCell(
    day: Int,
    isSelected: Boolean,
    isHighlighted: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(40.dp)
            .height(40.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(
                when {
                    isSelected -> MaterialTheme.colorScheme.primary
                    isHighlighted -> MaterialTheme.colorScheme.primaryContainer
                    else -> MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
                }
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = day.toString(),
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.SemiBold,
                color = when {
                    isSelected -> MaterialTheme.colorScheme.onPrimary
                    isHighlighted -> MaterialTheme.colorScheme.onPrimaryContainer
                    else -> MaterialTheme.colorScheme.onSurfaceVariant
                }
            )

            // If there are reminders, show a small dot (visible whether selected or not)
            if (isHighlighted) {
                Spacer(modifier = Modifier.height(2.dp))
                Box(
                    modifier = Modifier
                        .width(4.dp)
                        .height(4.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(
                            if (isSelected) MaterialTheme.colorScheme.onPrimary
                            else MaterialTheme.colorScheme.error
                        )
                )
            }
        }
    }
}

// Helper function: Get all reminder timestamps for the date of a timestamp
fun getHighlightedDatesFromReminders(reminders: List<Pair<String, Long>>): Set<Long> {
    val calendar = Calendar.getInstance()
    val highlightedDates = mutableSetOf<Long>()

    reminders.forEach { (_, timestamp) ->
        calendar.timeInMillis = timestamp
        // 重置时间为该天的开始时间
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        highlightedDates.add(calendar.timeInMillis)
    }

    return highlightedDates
}
