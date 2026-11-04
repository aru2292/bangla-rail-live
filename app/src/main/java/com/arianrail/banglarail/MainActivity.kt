package com.arianrail.banglarail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Night = Color(0xFF080D18)
private val Panel = Color(0xFF101B2D)
private val Blue = Color(0xFF20A9FF)
private val Muted = Color(0xFF9BAFC5)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(
                colorScheme = darkColorScheme(
                    primary = Blue,
                    background = Night,
                    surface = Panel
                )
            ) {
                RailApp()
            }
        }
    }
}

@Composable
fun RailApp() {
    var selectedTab by remember { mutableIntStateOf(0) }

    Scaffold(
        containerColor = Night,
        bottomBar = {
            NavigationBar(containerColor = Panel) {
                listOf("Home", "Live Map", "Trains", "Profile").forEachIndexed { index, title ->
                    NavigationBarItem(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        icon = {},
                        label = { Text(title, fontSize = 11.sp) }
                    )
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            Text(
                "BANGLA RAIL LIVE",
                color = Blue,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp
            )

            Text(
                when (selectedTab) {
                    1 -> "Live Map"
                    2 -> "Train Directory"
                    3 -> "Your Profile"
                    else -> "Journey starts here."
                },
                color = Color.White,
                fontSize = 27.sp,
                fontWeight = FontWeight.Bold
            )

            if (selectedTab == 0) {
                DashboardCard(
                    title = "LIVE TRACKING",
                    description = "See your train move in real time.",
                    detail = "GPS-powered • Satellite map",
                    label = "COMING IN PHASE 2"
                )

                Text(
                    "Explore Bangladesh Railways",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                DashboardCard(
                    title = "FIND YOUR TRAIN",
                    description = "Search trains and plan your journey.",
                    detail = "Train information",
                    label = "EXPLORE"
                )
            } else {
                DashboardCard(
                    title = when (selectedTab) {
                        1 -> "LIVE TRAIN MAP"
                        2 -> "TRAIN DIRECTORY"
                        else -> "YOUR ACCOUNT"
                    },
                    description = "This section is being prepared.",
                    detail = "More features coming soon",
                    label = "IN DEVELOPMENT"
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Text(
                "BANGLA RAIL LIVE • VERSION 1.0",
                color = Muted,
                fontSize = 10.sp
            )
        }
    }
}

@Composable
fun DashboardCard(
    title: String,
    description: String,
    detail: String,
    label: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = Panel)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                title,
                color = Blue,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.5.sp
            )
            Text(
                description,
                color = Color.White,
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold
            )
            Text(detail, color = Muted, fontSize = 12.sp)
            Surface(
                color = Blue.copy(alpha = 0.15f),
                shape = RoundedCornerShape(50.dp)
            ) {
                Text(
                    label,
                    modifier = Modifier.padding(horizontal = 13.dp, vertical = 8.dp),
                    color = Blue,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
