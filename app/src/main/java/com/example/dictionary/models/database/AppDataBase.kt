package com.example.dictionary.models.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.dictionary.presenters.pojos.InfoTranslation

@Database(entities = [InfoTranslation::class], version = 2)
abstract class AppDataBase : RoomDatabase() {
    abstract val translationDAO: TranslationDao
}
