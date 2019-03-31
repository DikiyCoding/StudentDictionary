package com.example.dictionary.utils

interface CallbackTranslation {
    fun translationChanged(translation: String)
    fun langsFromChanged()
    fun langsToChanged()
    fun cacheChanged()
}
