package com.example.dictionary.models.database

import android.arch.persistence.room.*
import com.example.dictionary.presenters.pojos.InfoTranslation
import io.reactivex.Single

@Dao
interface TranslationDao {

    @Query("SELECT * FROM translations WHERE is_saved = 0")
    fun getAll(): Single<List<InfoTranslation>>

    @Query("SELECT * FROM translations WHERE is_saved = 1 LIMIT :size OFFSET :position")
    fun getAll(position: Int, size: Int): Single<List<InfoTranslation>>

    @Query("SELECT * FROM translations WHERE value_from = :value_from AND value_to = :value_to")
    fun getByTranslation(value_from: String, value_to: String): Single<InfoTranslation>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(translation: InfoTranslation)

    @Update
    fun update(translation: InfoTranslation)

    @Delete
    fun delete(translation: InfoTranslation)
}
