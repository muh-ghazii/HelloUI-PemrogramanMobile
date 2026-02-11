package com.contoh.pemrogamanmobileweek1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isLightTheme by remember {
                mutableStateOf(false)
            }
            MaterialTheme(
                colorScheme =
                    if (isLightTheme) lightColorScheme()
                    else darkColorScheme()
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HelloUIScreen(
                        isLightTheme = isLightTheme,
                        onThemeChange = {
                            isLightTheme = it
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun HelloUIScreen(isLightTheme: Boolean, onThemeChange: (Boolean) -> Unit) {
    var name by remember {
        mutableStateOf("")
    }
    var greetingText by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Masukkan Nama Kamu!") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { greetingText = "Halo, $name! Bagaimana Kabarmu?" },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Sapa Sekarang!")
        }
        Spacer(modifier = Modifier.height(20.dp))
        if (greetingText.isNotEmpty()) {
            Text(
                text = greetingText,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.inverseSurface
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(14.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Mode Gelap/Terang")
            Switch(
                checked = isLightTheme,
                onCheckedChange = { onThemeChange(it) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MaterialTheme {
        HelloUIScreen(isLightTheme = false, onThemeChange = {})
    }
}