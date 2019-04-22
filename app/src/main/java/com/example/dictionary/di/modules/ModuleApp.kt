package com.example.dictionary.di.modules

import android.content.Context
import com.example.dictionary.utils.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ModuleApp(private val app: App) {

    @Provides
    @Singleton
    fun provideApp(): Context = app
}
