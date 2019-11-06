package com.mobilejazz.common

import com.mobilejazz.common.model.Item
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlinx.serialization.list
import kotlinx.serialization.serializer

suspend fun getAskStoriesIds(): List<Int> {
    // https://hacker-news.firebaseio.com/v0/askstories.json
    val client = HttpClient()
    val result = client.get<String>("https://hacker-news.firebaseio.com/v0/askstories.json")
    println("IDS: $result")
    val json = Json(JsonConfiguration.Stable)
    val array = json.parse(Int.serializer().list, result)
    client.close()
    return array
}

suspend fun getItem(id: Int): Item {
    // https://hacker-news.firebaseio.com/v0/item/$id.json
    val client = HttpClient()
    val result = client.get<String>("https://hacker-news.firebaseio.com/v0/item/$id.json")
    val json = Json(JsonConfiguration.Stable.copy(strictMode = false))
    val item = json.parse(Item.serializer(), result)
    client.close()
    return item
}