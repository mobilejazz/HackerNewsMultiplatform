package com.mobilejazz.common

expect fun platformName(): String

class Foo {

    fun bar() {
        print("Foo - bar")
    }
}

interface Executor {
    fun foo(): Int?
}

data class Person(val name: String)

fun test(executor: Executor): Int? = executor.foo()

class GetInteractor<T> {

    fun execute(v: T): T = v
}

fun createApplicationScreenMessage(): String {
    return "Kotlin Rocks on ${platformName()}"
}