package com.example.dictionary.models.network.apis.utils

import com.example.dictionary.models.network.apis.interfaces.OxfordDictionariesApi
import com.example.dictionary.models.network.apis.interfaces.YandexTranslateApi

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {

    private var client: OkHttpClient? = null
    @Volatile
    private var yandexApi: YandexTranslateApi? = null
    @Volatile
    private var oxfordApi: OxfordDictionariesApi? = null

    fun getYandexApi(): YandexTranslateApi {
        var service = yandexApi
        if (service == null)
            synchronized(ApiFactory::class.java) {
                service = yandexApi
                if (service == null) {
                    yandexApi = buildRetrofit(Constants.YANDEX_BASE_URL).create(YandexTranslateApi::class.java)
                    service = yandexApi
                }
            }
        return service!!
    }

    fun getOxfordApi(): OxfordDictionariesApi {
        var service = oxfordApi
        if (service == null)
            synchronized(ApiFactory::class.java) {
                service = oxfordApi
                if (service == null) {
                    oxfordApi = buildRetrofit(Constants.YANDEX_BASE_URL).create(OxfordDictionariesApi::class.java)
                    service = oxfordApi
                }
            }
        return service!!
    }

    fun recreate() {
        client = null
        client = getClient()
        yandexApi = buildRetrofit(Constants.YANDEX_BASE_URL).create(YandexTranslateApi::class.java)
        oxfordApi = buildRetrofit("Oxford url").create(OxfordDictionariesApi::class.java)
    }

    private fun buildRetrofit(url: String): Retrofit {
        return Retrofit.Builder()
                .baseUrl(url)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    private fun getClient(): OkHttpClient {
        var client = ApiFactory.client
        if (client == null)
            synchronized(ApiFactory::class.java) {
                client = ApiFactory.client
                if (client == null) {
                    ApiFactory.client = buildClient()
                    client = ApiFactory.client
                }
            }
        return client!!
    }

    private fun buildClient(): OkHttpClient {
        return OkHttpClient.Builder()
                /*.addInterceptor(ApiKeyInterceptor.create())
                .addInterceptor(LoggingInterceptor.create())
                .addInterceptor(new StethoInterceptor())*/
                .build()
    }
}
