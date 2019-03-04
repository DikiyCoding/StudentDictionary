package com.example.dictionary.contracts

import android.widget.AdapterView

import com.example.dictionary.models.network.apis.yandex.LangsAvailable
import com.example.dictionary.models.network.apis.yandex.Translation
import com.example.dictionary.presenters.adapters.SpinnerAdapter

import io.reactivex.SingleObserver

interface ContractTranslation {

    interface Model {
        fun getLanguages(singleLangs: SingleObserver<LangsAvailable>)
        fun getTranslation(singleTrans: SingleObserver<Translation>, text: String, lang: String)
    }

    interface View {
        fun setTranslation(translation: String)
    }

    interface Presenter {
        fun viewIsReady()
        fun detachView()
        fun translate(word: String, from: String, to: String)
        fun itemSelected(parent: AdapterView<*>, view: android.view.View, position: Int, id: Long)
        fun getAdapter(key: String): SpinnerAdapter?
    }
}
