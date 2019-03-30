package com.example.dictionary.models.models

import com.example.dictionary.models.network.apis.utils.ApiFactory
import com.example.dictionary.models.network.apis.utils.Constants
import com.example.dictionary.models.network.apis.yandex.LangsAvailable
import com.example.dictionary.models.network.apis.yandex.Translation
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
class TranslationModel {

    //TODO ApiKeyInterceptor
    fun getLanguages(): Single<LangsAvailable> =
        ApiFactory
            .getYandexApi()
            .getLangs(Constants.YANDEX_API_KEY, "ru")
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())

    fun getTranslation(text: String, lang: String): Single<Translation> =
        ApiFactory
            .getYandexApi()
            .translate(Constants.YANDEX_API_KEY, text, lang)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
}
