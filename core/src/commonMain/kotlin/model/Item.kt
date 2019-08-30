package com.mobilejazz.common.model

//import kotlinx.serialization.Serializable

//@Serializable
data class Item(
    val id : Int,
    val by : String,
    val title : String,
    val text : String,
    val kids : List<Int>
)
