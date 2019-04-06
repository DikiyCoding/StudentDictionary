package com.example.dictionary.presenters

import com.example.dictionary.models.DictionaryModel
import com.example.dictionary.pojos.InfoTranslation
import com.example.dictionary.utils.DictionaryDiffUtil
import com.example.dictionary.views.interfaces.ViewDictionary
import com.example.dictionary.views.interfaces.`ViewDictionary$$State`
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TestDictionaryPresenter {

    @Mock
    lateinit var mockViewState: `ViewDictionary$$State`

    @Mock
    lateinit var model: DictionaryModel

    @InjectMocks
    @Spy
    lateinit var presenter: DictionaryPresenter

    @Before
    fun setUp() {
        presenter.attachView(mockViewState)
    }

    @Test
    fun onFirstViewAttach() {
        val mockView = mock(ViewDictionary::class.java)
        presenter.attachView(mockView)
        verify(presenter, atLeastOnce()).attachView(mockView)
    }

    @Test
    fun getCallback() {
        val callback: DictionaryDiffUtil = mock(DictionaryDiffUtil::class.java)
        `when`(presenter.getCallback()).thenReturn(callback)
        assertEquals(presenter.getCallback(), callback)
        verify(presenter, times(2)).getCallback()
    }

    @Test
    fun getPagedList() {
        presenter.getPagedList()
        verify(presenter).getPagedList()
        verify(model).pagedList
    }

    @Test
    fun getOnItemClick() {
        val position = 0
        val item: InfoTranslation? = mock(InfoTranslation::class.java)
        `when`(presenter.onItemClick(position)).thenReturn(item)
        assertEquals(presenter.onItemClick(position), item)
        verify(presenter, times(2)).onItemClick(position)
    }
}
