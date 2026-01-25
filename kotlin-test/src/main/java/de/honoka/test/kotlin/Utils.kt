package de.honoka.test.kotlin

fun printWithThread(obj: Any?) {
    println("[${Thread.currentThread().name}] [${System.currentTimeMillis()}] ${obj?.toString()}")
}
