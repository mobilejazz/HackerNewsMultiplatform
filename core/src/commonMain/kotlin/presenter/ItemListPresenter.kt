package com.mobilejazz.common.presenter

import com.mobilejazz.common.model.Item
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlinx.serialization.list
import kotlinx.serialization.serializer

interface ItemListPresenter {

    interface View {
        fun onEventDisplay(items: List<Item>)
        fun onEventNavigateTo(item: Item)
    }

    fun onAppear()
    fun onActionDidSelect(item: Item)
    fun onActionRefresh()
}

class ItemListDefaultPresenter(val view: ItemListPresenter.View) : ItemListPresenter {

    private val baseUrl = "https://hacker-news.firebaseio.com/v0"

    override fun onAppear() {
        MainScope().launch {

            val client = HttpClient {
                install(JsonFeature) {
                    serializer = KotlinxSerializer(Json(JsonConfiguration.Stable.copy(strictMode = false)))
                }
            }

            // Get the content of an URL.
            val rawStories = client.get<String>("$baseUrl/askstories.json")
            val parsedStories =
                Json(JsonConfiguration.Stable.copy(strictMode = false)).parse(Int.serializer().list, rawStories)


           val items = parsedStories.map {
                client.get<Item>("$baseUrl/item/$it.json")
            }
            view.onEventDisplay(items)
        }
    }

    override fun onActionDidSelect(item: Item) {
        view.onEventNavigateTo(item)
    }

    override fun onActionRefresh() {
        // TODO: refresh AskStories data
        // For now, we are just re-calling onAppear to trigger the rendering of the list.
        onAppear()
    }
}
