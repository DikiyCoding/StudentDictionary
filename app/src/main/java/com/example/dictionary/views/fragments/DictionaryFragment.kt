package com.example.dictionary.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.dictionary.R
import com.example.dictionary.presenters.DictionaryPresenter
import com.example.dictionary.utils.App
import com.example.dictionary.views.activities.ContainerActivity
import com.example.dictionary.views.adapters.DictionaryAdapter
import com.example.dictionary.views.interfaces.ViewDictionary
import kotlinx.android.synthetic.main.fragment_dictionary.view.*

class DictionaryFragment : MvpAppCompatFragment(), ViewDictionary {

    @InjectPresenter
    lateinit var presenter: DictionaryPresenter

    @ProvidePresenter
    fun provideDictionaryPresenter()
            : DictionaryPresenter = App.appComponent.getDictionaryPresenter()

    private lateinit var adapterCache: DictionaryAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_dictionary, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
    }

    private fun init(view: View) {
        createAdapters()
        assignAdapters(view)
    }

    private fun createAdapters() {
        adapterCache = DictionaryAdapter(presenter.getCallback()) { position ->
            (activity as ContainerActivity).setItemDictionary(presenter.onItemClick(position))
            presenter.onForwardCommandClick()
        }
        adapterCache.submitList(presenter.getPagedList())
    }

    private fun assignAdapters(view: View) {
        view.list_translations.adapter = adapterCache
    }
}
