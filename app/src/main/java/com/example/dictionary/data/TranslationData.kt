package com.example.dictionary.data

import com.example.dictionary.pojos.InfoLanguage
import com.example.dictionary.pojos.InfoTranslation

class TranslationData(
    val langs: MutableList<InfoLanguage>,
    val translations: MutableList<InfoTranslation>,
    val langsSupported: MutableList<InfoLanguage>,
    val langsSearchable: MutableMap<String, InfoLanguage>
)
