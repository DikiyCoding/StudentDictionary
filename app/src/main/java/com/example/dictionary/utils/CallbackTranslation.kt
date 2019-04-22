package com.example.dictionary.utils

interface CallbackTranslation {
    fun translationChanged(translation: String): Unit?
    fun langsFromChanged()
    fun langsToChanged()
    fun cacheChanged()
}
