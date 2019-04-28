package com.example.dictionary.di.modules

import com.example.dictionary.network.apis.interfaces.YandexTranslateApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ModuleService {

    @Provides
    @Singleton
    fun provideYandexApi(retrofit: Retrofit): YandexTranslateApi =
        retrofit.create(YandexTranslateApi::class.java)
}
