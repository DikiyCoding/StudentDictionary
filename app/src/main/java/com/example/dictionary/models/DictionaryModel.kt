package com.example.dictionary.models

import android.arch.paging.PagedList
import android.arch.paging.PositionalDataSource
import com.example.dictionary.pojos.InfoTranslation
import com.example.dictionary.repository.CacheRepository
import com.example.dictionary.utils.*
import java.util.concurrent.Executors

class DictionaryModel {

    private val cacheRepository: CacheRepository
    private val dataSource: PositionalDataSource<InfoTranslation>
    private val config: PagedList.Config

    val pagedList: PagedList<InfoTranslation>
    val callback: DictionaryDiffUtil

    init {
        cacheRepository = CacheRepository()
        callback = DictionaryDiffUtil()
        dataSource = DictionaryDataSource(cacheRepository)
        config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(App.instance.prefSettings.getInt("pagination", Constants.pageSizeDefValue))
            .build()
        pagedList = PagedList.Builder(dataSource, config)
            .setFetchExecutor(Executors.newSingleThreadExecutor())
            .setNotifyExecutor(MainThreadExecutor())
            .build()
    }

    fun getItem(position: Int): InfoTranslation? =
        pagedList[position]
}
