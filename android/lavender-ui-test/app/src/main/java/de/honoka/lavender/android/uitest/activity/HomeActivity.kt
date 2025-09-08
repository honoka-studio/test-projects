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
import de.honoka.lavender.android.uitest.ui.style.AppDefaultTheme

class HomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HomeActivityView()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeActivityView() {
    AppDefaultTheme {
        val pageMap = mapOf(
            "视频" to {},
            "动态" to {},
            "设置" to {}
        )
        var page by remember { mutableStateOf("视频") }
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                NavigationBar {
                    pageMap.keys.forEach {
                        NavigationBarItem(
                            selected = page == it,
                            onClick = {
                                page = it
                            },
                            label = { Text(it) },
                            icon = { /* 可添加图标 */ }
                        )
                    }
                }
            }
        ) {
            Column(Modifier.padding(it)) {

            }
        }
    }
}
