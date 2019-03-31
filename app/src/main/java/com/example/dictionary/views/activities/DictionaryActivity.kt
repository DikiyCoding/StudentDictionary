package com.example.dictionary.views.activities

import android.content.Intent
import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.dictionary.R
import com.example.dictionary.models.DictionaryModel
import com.example.dictionary.presenters.DictionaryPresenter
import com.example.dictionary.views.adapters.DictionaryAdapter
import com.example.dictionary.views.interfaces.ViewDictionary
import kotlinx.android.synthetic.main.activity_dictionary.*

class DictionaryActivity : MvpAppCompatActivity(), ViewDictionary {

    @InjectPresenter
    lateinit var presenter: DictionaryPresenter

    @ProvidePresenter
    fun provideDictionaryPresenter(): DictionaryPresenter =
        DictionaryPresenter(DictionaryModel())

    private lateinit var wordDetail: Intent
    private lateinit var adapterCache: DictionaryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dictionary)
        init()
    }

    private fun init() {
        createAdapters()
        assignAdapters()
        createIntents()
    }

    private fun createAdapters() {
        adapterCache = DictionaryAdapter(presenter.getCallback()) { position ->
            wordDetail.putExtra("item", presenter.onItemClick(position))
            startActivity(wordDetail)
        }
        adapterCache.submitList(presenter.getPagedList())
    }

    private fun assignAdapters() {
        list_translations.adapter = adapterCache
    }

    private fun createIntents() {
        wordDetail = Intent(this, WordDetailActivity::class.java)
    }
}
