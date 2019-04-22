package com.example.dictionary.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.dictionary.models.MenuModel
import com.example.dictionary.views.interfaces.ViewMenu
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppScreen

@InjectViewState
class MenuPresenter(
    private val model: MenuModel,
    private val router: Router
) : MvpPresenter<ViewMenu>() {

    fun onForwardCommandClick(screen: SupportAppScreen) =
        router.navigateTo(screen)
}
