package com.example.dictionary.repository

class RepositoryProvider {
    val repositoryCache: CacheRepository =
        CacheRepository()
    val repositoryTranslation: TranslationRepository =
        TranslationRepository()
}
