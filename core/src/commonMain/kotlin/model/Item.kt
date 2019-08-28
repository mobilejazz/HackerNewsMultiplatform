package com.mobilejazz.common.model

data class Item(
    val id : Int,
    val by : String,
    val title : String,
    val text : String,
    val kids : List<Int>
)