package com.example.dictionary.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.dictionary.pojos.InfoTranslation

@Database(entities = [InfoTranslation::class], version = 2)
abstract class AppDataBase : RoomDatabase() {
    abstract val translationDAO: TranslationDao
}
