package com.example.dictionary.presenters.presenters

import android.arch.paging.PagedList
import android.arch.paging.PositionalDataSource
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.dictionary.models.models.CacheModel
import com.example.dictionary.presenters.adapters.DictionaryAdapter
import com.example.dictionary.presenters.pojos.InfoTranslation
import com.example.dictionary.presenters.utils.App
import com.example.dictionary.presenters.utils.DictionaryDataSource
import com.example.dictionary.presenters.utils.DictionaryDiffUtil
import com.example.dictionary.presenters.utils.MainThreadExecutor
import com.example.dictionary.views.interfaces.ViewDictionary
import java.util.concurrent.Executors

@InjectViewState
class DictionaryPresenter : MvpPresenter<ViewDictionary>() {

    private val modelCache: CacheModel
    private val translations: MutableList<InfoTranslation>
    private val dataSource: PositionalDataSource<InfoTranslation>
    private val config: PagedList.Config
    private val pagedList: PagedList<InfoTranslation>
    private val adapterCache: DictionaryAdapter
    private val callback: DictionaryDiffUtil

    init {
        translations = ArrayList()
        modelCache = CacheModel()
        callback = DictionaryDiffUtil()
        dataSource = DictionaryDataSource(modelCache)
        config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(App.instance.prefSettings.getInt("pagination", 10))
            .build()
        pagedList = PagedList.Builder(dataSource, config)
            .setFetchExecutor(Executors.newSingleThreadExecutor())
            .setNotifyExecutor(MainThreadExecutor)
            .build()
        adapterCache = DictionaryAdapter(callback)
        adapterCache.submitList(pagedList)
    }

    fun getAdapter(key: String): Any? {
        return when (key) {
            "adapterCache" -> adapterCache
            else -> null
        }
    }
}
