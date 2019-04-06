package com.example.dictionary.utils

object Constants {

    /**
     * Repository
     */
    const val DATABASE = "database"
    const val PREFERENCES = "settings"
    const val KEY_PAGE = "pagination"

    /**
     * Paging Library
     */
    const val pageSizeMinValue = 5
    const val pageSizeMaxValue = 20
    const val pageSizeDefValue = 10

    /**
     * Yandex Translate API
     */
    const val YANDEX_API_KEY = "trnsl.1.1.20190225T125027Z.a1e30e8767c2a327.62d0d2fe8e6ec665bf56ff80160d479e84be8366"
    const val YANDEX_BASE_URL = "https://translate.yandex.net/api/v1.5/tr.json/"
    const val YANDEX_LANGS_AVAILABLE = "getLangs?"
    const val YANDEX_TRANSLATION = "translate?"

    /**
     * Word Detail Activity
     */
    const val toastXOffset = 350

    /**
     * Translation Presenter
     */
    const val supLangSubStr = 3

    /**
     * Limited Database ArrayList
     */
    const val transListSizeMax = 14
}
