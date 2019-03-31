package com.example.dictionary.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.dictionary.models.WordDetailModel
import com.example.dictionary.pojos.InfoTranslation
import com.example.dictionary.views.interfaces.ViewWordDetail

@InjectViewState
class WordDetailPresenter(private val model: WordDetailModel) : MvpPresenter<ViewWordDetail>() {

    private fun updateCache(valueFrom: String, valueTo: String) =
        model.updateCache(valueFrom, valueTo)

    private fun getCurrentItem(): InfoTranslation =
        model.translation

    //TODO Regular Expression
    fun handleValues(valueFrom: String, valueTo: String) {
        if (valueFrom == "" || valueTo == "") {
            viewState.showErrorMessage("Сохраняемые значения не могут быть пусты")
        } else {
            updateCache(valueFrom, valueTo)
            updateValues()
        }
    }

    fun updateValues() =
        viewState.updateValues(getCurrentItem())

    fun setCurrentItem(translation: InfoTranslation) {
        model.translation = translation
    }
}
