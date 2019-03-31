package com.example.dictionary.network.apis.interfaces

import com.example.dictionary.network.apis.yandex.LangsAvailable
import com.example.dictionary.network.apis.yandex.Translation
import com.example.dictionary.utils.Constants

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface YandexTranslateApi {

    @GET(Constants.YANDEX_LANGS_AVAILABLE)
    fun getLangs(
        @Query("key") KEY: String,
        @Query("ui") ui: String
    ): Single<LangsAvailable>

    @GET(Constants.YANDEX_TRANSLATION)
    fun translate(
        @Query("key") KEY: String,
        @Query("text") text: String,
        @Query("lang") lang: String
    ): Single<Translation>
}
