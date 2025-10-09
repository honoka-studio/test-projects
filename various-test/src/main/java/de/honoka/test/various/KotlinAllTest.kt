package de.honoka.test.various

import de.honoka.sdk.util.kotlin.various.RuntimeUtilsExt
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test

class KotlinAllTest {

    @Test
    fun test22() {
        runBlocking {
            println(this)
            launch {
                println(this)
                launch {
                    println(this)
                }
            }
        }
    }

    @Test
    fun test21() {
        val s = "vaeivubediavbaeiufvbwbsrtniobntib"
        val map = s.groupingBy { it }.eachCount()
        val max = map.maxBy { it.value }
        println(map)
        println(max)
        println(max.key)
    }

    @Test
    fun test20() {
        //println(RuntimeUtils.exec(RuntimeUtils.Command.win("java", "-version")))
        //println(RuntimeUtils.exec(RuntimeUtils.Command.win("taskkill", "/f", "/im", "chrome.exe")))
        println(RuntimeUtilsExt.exec {
            win("java", "-version")
        })
        println(RuntimeUtilsExt.exec {
            //charset(StandardCharsets.UTF_8)
            win("taskkill", "/f", "/im", "chrome.exe")
        })
    }
    
    @Test
    fun test19() {
        try {
            error("abc")
        } catch(t: Throwable) {
            println("catch")
            throw t
        } finally {
            println("finally")
        }
    }
}
