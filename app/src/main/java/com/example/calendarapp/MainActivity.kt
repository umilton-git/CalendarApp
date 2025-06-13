package com.example.calendarapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.calendarapp.ui.theme.CalendarAppTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Button
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Brush
import java.time.YearMonth
import java.time.LocalDate


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
    // Create instance of CalendarData
    val calendarData = remember { CalendarData() }

    // Get current month
    val currentDate by remember {
        mutableStateOf(LocalDate.now())
    }

// Then get currentMonth from this:
    val currentMonth = YearMonth.from(currentDate)

    val monthName = currentMonth.month.name.lowercase()
        .replaceFirstChar { it.uppercase() }

    // Generate days in month
    val daysInMonth = remember { calendarData.generateMonth(currentMonth) }

    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF4f6b70),         // Top color
                        Color.DarkGray     // Bottom color
                    ),
                    startY = 0f,
                    endY = 500f  // Large enough so grey dominates
                )
            )
            .padding(16.dp, vertical = 45.dp)
    ) {
        // TEMP: Display first date as proof of data working
        Text(
            text = monthName.toString(),
            color = Color.White,
        )
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        color = Color.Red,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 32.dp)
    )
}


