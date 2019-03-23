package com.example.dictionary.presenters.utils

import android.util.Log
import com.example.dictionary.presenters.pojos.InfoTranslation
import io.reactivex.rxkotlin.subscribeBy
import java.util.*

class LimitedDataBaseArrayList : ArrayList<InfoTranslation>() {

    private val sizeMax = 14

    override fun add(element: InfoTranslation): Boolean {
        if (super.contains(element))
            return false
        if (super.size == sizeMax)
            this.remove(super.get(0))
        App.instance.
            modelCache.
            setCache(element).
            subscribeBy(onComplete = { Log.i("Logs", "Элемент добавлен!!!") })
        return super.add(element)
    }

    override fun remove(element: InfoTranslation): Boolean {
        if (!super.contains(element))
            return false
        App.instance.
            modelCache.
            deleteCache(element).
            subscribeBy(onComplete = { Log.i("Logs", "Элемент удалён!!!") })
        return super.remove(element)
    }
}
