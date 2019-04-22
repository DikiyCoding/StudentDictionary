package com.example.dictionary.utils

import android.arch.paging.PositionalDataSource
import android.util.Log
import com.example.dictionary.pojos.InfoTranslation
import com.example.dictionary.repository.CacheRepository
import io.reactivex.rxkotlin.subscribeBy

class DictionaryDataSource(private val repositoryCache: CacheRepository) : PositionalDataSource<InfoTranslation>() {

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<InfoTranslation>) {
        Log.d(
            "Logs", "loadInitial, requestedStartPosition = "
                    + params.requestedStartPosition + ", requestedLoadSize = " + params.requestedLoadSize
        )
        repositoryCache.getCache(
            params.requestedStartPosition,
            params.requestedLoadSize
        ).subscribeBy(onSuccess = { callback.onResult(it, 0) })
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<InfoTranslation>) {
        Log.d(
            "Logs", "loadRange, startPosition = "
                    + params.startPosition + ", loadSize = " + params.loadSize
        )
        repositoryCache.getCache(
            params.startPosition,
            params.loadSize
        ).subscribeBy(onSuccess = { callback.onResult(it) })
    }
}
