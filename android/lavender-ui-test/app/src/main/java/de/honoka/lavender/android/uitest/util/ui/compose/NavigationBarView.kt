package de.honoka.lavender.android.uitest.util.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun NavigationBarView(items: List<NavigationBarItemInfo>) {
    val itemsMap = items.associateBy { it.name }
    var selectedItemName by remember { mutableStateOf(items.first().name) }
    Scaffold(
        Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                items.forEach {
                    NavigationBarItem(
                        selectedItemName == it.name,
                        {
                            selectedItemName = it.name
                        },
                        { /* 可添加图标 */ },
                        label = { Text(it.name) }
                    )
                }
            }
        }
    ) {
        Column(Modifier.padding(it)) {
            itemsMap[selectedItemName]!!.view()
        }
    }
}

data class NavigationBarItemInfo(

    var name: String,

    var icon: String?,

    var view: @Composable () -> Unit
)
