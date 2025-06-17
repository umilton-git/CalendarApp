package com.example.calendarapp

import java.time.*

class CalendarData {
    data class CalendarDay(
        val date: LocalDate,
        val events: MutableList<String> = mutableListOf()
    )

    fun generateMonth(month: YearMonth): List<CalendarDay> {
        val daysInMonth = month.lengthOfMonth()
        return (1..daysInMonth).map { day ->
            CalendarDay(date = month.atDay(day))
        }
    }

    fun generateCalendarCells(month: YearMonth): List<LocalDate?> {
        val days = mutableListOf<LocalDate?>()
        val firstDay = month.atDay(1)
        val offset = when (firstDay.dayOfWeek) {
            DayOfWeek.SUNDAY -> 0
            DayOfWeek.MONDAY -> 1
            DayOfWeek.TUESDAY -> 2
            DayOfWeek.WEDNESDAY -> 3
            DayOfWeek.THURSDAY -> 4
            DayOfWeek.FRIDAY -> 5
            DayOfWeek.SATURDAY -> 6
        }

        // Insert nulls to pad the first week
        repeat(offset) { days.add(null) }

        // Add actual days
        val totalDays = month.lengthOfMonth()
        for (i in 1..totalDays) {
            days.add(month.atDay(i))
        }

        return days
    }

}