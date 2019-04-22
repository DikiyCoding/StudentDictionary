package com.example.dictionary.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.dictionary.R
import com.example.dictionary.pojos.InfoLanguage
import com.example.dictionary.presenters.TranslationPresenter
import com.example.dictionary.utils.App
import com.example.dictionary.views.adapters.SpinnerAdapter
import com.example.dictionary.views.adapters.TranslationAdapter
import com.example.dictionary.views.interfaces.ViewTranslation
import kotlinx.android.synthetic.main.fragment_translation.view.*

class TranslationFragment : MvpAppCompatFragment(), ViewTranslation, AdapterView.OnItemSelectedListener {

    @InjectPresenter
    lateinit var presenter: TranslationPresenter

    @ProvidePresenter
    fun provideTranslationPresenter()
            : TranslationPresenter = App.appComponent.getTranslationPresenter()

    private lateinit var adapterCache: TranslationAdapter
    private lateinit var adapterFrom: SpinnerAdapter
    private lateinit var adapterTo: SpinnerAdapter

    private lateinit var currentView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_translation, null)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentView = view
        init(view)
    }

    override fun onNothingSelected(parent: AdapterView<*>) {}

    override fun setTranslation(translation: String) =
        currentView.et_trans_to.setText(translation)

    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) =
        presenter.onItemSelected(parent, view, position, id)

    override fun setLangsFrom() =
        adapterFrom.notifyDataSetChanged()

    override fun setLangsTo() =
        adapterTo.notifyDataSetChanged()

    override fun setCache() =
        adapterCache.notifyDataSetChanged()

    private fun init(view: View) {
        createAdapters()
        assignAdapters(view)
        setTranslateListener(view)
    }

    private fun createAdapters() {
        createAdapterCache()
        createAdapterFrom()
        createAdapterTo()
    }

    private fun createAdapterFrom() {
        adapterFrom = SpinnerAdapter(
            App.appComponent.getContext(),
            android.R.layout.simple_spinner_item,
            presenter.getListLangs()
        )
        adapterFrom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    }

    private fun createAdapterTo() {
        adapterTo = SpinnerAdapter(
            App.appComponent.getContext(),
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

    private fun assignAdapters(view: View) {
        view.spin_transl_from.adapter = adapterFrom
        view.spin_transl_from.onItemSelectedListener = this
        view.spin_transl_to.adapter = adapterTo
        view.list_translations.adapter = adapterCache
    }

    private fun setTranslateListener(view: View) =
        view.btn_trans.setOnClickListener {
            presenter.translate(
                view.et_trans_from.text.toString(),
                view.spin_transl_from.selectedItem as InfoLanguage,
                view.spin_transl_to.selectedItem as InfoLanguage
            )
        }
}
