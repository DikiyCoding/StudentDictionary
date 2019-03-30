package com.example.dictionary.views.activities

import android.arch.paging.PagedListAdapter
import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.dictionary.R
import com.example.dictionary.presenters.presenters.DictionaryPresenter
import com.example.dictionary.views.interfaces.ViewDictionary
import kotlinx.android.synthetic.main.activity_dictionary.*

class DictionaryActivity : MvpAppCompatActivity(), ViewDictionary {

    @InjectPresenter
    lateinit var presenter: DictionaryPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dictionary)
        init()
    }

    private fun init() {
        list_translations.adapter = presenter.getAdapter("adapterCache") as PagedListAdapter<*, *>
    }
}
