package com.example.dictionary.presenters

import com.example.dictionary.models.WordDetailModel
import com.example.dictionary.pojos.InfoTranslation
import com.example.dictionary.views.interfaces.ViewWordDetail
import com.example.dictionary.views.interfaces.`ViewWordDetail$$State`
import junit.framework.Assert.assertEquals
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
class TestWordDetailPresenter {

    private val errorMessage = "Сохраняемые значения не могут быть пусты"

    @Mock
    lateinit var item: InfoTranslation

    @Mock
    lateinit var mockViewState: `ViewWordDetail$$State`

    @Mock
    lateinit var model: WordDetailModel

    @Mock
    lateinit var router: Router

    @InjectMocks
    @Spy
    lateinit var presenter: WordDetailPresenter

    @Before
    fun setUp() {
        presenter.attachView(mockViewState)
    }

    @Test
    fun onFirstViewAttach() {
        val mockView = mock(ViewWordDetail::class.java)
        presenter.attachView(mockView)
        verify(presenter, atLeastOnce()).attachView(mockView)
    }

    @Test
    fun getCurrentItemTest() {
        `when`(model.translation).thenReturn(item)
        assertEquals(model.translation, item)
        verify(model).translation
    }

    @Test
    fun setCurrentItemTest() {
        `when`(model.translation).thenReturn(item)
        presenter.setCurrentItem(model.translation)
        verify(model).translation
    }

    @Test
    fun handleValuesSuccess() {
        val valueFrom = "Денчик"
        val valueTo = "Denchik"
        presenter.handleValues(valueFrom, valueTo)
        verify(model).updateCache(valueFrom, valueTo)
        verify(mockViewState).updateValues(getCurrentItem())
        verify(mockViewState, never()).showErrorMessage(errorMessage)
    }

    @Test
    fun handleValuesError() {
        var valueFrom = "Денчик"
        var valueTo = ""
        presenter.handleValues(valueFrom, valueTo)
        verify(model, never()).updateCache(valueFrom, valueTo)
        verify(mockViewState, never()).updateValues(getCurrentItem())
        verify(mockViewState).showErrorMessage(errorMessage)

        valueFrom = ""
        valueTo = "Denchik"
        presenter.handleValues(valueFrom, valueTo)
        verify(model, never()).updateCache(valueFrom, valueTo)
        verify(mockViewState, never()).updateValues(getCurrentItem())
        verify(mockViewState, times(2)).showErrorMessage(errorMessage)

        valueFrom = ""
        valueTo = ""
        presenter.handleValues(valueFrom, valueTo)
        verify(model, never()).updateCache(valueFrom, valueTo)
        verify(mockViewState, never()).updateValues(getCurrentItem())
        verify(mockViewState, times(3)).showErrorMessage(errorMessage)
    }

    @Test
    fun updateValues() {
        presenter.updateValues()
        verify(mockViewState).updateValues(getCurrentItem())
    }

    private fun getCurrentItem(): InfoTranslation =
        model.translation
}
