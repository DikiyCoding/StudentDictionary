package com.example.dictionary.presenters

import android.view.View
import android.widget.AdapterView
import com.example.dictionary.models.TranslationModel
import com.example.dictionary.pojos.InfoLanguage
import com.example.dictionary.views.interfaces.ViewTranslation
import com.example.dictionary.views.interfaces.`ViewTranslation$$State`

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner
import ru.terrakok.cicerone.Router

@RunWith(MockitoJUnitRunner::class)
class TestTranslationPresenter {

    @Mock
    lateinit var mockViewState: `ViewTranslation$$State`

    @Mock
    lateinit var model: TranslationModel

    @Mock
    lateinit var router: Router

    @InjectMocks
    @Spy
    lateinit var presenter: TranslationPresenter

    @Before
    fun setUpView() {
        presenter.attachView(mockViewState)
    }

    @Test
    fun onFirstViewAttach() {
        val mockView = mock(ViewTranslation::class.java)
        presenter.attachView(mockView)
        verify(presenter, atLeastOnce()).attachView(mockView)
        verify(model, atLeastOnce()).getCache()
        verify(model).getLanguages()
    }

    @Test
    fun onItemSelected() {
        val parent: AdapterView<*> = mock(AdapterView::class.java)
        val view: View = mock(View::class.java)
        val position = 0
        val id = 100L
        presenter.onItemSelected(parent, view, position, id)
        verify(presenter).onItemSelected(parent, view, position, id)
    }

    @Test
    fun getLists() {
        presenter.getListLangs()
        verify(model).getListLangs()
        presenter.getListLangsSup()
        verify(model).getListLangsSup()
        presenter.getListLangsSearch()
        verify(model).getListLangsSearch()
        presenter.getListTransl()
        verify(model).getListTransl()
    }

    @Test
    fun onItemClick() {
        val position = 0
        val actionSave = "save"
        val actionDelete = "delete"
        presenter.onItemClick(actionSave, position)
        presenter.onItemClick(actionDelete, position)
        verify(model).updateCache(position)
        verify(model).deleteCache(position)
        verify(presenter).onItemClick(actionSave, position)
        verify(presenter).onItemClick(actionDelete, position)
    }

    @Test
    fun translate() {
        val word = "Hello World"
        val mockInfo: InfoLanguage = mock(InfoLanguage::class.java)
        presenter.translate(word, mockInfo, mockInfo)
        verify(presenter).translate(word, mockInfo, mockInfo)
    }

    @Test
    fun updateAdaptersData() {
        val translation = "something changed"
        presenter.cacheChanged()
        verify(mockViewState).setCache()
        presenter.langsFromChanged()
        verify(mockViewState).setLangsFrom()
        presenter.langsToChanged()
        verify(mockViewState).setLangsTo()
        presenter.translationChanged(translation)
        verify(mockViewState).setTranslation(translation)
    }
}
