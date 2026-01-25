package de.honoka.test.various.test

import de.honoka.sdk.util.gui.ConsoleWindow
import de.honoka.sdk.util.lang.ThrowsRunnable

object ConsoleWindowTest {

    @JvmStatic
    fun main(args: Array<String>) {
        ConsoleWindow.Builder.of().apply {
            windowName = "ConsoleWindowTest"
            screenZoomScale = 1.25
            isBackgroundMode = true
            onExit = ThrowsRunnable {}
            isShowOnBuild = false
        }.build()
        for(i in 1..200) {
            println("ConsoleWindowTest $i")
            Thread.sleep(200)
        }
    }
}