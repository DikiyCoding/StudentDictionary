package com.example.dictionary.views.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import com.example.dictionary.R
import com.example.dictionary.contracts.ContractTranslation
import com.example.dictionary.presenters.pojos.InfoLanguage
import com.example.dictionary.presenters.presenters.TranslationPresenter
import kotlinx.android.synthetic.main.activity_translation.*

class TranslationActivity : AppCompatActivity(), ContractTranslation.View, AdapterView.OnItemSelectedListener {

    private var presenter: ContractTranslation.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_translation)
        init()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.detachView()
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        presenter?.itemSelected(parent, view, position, id)
    }

    override fun onNothingSelected(parent: AdapterView<*>) {}

    override fun setTranslation(translation: String) {
        et_trans_to.setText(translation)
    }

    private fun init() {
        presenter = TranslationPresenter(this)
        spin_transl_from.adapter = presenter?.getAdapter("adapterFrom")
        spin_transl_to.adapter = presenter?.getAdapter("adapterTo")
        spin_transl_from.onItemSelectedListener = this
        presenter?.viewIsReady()
    }

    public fun translate(view: View) {
        presenter?.translate(et_trans_from.text.toString(),
                (spin_transl_from.selectedItem as InfoLanguage).langSign,
                (spin_transl_to.selectedItem as InfoLanguage).langSign)
    }
}
