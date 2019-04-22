package com.example.dictionary.repository

import com.example.dictionary.network.apis.interfaces.YandexTranslateApi
import com.example.dictionary.network.apis.yandex.LangsAvailable
import com.example.dictionary.network.apis.yandex.Translation
import com.example.dictionary.utils.Constants
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Yandex Translate API:
 * получение доступных языков,
 * перевод с одного языка на другой;
 *
 * Oxford Dictionary API (для английского):
 * значение слова на английском;
 */
class TranslationRepository(private val yandex: YandexTranslateApi) {

    //TODO ApiKeyInterceptor
    fun getLanguages(): Single<LangsAvailable> =
        yandex
            .getLangs(Constants.YANDEX_API_KEY, "ru")
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())

    fun getTranslation(text: String, lang: String): Single<Translation> =
        yandex
            .translate(Constants.YANDEX_API_KEY, text, lang)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
}
