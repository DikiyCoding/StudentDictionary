package com.example.dictionary.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.dictionary.models.MenuModel
import com.example.dictionary.views.interfaces.ViewMenu

@InjectViewState
class MenuPresenter(private val model: MenuModel)
    : MvpPresenter<ViewMenu>()
