package de.honoka.lavender.android.uitest.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import de.honoka.lavender.android.uitest.ui.style.AppDefaultTheme
import de.honoka.lavender.android.uitest.ui.style.Fonts
import de.honoka.sdk.util.android.basic.initGlobalComponents
import de.honoka.sdk.util.android.basic.launchOnIo
import de.honoka.sdk.util.android.ui.fullScreen
import de.honoka.sdk.util.android.ui.switchActivity
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fullScreen()
        setContent {
            MainActivityView()
        }
        initGlobalComponents()
        launchOnIo {
            initApplication()
            switchActivity(HomeActivity::class)
        }
    }

    private suspend fun initApplication() {
        delay(1000)
    }
}

@Preview(showBackground = true)
@Composable
private fun MainActivityView() {
    AppDefaultTheme {
        Box(
            Modifier.fillMaxSize(),
            Alignment.Center
        ) {
            Text(
                "Lavender",
                style = TextStyle(
                    fontFamily = Fonts.kunstler,
                    fontWeight = FontWeight.Bold,
                    fontSize = 70.sp
                )
            )
        }
    }
}
