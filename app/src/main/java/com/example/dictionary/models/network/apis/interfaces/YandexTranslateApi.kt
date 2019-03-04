package com.example.dictionary.models.network.apis.interfaces

import com.example.dictionary.models.network.apis.utils.Constants
import com.example.dictionary.models.network.apis.yandex.LangsAvailable
import com.example.dictionary.models.network.apis.yandex.Translation

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface YandexTranslateApi {

    @GET(Constants.YANDEX_LANGS_AVAILABLE)
    fun getLangs(@Query("key") KEY: String, @Query("ui") ui: String): Single<LangsAvailable>

    @GET(Constants.YANDEX_TRANSLATION)
    fun translate(@Query("key") KEY: String, @Query("text") text: String, @Query("lang") lang: String): Single<Translation>
}
