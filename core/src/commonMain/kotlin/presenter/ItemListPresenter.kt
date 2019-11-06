package com.mobilejazz.common.presenter
import com.mobilejazz.common.getAskStoriesIds
import com.mobilejazz.common.getItem
import com.mobilejazz.common.model.Item

import kotlinx.coroutines.MainScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

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
    override fun onAppear() {
        // TODO: fetch AskStories data

        val scope = MainScope()
        scope.launch {
            val ids = getAskStoriesIds()
            //var items = mutableListOf<Item>()
            val items = ids.map { async { getItem(it) } }.awaitAll()
            view.onEventDisplay(items)
        }

        /*
        view.onEventDisplay(listOf(
            Item(1,"Jose Luis Franconetti", "Neque porro quisquam est qui dolorem", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur fermentum facilisis odio, a porttitor mi commodo id. Aliquam finibus leo sed tempus vestibulum. Pellentesque tempus ante lectus, at condimentum purus imperdiet malesuada. Ut quis semper arcu, sit amet placerat elit. Nam at mi ante. Mauris ullamcorper at elit in efficitur. Aliquam nec metus urna. Maecenas cursus venenatis mi vel dictum. Integer at condimentum tortor, et pellentesque felis. Duis a massa molestie, porttitor urna non, commodo quam. Sed non facilisis odio, at tristique mauris. Quisque volutpat diam eu nunc rhoncus hendrerit. Cras turpis turpis, blandit ut ipsum et, iaculis commodo metus. Praesent aliquam ut risus at tincidunt.", emptyList()),
            Item(2,"Joan Martin", "Duis a massa molestie", "Duis facilisis dolor nec erat efficitur eleifend. Aliquam ac tellus nec lectus ullamcorper auctor vel vel lacus. Nulla at euismod augue. Vestibulum vel egestas massa, at laoreet eros. Duis fermentum ligula felis. Quisque ornare nulla ut orci tristique, eleifend viverra mi tincidunt. Fusce tincidunt interdum lorem, sed semper mauris tincidunt vitae. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Donec venenatis rhoncus dolor, vel maximus neque iaculis id. Aliquam viverra elementum elit, et posuere tortor sollicitudin at. In hac habitasse platea dictumst. Interdum et malesuada fames ac ante ipsum primis in faucibus. In maximus, lacus id ultricies consequat, urna neque pellentesque sem, at suscipit leo sapien ac augue.", emptyList()),
            Item(3,"Fran Montiel", "Venenatis rhoncus dolor", "Ut nulla ante, rhoncus in lorem et, laoreet euismod lorem. Fusce feugiat molestie est sit amet venenatis. Suspendisse molestie facilisis mi in tempor. Sed elit tortor, pharetra vel sapien ut, maximus maximus velit. Duis ut metus vitae nulla iaculis dictum. Suspendisse imperdiet viverra tincidunt. Nullam rutrum a velit vel condimentum. Aenean volutpat nisi pulvinar enim euismod, sit amet fringilla justo efficitur. Vestibulum nec augue pharetra, maximus dolor eu, sodales tellus. Mauris congue efficitur vehicula. Suspendisse orci lorem, rutrum et posuere sed, finibus eu leo.", emptyList()),
            Item(4,"Borja Arias", "Malesuada elementum tellus", "Fusce pellentesque erat sed mi tincidunt, sit amet finibus nisl vestibulum. Mauris malesuada elementum tellus, at porttitor neque ornare ac. Nam ultricies ornare lacinia. Donec tempor porta metus, iaculis dictum lectus pellentesque vitae. Duis et semper neque. Aliquam aliquet felis eu leo faucibus mollis. Donec id venenatis mauris, in suscipit sem. Nam molestie, dolor sit amet mollis ornare, dolor dolor consectetur ipsum, in eleifend mi justo a eros. Vivamus mollis luctus mauris a accumsan. Ut vulputate posuere sapien ac laoreet. Vivamus ipsum sem, feugiat eget libero nec, cursus interdum nisi. Mauris eu porta dui. Curabitur in eros sed dolor tempor pharetra. Donec sed vestibulum felis. Morbi hendrerit mauris non ornare rhoncus.", emptyList())
        ))
         */
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
