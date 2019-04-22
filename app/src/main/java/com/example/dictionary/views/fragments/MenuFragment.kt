package com.example.dictionary.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.dictionary.R
import com.example.dictionary.navigation.Screens
import com.example.dictionary.presenters.MenuPresenter
import com.example.dictionary.utils.App
import com.example.dictionary.views.interfaces.ViewMenu
import kotlinx.android.synthetic.main.fragment_menu.view.*

class MenuFragment : MvpAppCompatFragment(), ViewMenu {

    @InjectPresenter
    lateinit var presenter: MenuPresenter

    @ProvidePresenter
    fun provideMenuPresenter(): MenuPresenter = App.appComponent.getMenuPresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_menu, null)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
    }

    private fun init(view: View) {
        setClickListeners(view)
    }

    private fun setClickListeners(view: View) {
        view.btn_dictionary.setOnClickListener {
            presenter.onForwardCommandClick(Screens.DictionaryScreen)
        }
        view.btn_translation.setOnClickListener {
            presenter.onForwardCommandClick(Screens.TranslationScreen)
        }
        view.btn_navigation.setOnClickListener {
            presenter.onForwardCommandClick(Screens.HomeScreen)
        }
    }
}
