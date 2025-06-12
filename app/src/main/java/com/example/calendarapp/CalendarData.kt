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
}