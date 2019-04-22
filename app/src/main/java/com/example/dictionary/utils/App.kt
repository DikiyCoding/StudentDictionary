package com.example.dictionary.utils

import android.app.Application
import com.example.dictionary.di.components.AppComponent
import com.example.dictionary.di.components.DaggerAppComponent
import com.example.dictionary.di.modules.*

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .moduleApp(ModuleApp(this))
            .moduleModel(ModuleModel())
            .moduleNavigation(ModuleNavigation())
            .moduleNetwork(ModuleNetwork())
            .modulePagedList(ModulePagedList())
            .modulePresenter(ModulePresenter())
            .moduleRepository(ModuleRepository())
            .moduleService(ModuleService())
            .build()
    }

    companion object {
        lateinit var appComponent: AppComponent
    }
}
