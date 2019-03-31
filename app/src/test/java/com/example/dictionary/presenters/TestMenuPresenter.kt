package com.example.dictionary.presenters

import com.example.dictionary.views.interfaces.ViewMenu
import com.example.dictionary.views.interfaces.`ViewMenu$$State`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TestMenuPresenter {

    @Mock
    lateinit var mockViewState: `ViewMenu$$State`

    @InjectMocks
    @Spy
    lateinit var presenter: MenuPresenter

    @Before
    fun setUp() {
        presenter.attachView(mockViewState)
    }

    @Test
    fun onFirstViewAttach() {
        val mockView = Mockito.mock(ViewMenu::class.java)
        presenter.attachView(mockView)
        Mockito.verify(presenter, Mockito.atLeastOnce()).attachView(mockView)
    }
}
