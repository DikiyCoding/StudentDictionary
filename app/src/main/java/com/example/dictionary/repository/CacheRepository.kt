package com.example.dictionary.repository

import com.example.dictionary.database.TranslationDao
import com.example.dictionary.pojos.InfoTranslation
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CacheRepository(private val dao: TranslationDao) {

    fun getCache(): Single<List<InfoTranslation>> =
        dao.getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getCache(position: Int, size: Int): Single<List<InfoTranslation>> =
        dao.getAll(position, size)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun setCache(translation: InfoTranslation): Completable =
        Completable.fromAction {
            dao.insert(translation)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun updateCache(translation: InfoTranslation): Completable =
        Completable.fromAction {
            dao.update(translation)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getCacheByValues(valueFrom: String, valueTo: String): Single<InfoTranslation> =
        dao.getByTranslation(valueFrom, valueTo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun deleteCache(translation: InfoTranslation): Completable =
        Completable.fromAction {
            dao.delete(translation)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}
