package de.honoka.test.various.test

import de.honoka.sdk.util.kotlin.lang.DirectDelegate
import org.junit.jupiter.api.Test

class DelegateTest {

    var a: Int = 0

    var b: Int by DirectDelegate(::a)

    object Container {

        var c = DelegateTest()

        var d: Int by DirectDelegate(c::a)
    }

    @Test
    fun test1() {
        println("$a $b")
        a = 1
        println("$a $b")
        b = 2
        println("$a $b")
        Container.run {
            println("${c.a} $d")
            c.a = 3
            println("${c.a} $d")
        }
    }
}
