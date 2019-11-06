package com.mobilejazz.hackernews.android

import android.os.Bundle
import android.util.Log
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobilejazz.common.model.Item
import com.mobilejazz.common.presenter.ItemListDefaultPresenter
import com.mobilejazz.common.presenter.ItemListPresenter
import kotlinx.android.synthetic.main.activity_item_list.*
import kotlin.time.ExperimentalTime


@ExperimentalTime
class ItemListActivity : AppCompatActivity(), ItemListPresenter.View {

    private val presenter : ItemListPresenter by lazy {
        ItemListDefaultPresenter(this)
    }

    private val adapter by lazy {
        ItemsAdapter(listener = {
            presenter.onActionDidSelect(it)
        }, displayAllContent = false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)

        title = "Hacker News"

        val linearLayoutManager = LinearLayoutManager(this)
        activity_item_list.layoutManager = linearLayoutManager
        activity_item_list.addItemDecoration(DividerItemDecoration(this, linearLayoutManager.orientation))
        activity_item_list.adapter = adapter

        activity_item_list_swipe_refresh.setOnRefreshListener {
            presenter.onActionRefresh()
        }

        presenter.onAppear()
    }

    // MARK: ItemListPresenter.View

    override fun onEventDisplay(items: List<Item>) {
        activity_item_list_swipe_refresh.isRefreshing = false
        adapter.reloadData(items)
    }

    override fun onEventNavigateTo(item: Item) {
        Log.d("itemlist", "Navigate to: $item")
        startActivity(ItemDetailActivity.getIntent(this, item))
    }
}
