package com.mobilejazz.common.presenter
import com.mobilejazz.common.getAskStoriesIds
import com.mobilejazz.common.getItem
import com.mobilejazz.common.model.Item
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

interface ItemDetailPresenter {

    interface View {
        fun onEventDisplay(item: Item, kids: List<Item>)
    }

    fun onAppearWith(itemId: Int)
}

class ItemDetailDefaultPresenter(val view: ItemDetailPresenter.View) : ItemDetailPresenter {

    override fun onAppearWith(itemId: Int) {
        // TODO: fetch item and kids data

        val scope = MainScope()
        scope.launch {
            val item = getItem(itemId)
            var ids = emptyList<Int>()
            if (item.kids != null) {
                ids = item.kids!!
            }
            val kids = ids.map { async { getItem(it) } }.awaitAll()
            view.onEventDisplay(item, kids)
        }

        /*
        view.onEventDisplay(
            Item(itemId,"Jose Luis Franconetti", "Neque porro quisquam est qui dolorem", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur fermentum facilisis odio, a porttitor mi commodo id. Aliquam finibus leo sed tempus vestibulum. Pellentesque tempus ante lectus, at condimentum purus imperdiet malesuada. Ut quis semper arcu, sit amet placerat elit. Nam at mi ante. Mauris ullamcorper at elit in efficitur. Aliquam nec metus urna. Maecenas cursus venenatis mi vel dictum. Integer at condimentum tortor, et pellentesque felis. Duis a massa molestie, porttitor urna non, commodo quam. Sed non facilisis odio, at tristique mauris. Quisque volutpat diam eu nunc rhoncus hendrerit. Cras turpis turpis, blandit ut ipsum et, iaculis commodo metus. Praesent aliquam ut risus at tincidunt.", emptyList()),
            listOf(
            Item(5,"Stefan Klumpp", "", "Curabitur fermentum facilisis odio, a porttitor mi commodo id. ", emptyList()),
            Item(6,"Jordi Gimenez", "", "Aliquam ac tellus nec lectus ullamcorper auctor vel vel lacus. Nulla at euismod augue. Duis fermentum ligula felis. Quisque ornare nulla ut orci tristique, eleifend viverra mi tincidunt.", emptyList())
        ))
         */
    }
}
