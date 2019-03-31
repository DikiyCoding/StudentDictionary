package com.example.dictionary.utils

import com.example.dictionary.network.apis.interfaces.YandexTranslateApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {

    private val client: OkHttpClient
    private val yandexApi: YandexTranslateApi
//    private val oxfordApi: OxfordDictionariesApi

    init {
        client = buildClient()
        yandexApi = buildRetrofit(Constants.YANDEX_BASE_URL).create(YandexTranslateApi::class.java)
//        oxfordApi = buildRetrofit("Oxford url").create(OxfordDictionariesApi::class.java)
    }

    fun getYandexApi(): YandexTranslateApi = yandexApi

//    fun getOxfordApi(): OxfordDictionariesApi = oxfordApi

    private fun getClient(): OkHttpClient = client

    private fun buildRetrofit(url: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .client(getClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private fun buildClient(): OkHttpClient {
        return OkHttpClient.Builder()
            /*.addInterceptor(ApiKeyInterceptor.create())
            .addInterceptor(LoggingInterceptor.create())
            .addInterceptor(new StethoInterceptor())*/
            .build()
    }
}
