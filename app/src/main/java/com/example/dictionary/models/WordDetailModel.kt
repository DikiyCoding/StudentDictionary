package com.example.dictionary.models

import com.example.dictionary.pojos.InfoTranslation
import com.example.dictionary.repository.CacheRepository
import com.example.dictionary.repository.RepositoryProvider

class WordDetailModel {

    lateinit var translation: InfoTranslation
    lateinit var save: InfoTranslation

    private val repositoryCache: CacheRepository =
        RepositoryProvider().repositoryCache

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
