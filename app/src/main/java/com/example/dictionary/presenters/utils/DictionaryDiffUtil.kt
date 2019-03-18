package com.example.dictionary.presenters.utils

import android.support.v7.util.DiffUtil
import com.example.dictionary.presenters.pojos.InfoTranslation

class DictionaryDiffUtil : DiffUtil.ItemCallback<InfoTranslation>() {

    override fun areItemsTheSame(itemOld: InfoTranslation, itemNew: InfoTranslation): Boolean {
        return itemOld.isSame(itemNew)
    }

    override fun areContentsTheSame(itemOld: InfoTranslation, itemNew: InfoTranslation): Boolean {
        return itemOld.isSame(itemNew)
    }
}
