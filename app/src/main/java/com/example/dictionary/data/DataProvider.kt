package com.example.dictionary.data

import com.example.dictionary.repository.CacheRepository
import com.example.dictionary.utils.LimitedDataBaseArrayList

class DataProvider(repositoryCache: CacheRepository) {
    val dataTranslation: TranslationData = TranslationData(
        ArrayList(),
        LimitedDataBaseArrayList(repositoryCache),
        ArrayList(),
        HashMap()
    )
}
