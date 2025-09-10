package de.honoka.lavender.android.uitest.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import de.honoka.lavender.android.uitest.ui.style.AppDefaultTheme
import de.honoka.lavender.android.uitest.ui.view.DynamicView
import de.honoka.lavender.android.uitest.ui.view.SettingsView
import de.honoka.lavender.android.uitest.ui.view.VideoListView
import de.honoka.lavender.android.uitest.util.ui.compose.NavigationBarItemInfo
import de.honoka.lavender.android.uitest.util.ui.compose.NavigationBarView

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
        val navigationBarItems = listOf(
            NavigationBarItemInfo("视频", null, { VideoListView() }),
            NavigationBarItemInfo("动态", null, { DynamicView() }),
            NavigationBarItemInfo("设置", null, { SettingsView() })
        )
        NavigationBarView(navigationBarItems)
    }
}
