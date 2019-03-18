package com.example.dictionary.views.activities

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.SpinnerAdapter
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.dictionary.R
import com.example.dictionary.presenters.pojos.InfoLanguage
import com.example.dictionary.presenters.presenters.TranslationPresenter
import com.example.dictionary.views.interfaces.ViewTranslation
import kotlinx.android.synthetic.main.activity_translation.*

class TranslationActivity : MvpAppCompatActivity(), ViewTranslation {

    @InjectPresenter
    lateinit var presenter: TranslationPresenter

    @ProvidePresenter
    fun provideTranslationPresenter(): TranslationPresenter {
        return TranslationPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_translation)
        init()
    }

    override fun setTranslation(translation: String) {
        et_trans_to.setText(translation)
    }

    fun translate(view: View) {
        presenter.translate(
            et_trans_from.text.toString(),
            (spin_transl_from.selectedItem as InfoLanguage),
            (spin_transl_to.selectedItem as InfoLanguage)
        )
    }

    private fun init() {
        spin_transl_from.adapter = presenter.getAdapter("adapterFrom") as SpinnerAdapter?
        spin_transl_from.onItemSelectedListener = presenter
        spin_transl_to.adapter = presenter.getAdapter("adapterTo") as SpinnerAdapter?
        list_translations.adapter = presenter.getAdapter("adapterCache") as RecyclerView.Adapter<*>?
        presenter.viewIsReady()
    }
}
