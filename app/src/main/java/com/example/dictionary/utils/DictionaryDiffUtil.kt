package com.example.dictionary.utils

import android.support.v7.util.DiffUtil
import com.example.dictionary.pojos.InfoTranslation

class DictionaryDiffUtil : DiffUtil.ItemCallback<InfoTranslation>() {

    override fun areItemsTheSame(
        itemOld: InfoTranslation,
        itemNew: InfoTranslation
    ): Boolean = itemOld.isSame(itemNew)

    override fun areContentsTheSame(
        itemOld: InfoTranslation,
        itemNew: InfoTranslation
    ): Boolean = itemOld.isSame(itemNew)
}
