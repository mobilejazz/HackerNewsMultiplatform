package com.mobilejazz.hackernews.android

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mobilejazz.common.model.Item
import com.mobilejazz.common.presenter.ItemDetailDefaultPresenter
import com.mobilejazz.common.presenter.ItemDetailPresenter

class ItemDetailActivity : AppCompatActivity(), ItemDetailPresenter.View {

    companion object {
        const val ITEM_KEY = "item-key"
    //    fun getIntent(context: Context, item: Item): Intent = Intent(context, ItemDetailActivity::class.java).apply { putExtra(ITEM_KEY, item) }
    }

    private val presenter : ItemDetailPresenter by lazy {
        ItemDetailDefaultPresenter(this)
    }

    private val item : Item
        get() = intent.extras.get(ITEM_KEY) as Item

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = "Hacker News"
    }

    // MARK: ItemDetailPresenter.View

    override fun onEventDisplay(comments: List<Item>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}