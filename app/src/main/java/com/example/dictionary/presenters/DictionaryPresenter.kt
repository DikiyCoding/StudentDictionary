package com.example.dictionary.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.dictionary.models.DictionaryModel
import com.example.dictionary.navigation.Screens
import com.example.dictionary.pojos.InfoTranslation
import com.example.dictionary.views.interfaces.ViewDictionary
import ru.terrakok.cicerone.Router

@InjectViewState
class DictionaryPresenter(
    private val model: DictionaryModel,
    private val router: Router
) : MvpPresenter<ViewDictionary>() {

    fun getCallback() =
        model.callback

    fun getPagedList() =
        model.pagedList

    fun onItemClick(position: Int): InfoTranslation =
        model.getItem(position)

    fun onForwardCommandClick() =
        router.navigateTo(Screens.WordDetailScreen)
}
