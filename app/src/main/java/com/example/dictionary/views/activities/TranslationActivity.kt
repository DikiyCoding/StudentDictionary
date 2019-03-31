package com.example.dictionary.views.activities

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.dictionary.R
import com.example.dictionary.models.TranslationModel
import com.example.dictionary.pojos.InfoLanguage
import com.example.dictionary.presenters.TranslationPresenter
import com.example.dictionary.views.adapters.SpinnerAdapter
import com.example.dictionary.views.adapters.TranslationAdapter
import com.example.dictionary.views.interfaces.ViewTranslation
import kotlinx.android.synthetic.main.activity_translation.*

class TranslationActivity : MvpAppCompatActivity(), ViewTranslation, AdapterView.OnItemSelectedListener {

    @InjectPresenter
    lateinit var presenter: TranslationPresenter

    private lateinit var adapterCache: TranslationAdapter
    private lateinit var adapterFrom: SpinnerAdapter
    private lateinit var adapterTo: SpinnerAdapter

    @ProvidePresenter
    fun provideTranslationPresenter(): TranslationPresenter
            = TranslationPresenter(TranslationModel())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_translation)
        init()
    }

    override fun onNothingSelected(parent: AdapterView<*>) {}

    override fun setTranslation(translation: String) =
        et_trans_to.setText(translation)

    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) =
        presenter.onItemSelected(parent, view, position, id)

    override fun setLangsFrom() =
        adapterFrom.notifyDataSetChanged()

    override fun setLangsTo() =
        adapterTo.notifyDataSetChanged()

    override fun setCache() =
        adapterCache.notifyDataSetChanged()

    private fun init() {
        createAdapters()
        assignAdapters()
    }

    private fun createAdapters() {
        createAdapterCache()
        createAdapterFrom()
        createAdapterTo()
    }

    private fun createAdapterFrom() {
        adapterFrom = SpinnerAdapter(
            this,
            android.R.layout.simple_spinner_item,
            presenter.getListLangs()
        )
        adapterFrom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    }

    private fun createAdapterTo() {
        adapterTo = SpinnerAdapter(
            this,
            android.R.layout.simple_spinner_item,
            presenter.getListLangsSup()
        )
        adapterTo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    }

    private fun createAdapterCache() {
        adapterCache = TranslationAdapter(
            presenter.getListTransl(),
            presenter.getListLangsSearch()
        ) { action, position ->
            presenter.onItemClick(action, position)
        }
    }

    private fun assignAdapters() {
        spin_transl_from.adapter = adapterFrom
        spin_transl_from.onItemSelectedListener = this
        spin_transl_to.adapter = adapterTo
        list_translations.adapter = adapterCache
    }

    fun translate(view: View) {
        presenter.translate(
            et_trans_from.text.toString(),
            spin_transl_from.selectedItem as InfoLanguage,
            spin_transl_to.selectedItem as InfoLanguage
        )
    }
}
