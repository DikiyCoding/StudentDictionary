package com.example.dictionary.models

import android.arch.paging.PagedList
import com.example.dictionary.pojos.InfoTranslation
import com.example.dictionary.utils.DictionaryDiffUtil

class DictionaryModel(
    val callback: DictionaryDiffUtil,
    val pagedList: PagedList<InfoTranslation>
) {

    fun getItem(position: Int): InfoTranslation =
        pagedList[position] as InfoTranslation
}
