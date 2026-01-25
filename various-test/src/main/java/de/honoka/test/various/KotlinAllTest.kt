package de.honoka.test.various

import cn.hutool.json.JSONObject
import de.honoka.sdk.util.kotlin.lang.tryCast
import de.honoka.sdk.util.kotlin.various.RuntimeUtilsExt
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.reflect.KClass
import kotlin.reflect.full.starProjectedType
import kotlin.reflect.jvm.javaType
import kotlin.reflect.typeOf

@Suppress("UNCHECKED_CAST")
class KotlinAllTest {

    @Test
    fun test24() {
        val json = JSONObject().set("a", "b")
        println(json.tryCast<JSONObject>(typeOf<JSONObject>()))
    }

    @Test
    fun test23() {
        val type = this::class.starProjectedType
        println(type)
        val type2 = typeOf<Map<String, Int>>()
        println(type2.javaType)
        val classes = arrayListOf(type2.classifier as KClass<*>).apply {
            addAll(type2.arguments.map { it.type!!.classifier as KClass<*> })
        }
        classes.forEach {
            println(it)
        }
    }

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
