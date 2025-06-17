package com.example.calendarapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calendarapp.ui.theme.CalendarAppTheme
import java.time.LocalDate
import java.time.YearMonth
import androidx.compose.material3.TextButton


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalendarAppTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val calendarData = remember { CalendarData() }

    val currentDate by remember { mutableStateOf(LocalDate.now()) }
    val currentMonth = YearMonth.from(currentDate)

    val monthName = currentMonth.month.name.lowercase()
        .replaceFirstChar { it.uppercase() }

    val calendarCells = remember { calendarData.generateCalendarCells(currentMonth) }

    // 🔲 Main container for background and layout
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF4f6b70), // Top (blue-grey)
                        Color.DarkGray     // Bottom
                    ),
                    startY = 0f,
                    endY = 500f
                )
            )
            .padding(26.dp) // Padding around the whole screen content
    ) {
        // 🟦 MONTH TITLE BAR
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = monthName,
                color = Color.White,
                fontSize = 28.sp
            )
        }

        // ⬜ Space between month title and calendar grid
        Spacer(modifier = Modifier.height(30.dp))

// ⬜ Faded white underline between month and calendar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.White,
                            Color.Transparent
                        )
                    )
                )
        )

        Spacer(modifier = Modifier.height(45.dp)) // Optional: adds space below the line

        // 🗓️ CALENDAR GRID BOX
        LazyVerticalGrid(
            columns = GridCells.Fixed(7),
            modifier = Modifier.fillMaxSize(),
            content = {
                items(calendarCells.size) { index ->
                    val date = calendarCells[index]

                    // ⬛ EMPTY CELL (padding before 1st of month)
                    if (date == null) {
                        Box(
                            modifier = Modifier
                                .height(10.dp)
                                .fillMaxWidth()
                        )
                    } else {
                        // 🟨 DATE CELL BUTTON
                        TextButton(
                            onClick = {
                                // Handle click (e.g., show events or date picker)
                                println("Clicked ${date}")
                            },
                            modifier = Modifier
                                .padding(2.dp)
                                .aspectRatio(1f) // ensures square shape in grid
                                .fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.DarkGray, // same as background
                                contentColor = Color.White
                            ),
                            elevation = null, // no shadow
                            content = {
                                Text(
                                    text = "${date.dayOfMonth}",
                                    fontSize = 14.sp
                                )
                            }
                        )
                    }
                }
            }
        )
    }
}





