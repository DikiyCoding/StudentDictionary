package com.example.dictionary.views.fragments.nav_fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dictionary.R
import com.example.dictionary.navigation.Screens
import com.example.dictionary.utils.App
import kotlinx.android.synthetic.main.fragment_page.view.*

class SeventhFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_page, null)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.tv_page.text = getString(R.string.tv_seventh_page)
        view.btn_page.text = getString(R.string.tv_to_translation)
        view.btn_page.setOnClickListener {
            App.appComponent.getRouter().navigateTo(Screens.TranslationScreen)
        }
    }
}
