package com.example.dictionary.utils

import android.util.Log
import com.example.dictionary.pojos.InfoTranslation
import com.example.dictionary.repository.CacheRepository
import io.reactivex.rxkotlin.subscribeBy
import java.util.*

class LimitedDataBaseArrayList(private val repositoryCache: CacheRepository) : ArrayList<InfoTranslation>() {

    override fun add(element: InfoTranslation): Boolean {
        if (super.contains(element))
            return false
        if (super.size == Constants.transListSizeMax)
            this.remove(super.get(0))
            repositoryCache
            .setCache(element)
            .subscribeBy(onComplete = { Log.i("Logs", "Элемент добавлен!!!") })
        return super.add(element)
    }

    override fun remove(element: InfoTranslation): Boolean {
        if (!super.contains(element))
            return false
            repositoryCache
            .deleteCache(element).subscribeBy(onComplete = { Log.i("Logs", "Элемент удалён!!!") })
        return super.remove(element)
    }
}
