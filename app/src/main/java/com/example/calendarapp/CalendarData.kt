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
        val firstWeekday = firstDay.dayOfWeek.value % 7 // Sunday = 0

        // Add blanks before the first day
        repeat(firstWeekday) { days.add(null) }

        // Add real days
        val totalDays = month.lengthOfMonth()
        for (i in 1..totalDays) {
            days.add(month.atDay(i))
        }

        return days
    }
}