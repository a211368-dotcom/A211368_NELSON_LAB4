package com.example.a211368_nelson_lab2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.SemanticsProperties.ImeAction
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a211368_nelson_lab2.ui.theme.A211368_NELSON_LAB2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            A211368_NELSON_LAB2Theme {
                Scaffold(
                    floatingActionButton = {
                        FloatingActionButton(onClick = { /* TODO */ }) {
                            Text("+", fontSize = 24.sp)
                        }
                    },
                    bottomBar = { BottomNavigationBar() }
                ) { innerPadding ->
                    HomeScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    val chemistry = listOf("Acid Reaction", "Acid-Base Titration", "Electrolysis")
    val physics = listOf("Pendulum Motion", "Gravity Test")
    val biology = listOf("Plant Growth", "Cell Observation")

    var name by remember { mutableStateOf("") }
    var isSubmitted by remember { mutableStateOf(false) }

    Box(modifier = modifier.fillMaxSize()) {

        // Background image
        Image(
            painter = painterResource(id = R.drawable.lab2),
            contentDescription = "Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Dark overlay supaya text jelas
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x88000000))
        )

        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

            // Greeting / Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xAA03A9F4), RoundedCornerShape(16.dp))
                    .shadow(4.dp, RoundedCornerShape(16.dp))
                    .padding(horizontal = 20.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Column {
                    Text(
                        if (isSubmitted) "Welcome, $name" else "LabQuest",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    "A211368",
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.8f)
                )
            }

                Image(
                    painter = painterResource(id = R.drawable.lab_logo), // ganti dengan nama file logo awak
                    contentDescription = "App Logo",
                    modifier = Modifier
                        .size(40.dp) // saiz logo
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Fit
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

                if (!isSubmitted) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                    // TextField simple
                    TextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Enter your name") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0x55000000),
                                RoundedCornerShape(12.dp)),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = { if (name.isNotBlank()) isSubmitted = true },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE91E63))
                    ) {
                        Text(
                            text =  "Submit",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }

                // Greeting muncul selepas submit
                if (isSubmitted) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Welcome back, $name!",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

                Spacer(modifier = Modifier.height(24.dp))


            // Categories
            CategorySection("Chemistry", chemistry, Icons.Filled.Build)
            CategorySection("Physics", physics, Icons.Filled.FlashOn)
            CategorySection("Biology", biology, Icons.Filled.Nature)
        }
    }
}

@Composable
fun CategorySection(title: String, items: List<String>, icon: ImageVector) {
    Column(modifier = Modifier.fillMaxWidth()) {

        // Title row
        //header
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 8.dp)) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .background(Color(0xFF03A9F4), RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = title, tint = Color.White, modifier = Modifier.size(24.dp))
            }

            Spacer(modifier = Modifier.width(12.dp))

            Text(title, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Cards
        items.forEach { item ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .background(Color.White.copy(alpha = 0.9f), RoundedCornerShape(16.dp))
                    .padding(horizontal = 16.dp, vertical = 14.dp)
            ) {
                Text(item, fontSize = 16.sp, color = Color(0xFF222222))
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun BottomNavigationBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFFFFFF), RoundedCornerShape(topStart = 1.dp, topEnd = 1.dp))
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val navItems = listOf(
            "Home" to Icons.Filled.Home,
            "Class" to Icons.Filled.Person,
            "Me" to Icons.Filled.AccountCircle
        )

        navItems.forEach { (label, icon) ->
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(icon, contentDescription = label, tint = Color.Black, modifier = Modifier.size(28.dp))
                Text(label, fontSize = 12.sp, color = Color.Black, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    A211368_NELSON_LAB2Theme {
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(onClick = {}) { Text("+", fontSize = 24.sp) }
            },
            bottomBar = { BottomNavigationBar() }
        ) { innerPadding ->
            HomeScreen(modifier = Modifier.padding(innerPadding))
        }
    }
}