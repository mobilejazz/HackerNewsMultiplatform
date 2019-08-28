package com.mobilejazz.common.presenter
import com.mobilejazz.common.model.Item

interface ItemListPresenter {

    interface View {
        fun onEventDisplay(items: List<Item>)
    }

    fun onAppear()
}

class ItemListDefaultPresenter(val view: ItemListPresenter.View) : ItemListPresenter {
    override fun onAppear() {
        view.onEventDisplay(listOf(
            Item(1,"Jose Luis", "Hello World", "Lorem ipsum", emptyList()),
            Item(2,"Joan", "Hamburguesa", "Lorem ipsum", emptyList()),
            Item(3,"Monti", "iPhone rulez", "Lorem ipsum", emptyList()),
            Item(4,"Borja", "Patata picante", "Lorem ipsum", emptyList())
        ))
    }
}
