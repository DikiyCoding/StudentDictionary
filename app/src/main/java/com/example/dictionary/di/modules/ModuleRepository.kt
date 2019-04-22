package com.example.dictionary.di.modules

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import com.example.dictionary.data.TranslationData
import com.example.dictionary.database.AppDataBase
import com.example.dictionary.database.TranslationDao
import com.example.dictionary.network.apis.interfaces.YandexTranslateApi
import com.example.dictionary.repository.CacheRepository
import com.example.dictionary.repository.TranslationRepository
import com.example.dictionary.utils.Constants
import com.example.dictionary.utils.LimitedDataBaseArrayList
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ModuleRepository {

    @Provides
    @Singleton
    fun provideDataBase(context: Context): AppDataBase =
        Room.databaseBuilder(context, AppDataBase::class.java, Constants.DATABASE).build()

    @Provides
    @Singleton
    fun provideTranslationDao(database: AppDataBase): TranslationDao =
        database.translationDAO

    @Provides
    @Singleton
    fun provideCacheRepository(dao: TranslationDao)
            : CacheRepository = CacheRepository(dao)

    @Provides
    @Singleton
    fun provideTranslationRepository(yandex: YandexTranslateApi)
            : TranslationRepository = TranslationRepository(yandex)

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context)
            : SharedPreferences = context.getSharedPreferences(Constants.PREFERENCES, Application.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideTranslationData(repository: CacheRepository)
            : TranslationData =
        TranslationData(
            ArrayList(),
            LimitedDataBaseArrayList(repository),
            ArrayList(),
            HashMap()
        )
}
