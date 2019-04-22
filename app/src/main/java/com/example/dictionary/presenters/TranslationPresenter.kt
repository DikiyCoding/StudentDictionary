package com.example.dictionary.presenters

import android.view.View
import android.widget.AdapterView
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.dictionary.models.TranslationModel
import com.example.dictionary.pojos.InfoLanguage
import com.example.dictionary.utils.CallbackTranslation
import com.example.dictionary.views.interfaces.ViewTranslation
import ru.terrakok.cicerone.Router

@InjectViewState
class TranslationPresenter(
    private val model: TranslationModel,
    private val router: Router
) : MvpPresenter<ViewTranslation>(), CallbackTranslation {

    init {
        model.callback = this
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getLanguages()
    }

    override fun attachView(view: ViewTranslation?) {
        super.attachView(view)
        getCache()
    }

    override fun translationChanged(translation: String) =
        viewState.setTranslation(translation)

    override fun cacheChanged() =
        viewState.setCache()

    override fun langsFromChanged() =
        viewState.setLangsFrom()

    override fun langsToChanged() =
        viewState.setLangsTo()

    private fun getCache() =
        model.getCache()

    private fun updateCache(position: Int) =
        model.updateCache(position)

    private fun deleteCache(position: Int) =
        model.deleteCache(position)

    private fun getLanguages() =
        model.getLanguages()

    fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) =
        model.onItemSelected(parent, view, position, id)

    fun onItemClick(action: String, position: Int) {
        when (action) {
            "save" -> updateCache(position)
            "delete" -> deleteCache(position)
        }
    }

    fun translate(word: String, from: InfoLanguage, to: InfoLanguage) =
        model.translate(word, from, to)

    fun getListLangs() =
        model.getListLangs()

    fun getListLangsSup() =
        model.getListLangsSup()

    fun getListLangsSearch() =
        model.getListLangsSearch()

    fun getListTransl() =
        model.getListTransl()
}
