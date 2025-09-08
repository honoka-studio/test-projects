package de.honoka.lavender.android.uitest.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import de.honoka.lavender.android.uitest.ui.AppDefaultTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainActivityView()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainActivityView() {
    var dark by remember { mutableStateOf(false) }
    AppDefaultTheme(dark) {
        var selectedIndex by remember { mutableIntStateOf(0) }
        val items = listOf("首页", "设置")
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            //containerColor = Color(0xFFE8F5E9),
            bottomBar = {
                NavigationBar {
                    items.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = selectedIndex == index,
                            onClick = {
                                selectedIndex = index
                                dark = !dark
                            },
                            label = { Text(item) },
                            icon = { /* 可添加图标 */ }
                        )
                    }
                }
            }
        ) {
            Column(Modifier.padding(it)) {
                when(selectedIndex) {
                    0 -> HomeScreen()
                    1 -> SettingsScreen()
                }
            }
        }
    }
}

@Composable
private fun HomeScreen() {
    repeat(50) {
        Text("这是首页 $it")
    }
}

@Composable
private fun SettingsScreen() {
    Text("这是设置页")
}
