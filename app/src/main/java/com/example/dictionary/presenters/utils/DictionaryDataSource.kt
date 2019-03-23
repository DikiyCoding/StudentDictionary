package com.example.dictionary.presenters.utils

import android.arch.paging.PositionalDataSource
import android.util.Log
import com.example.dictionary.models.models.CacheModel
import com.example.dictionary.presenters.pojos.InfoTranslation
import io.reactivex.rxkotlin.subscribeBy

class DictionaryDataSource(private val modelCache: CacheModel) : PositionalDataSource<InfoTranslation>() {

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<InfoTranslation>) {
        Log.d("Logs", "loadInitial, requestedStartPosition = "
                + params.requestedStartPosition + ", requestedLoadSize = " + params.requestedLoadSize)
        modelCache.getCache(
            params.requestedStartPosition,
            params.requestedLoadSize).subscribeBy(onSuccess = {callback.onResult(it, 0)})
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<InfoTranslation>) {
        Log.d("Logs", "loadRange, startPosition = "
                + params.startPosition + ", loadSize = " + params.loadSize)
        modelCache.getCache(
            params.startPosition,
            params.loadSize).subscribeBy(onSuccess = {callback.onResult(it)})
    }
}
