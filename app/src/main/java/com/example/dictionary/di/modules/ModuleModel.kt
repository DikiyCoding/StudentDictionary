package com.example.dictionary.di.modules

import android.arch.paging.PagedList
import com.example.dictionary.data.TranslationData
import com.example.dictionary.models.*
import com.example.dictionary.pojos.InfoTranslation
import com.example.dictionary.repository.CacheRepository
import com.example.dictionary.repository.TranslationRepository
import com.example.dictionary.utils.DictionaryDiffUtil
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ModuleModel {

    @Provides
    @Singleton
    fun provideDictionaryModel(
        callback: DictionaryDiffUtil,
        pagedList: PagedList<InfoTranslation>
    ): DictionaryModel = DictionaryModel(callback, pagedList)

    @Provides
    @Singleton
    fun provideMenuModel()
            : MenuModel = MenuModel()

    @Provides
    @Singleton
    fun provideTranslationModel(
        repositoryCache: CacheRepository,
        dataTranslation: TranslationData,
        repositoryTranslation: TranslationRepository
    ): TranslationModel = TranslationModel(
        repositoryCache,
        dataTranslation,
        repositoryTranslation
    )

    @Provides
    @Singleton
    fun provideWordDetailModel(repositoryCache: CacheRepository)
            : WordDetailModel = WordDetailModel(repositoryCache)

    @Provides
    @Singleton
    fun provideContainerModel()
            : ContainerModel = ContainerModel()
}
