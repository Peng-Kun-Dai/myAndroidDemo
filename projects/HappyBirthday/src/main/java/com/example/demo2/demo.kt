package com.example.demo2

class SmartDevice(
    val name: String,
    val category: String,
    val deviceType: String
) {
    fun printDeviceInfo() {
        println("Device name: $name, category: $category, type: $deviceType")
    }

}


fun trickOrTreat(
    isTrick: Boolean,
    extraTreat: (Int) -> String
): () -> Unit {
    if (isTrick) {
        return trick
    } else {
        println(extraTreat(5))
        return treat
    }
}

val trick = {
    println("No treats!")
}

val treat = {
    println("Have a treat!")
}
val funname = { name: String ->

}

fun main() {

    val coins: (Int, Int) -> String = { quantity1, quantity2 ->
        "$quantity1 quarters"
    }

    println(
        coins(
            5,
            6
        )
    )

    val treatFunction = trickOrTreat(
        false
    ) { "$it quarters" }

    repeat(5,{
        println()
    })
}