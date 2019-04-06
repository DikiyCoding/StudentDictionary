package com.example.dictionary.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.dictionary.models.DictionaryModel
import com.example.dictionary.pojos.InfoTranslation
import com.example.dictionary.views.interfaces.ViewDictionary

@InjectViewState
class DictionaryPresenter(private val model: DictionaryModel)
    : MvpPresenter<ViewDictionary>() {

    fun getCallback() =
        model.callback

    fun getPagedList() =
        model.pagedList

    fun onItemClick(position: Int): InfoTranslation? =
        model.getItem(position)
}
