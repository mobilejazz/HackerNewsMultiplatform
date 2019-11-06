package com.mobilejazz.common.model

import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val id: Int,
    val by: String?=null,
    val title: String?=null,
    val text: String?=null,
    val kids: List<Int>?=null
)
