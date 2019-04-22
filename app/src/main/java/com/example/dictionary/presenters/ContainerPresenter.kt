package com.example.dictionary.presenters

import com.arellomobile.mvp.MvpPresenter
import com.example.dictionary.models.ContainerModel
import com.example.dictionary.navigation.Screens
import com.example.dictionary.pojos.InfoTranslation
import com.example.dictionary.views.interfaces.ViewContainer
import ru.terrakok.cicerone.Router

class ContainerPresenter(
    private val model: ContainerModel,
    private val router: Router
) : MvpPresenter<ViewContainer>() {

    fun onBackCommandClick() =
        router.exit()

    fun showFirstScreen() =
        router.navigateTo(Screens.MenuScreen)

    fun setItemDictionary(item: InfoTranslation) {
        model.itemDictionary = item
    }

    fun getItemDictionary(): InfoTranslation =
        model.itemDictionary
}
