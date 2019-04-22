package com.example.dictionary.di.modules

import android.arch.paging.PagedList
import android.content.SharedPreferences
import com.example.dictionary.pojos.InfoTranslation
import com.example.dictionary.repository.CacheRepository
import com.example.dictionary.utils.Constants
import com.example.dictionary.utils.DictionaryDataSource
import com.example.dictionary.utils.DictionaryDiffUtil
import com.example.dictionary.utils.MainThreadExecutor
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Named
import javax.inject.Singleton

@Module
class ModulePagedList {

    @Provides
    @Singleton
    fun provideDiffUtil()
            : DictionaryDiffUtil = DictionaryDiffUtil()

    @Provides
    @Singleton
    fun provideDataSource(repository: CacheRepository)
            : DictionaryDataSource = DictionaryDataSource(repository)

    @Provides
    @Singleton
    fun provideConfig(preferences: SharedPreferences)
            : PagedList.Config =
        PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(preferences.getInt(Constants.KEY_PAGE, Constants.pageSizeDefValue))
            .build()

    @Provides
    @Singleton
    @Named("Fetch Executor")
    fun provideFetchExecutor()
            : Executor = Executors.newSingleThreadExecutor()

    @Provides
    @Singleton
    @Named("Notify Executor")
    fun provideNotifyExecutor()
            : Executor = MainThreadExecutor()

    @Provides
    @Singleton
    fun providePagedList(
        dataSource: DictionaryDataSource,
        config: PagedList.Config,
        @Named("Fetch Executor") fetchExecutor: Executor,
        @Named("Notify Executor") notifyExecutor: Executor
    )
            : PagedList<InfoTranslation> =
        PagedList.Builder(dataSource, config)
            .setFetchExecutor(fetchExecutor)
            .setNotifyExecutor(notifyExecutor)
            .build()
}
