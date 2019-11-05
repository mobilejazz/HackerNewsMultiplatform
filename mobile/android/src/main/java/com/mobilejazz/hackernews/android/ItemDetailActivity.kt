package com.mobilejazz.hackernews.android

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobilejazz.common.model.Item
import com.mobilejazz.common.presenter.ItemDetailDefaultPresenter
import com.mobilejazz.common.presenter.ItemDetailPresenter
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlinx.android.synthetic.main.activity_item_list.*

class ItemDetailActivity : AppCompatActivity(), ItemDetailPresenter.View {

    companion object {
        const val ITEM_ID = "item-id"
        fun getIntent(context: Context, item: Item): Intent = Intent(context, ItemDetailActivity::class.java).apply { putExtra(ITEM_ID, item.id) }
    }

    private val presenter : ItemDetailPresenter by lazy {
        ItemDetailDefaultPresenter(this)
    }

    private val adapter by lazy {
        ItemsAdapter(listener = {
            // Nothing to do
        }, displayAllContent = false)
    }

    private val itemId : Int
        get() = intent.extras.getInt(ITEM_ID)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)

        title = "Hacker News"
        presenter.onAppearWith(itemId)
    }

    // MARK: ItemDetailPresenter.View

    override fun onEventDisplay(item: Item, kids: List<Item>) {
        item_detail_title_tv.text = item.title
        item_detail_description_tv.text = item.text
        item_detail_by_tv.text = item.by

        val linearLayoutManager = LinearLayoutManager(this)
        item_detail_kids_list.layoutManager = linearLayoutManager
        item_detail_kids_list.addItemDecoration(DividerItemDecoration(this, linearLayoutManager.orientation))
        item_detail_kids_list.adapter = adapter

        adapter.reloadData(kids)
    }
}