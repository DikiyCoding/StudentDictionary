package com.example.dictionary.models.models

import com.example.dictionary.presenters.pojos.InfoTranslation
import com.example.dictionary.presenters.utils.App
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CacheModel {

    fun getCache(): Single<List<InfoTranslation>> =
        App.instance.dataBase
            .translationDAO
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getCache(position: Int, size: Int): Single<List<InfoTranslation>> =
        App.instance.dataBase
            .translationDAO
            .getAll(position, size)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun setCache(translation: InfoTranslation): Completable =
        Completable.fromAction {
            App.instance.dataBase
                .translationDAO
                .insert(translation)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun updateCache(translation: InfoTranslation): Completable =
        Completable.fromAction {
            App.instance.dataBase
                .translationDAO
                .update(translation)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getCacheByValues(valueFrom: String, valueTo: String): Single<InfoTranslation> =
        App.instance.dataBase
            .translationDAO
            .getByTranslation(valueFrom, valueTo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun deleteCache(translation: InfoTranslation): Completable =
        Completable.fromAction {
            App.instance.dataBase
                .translationDAO
                .delete(translation)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}
