package com.example.dictionary.presenters.utils

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import com.example.dictionary.models.database.AppDataBase
import com.example.dictionary.models.models.CacheModel
import com.example.dictionary.models.models.TranslationModel

class App : Application() {

    lateinit var dataBase: AppDataBase
        private set
    lateinit var modelCache: CacheModel
        private set
    lateinit var modelTranslation: TranslationModel
        private set
    lateinit var prefSettings: SharedPreferences
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this
        modelCache = CacheModel()
        modelTranslation = TranslationModel()
        prefSettings = getSharedPreferences("settings", Application.MODE_PRIVATE)
        dataBase = Room.databaseBuilder(this, AppDataBase::class.java, "database").build()
    }

    companion object {
        lateinit var instance: App
            private set
        fun appContext(): Context = instance.applicationContext
    }
}
