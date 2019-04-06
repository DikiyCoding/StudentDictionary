package com.example.dictionary.models

import com.example.dictionary.pojos.InfoTranslation
import com.example.dictionary.repository.CacheRepository

class WordDetailModel(private val repositoryCache: CacheRepository) {

    lateinit var translation: InfoTranslation
    lateinit var save: InfoTranslation

    fun updateCache(valueFrom: String, valueTo: String) {
        save = InfoTranslation(
            valueFrom,
            valueTo,
            translation.langFrom,
            translation.langTo,
            translation.isSaved
        )
        repositoryCache.deleteCache(translation)
        repositoryCache.setCache(save)
        translation = save
    }

    fun deleteCache() {
        repositoryCache.deleteCache(translation)
        //Back to dictionary
    }
}
