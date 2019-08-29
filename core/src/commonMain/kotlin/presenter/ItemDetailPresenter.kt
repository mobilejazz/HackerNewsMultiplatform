package com.mobilejazz.common.presenter
import com.mobilejazz.common.model.Item

interface ItemDetailPresenter {

    interface View {
        fun onEventDisplay(comments: List<Item>)

    }

    fun onAppearWith(item: Item)
    fun onActionRefresh(item: Item)
}

class ItemDetailDefaultPresenter(val view: ItemDetailPresenter.View) : ItemDetailPresenter {
    override fun onAppearWith(item: Item) {
        // TODO: fetch data
        view.onEventDisplay(listOf(
            Item(5,"Stefan Klump", "", "Curabitur fermentum facilisis odio, a porttitor mi commodo id. ", emptyList()),
            Item(6,"Jordi Gimenez", "", "Aliquam ac tellus nec lectus ullamcorper auctor vel vel lacus. Nulla at euismod augue. Duis fermentum ligula felis. Quisque ornare nulla ut orci tristique, eleifend viverra mi tincidunt.", emptyList())
        ))
    }

    override fun onActionRefresh(item: Item) {
        // TODO: Reload comments
        // For now, we are just re-calling onAppear to trigger the rendering of the list.
        onAppearWith(item)
    }
}
