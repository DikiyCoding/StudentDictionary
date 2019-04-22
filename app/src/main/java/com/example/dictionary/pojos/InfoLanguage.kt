package com.example.dictionary.pojos

class InfoLanguage(
    val langSign: String,
    val name: String,
    val supportedLanguages: MutableList<InfoLanguage>
)
