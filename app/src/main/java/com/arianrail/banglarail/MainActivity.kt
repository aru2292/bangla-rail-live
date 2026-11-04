package com.arianrail.banglarail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Night = Color(0xFF050914)
private val Panel = Color(0xFF0D1728)
private val Blue = Color(0xFF19B8FF)
private val Muted = Color(0xFF91A4BC)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { RailApp() }
    }
}

@Composable
fun RailApp() {
    var selected by remember { mutableIntStateOf(0) }
    MaterialTheme(
        colorScheme = darkColorScheme(
            background = Night,
            surface = Panel,
            primary = Blue,
            secondary = Color(0xFF5B8CFF),
            onBackground = Color.White,
            onSurface = Color.White
        )
    ) {
        Scaffold(
            containerColor = Night,
            bottomBar = {
                NavigationBar(containerColor = Panel, contentColor = Blue) {
                    val items = listOf("Home", "Live Map", "Trains", "Profile")
                    val icons = listOf(Icons.Default.Home, Icons.Default.Map, Icons.Default.Train, Icons.Default.Person)
                    items.forEachIndexed { index, label ->
                        NavigationBarItem(
                            selected = selected == index,
                            onClick = { selected = index },
                            icon = { Icon(icons[index], contentDescription = label) },
                            label = { Text(label, fontSize = 10.sp) }
                        )
                    }
                }
            }
        ) { padding ->
            when (selected) {
                0 -> HomeScreen(Modifier.padding(padding))
                1 -> PlaceholderScreen("LIVE SATELLITE MAP", "3D satellite tracking will be added in the next phase.", Modifier.padding(padding))
                2 -> PlaceholderScreen("TRAIN DIRECTORY", "Search and browse Bangladesh train services.", Modifier.padding(padding))
                else -> PlaceholderScreen("YOUR PROFILE", "Account, GPS sharing and app settings.", Modifier.padding(padding))
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize().background(
            Brush.verticalGradient(listOf(Color(0xFF07172A), Night))
        ),
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        item {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(Modifier.weight(1f)) {
                    Text("BANGLA RAIL LIVE", color = Blue, fontSize = 12.sp, fontWeight = FontWeight.Bold, letterSpacing = 2.sp)
                    Text("Track your journey.", color = Color.White, fontSize = 26.sp, fontWeight = FontWeight.Bold)
                    Text("Bangladesh railway, reimagined.", color = Muted, fontSize = 13.sp)
                }
                Surface(color = Color(0xFF102943), shape = RoundedCornerShape(16.dp)) {
                    Icon(Icons.Default.Train, null, tint = Blue, modifier = Modifier.padding(14.dp).size(28.dp))
                }
            }
        }
        item {
            OutlinedTextField(
                value = "",
                onValueChange = {},
                readOnly = true,
                placeholder = { Text("Search train name or number", color = Muted) },
                leadingIcon = { Icon(Icons.Default.Search, null, tint = Blue) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Blue,
                    unfocusedBorderColor = Color(0xFF26364D),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )
        }
        item {
            Box(
                Modifier.fillMaxWidth().height(190.dp)
                    .background(Brush.linearGradient(listOf(Color(0xFF102D4B), Color(0xFF101B35))), RoundedCornerShape(22.dp))
                    .border(1.dp, Color(0xFF1C466B), RoundedCornerShape(22.dp))
                    .padding(18.dp)
            ) {
                Column(Modifier.align(Alignment.CenterStart), verticalArrangement = Arrangement.spacedBy(9.dp)) {
                    Text("LIVE TRACKING", color = Blue, fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 2.sp)
                    Text("See your train move in real time.", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                    Text("GPS-powered • Satellite map", color = Muted, fontSize = 12.sp)
                    Surface(color = Blue, shape = RoundedCornerShape(50)) {
                        Text("COMING IN PHASE 2", color = Night, fontSize = 10.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 12.dp, vertical = 7.dp))
                    }
                    Icon(Icons.Default.SatelliteAlt, null, tint = Color(0xFF3D8FC5), modifier = Modifier.align(Alignment.BottomEnd).size(66.dp))
            }
        }
        item {
            Text("QUICK ACCESS", color = Muted, fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 2.sp)
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                QuickCard("Find trains", Icons.Default.Train, Modifier.weight(1f))
                QuickCard("Stations", Icons.Default.LocationOn, Modifier.weight(1f))
            }
        }
        item {
            Text("TRACKING STATUS", color = Muted, fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 2.sp)
            Surface(color = Panel, shape = RoundedCornerShape(16.dp), modifier = Modifier.fillMaxWidth()) {
                Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.GpsNotFixed, null, tint = Blue, modifier = Modifier.size(28.dp))
                    Spacer(Modifier.width(12.dp))
                    Column {
                        Text("GPS network not connected", color = Color.White, fontWeight = FontWeight.SemiBold)
                        Text("Live tracking will activate after backend setup.", color = Muted, fontSize = 11.sp)
                    }
                }
            }
        }
        item {
            Text("Built for Bangladesh 🇧🇩", color = Muted, fontSize = 12.sp, modifier = Modifier.fillMaxWidth())
        }
    }
}

@Composable
fun QuickCard(title: String, icon: androidx.compose.ui.graphics.vector.ImageVector, modifier: Modifier = Modifier) {
    Surface(color = Panel, shape = RoundedCornerShape(16.dp), modifier = modifier) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Icon(icon, null, tint = Blue, modifier = Modifier.size(25.dp))
            Text(title, color = Color.White, fontWeight = FontWeight.SemiBold)
            Text("Coming soon", color = Muted, fontSize = 11.sp)
        }
    }
}

@Composable
fun PlaceholderScreen(title: String, description: String, modifier: Modifier = Modifier) {
    Column(
        modifier.fillMaxSize().background(Night).padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(Icons.Default.Train, null, tint = Blue, modifier = Modifier.size(56.dp))
        Spacer(Modifier.height(18.dp))
        Text(title, color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        Text(description, color = Muted, fontSize = 13.sp)
        Spacer(Modifier.height(20.dp))
        Text("BANGLA RAIL LIVE • v1.0.0", color = Blue, fontSize = 11.sp)
    }
}
