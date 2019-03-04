package com.example.dictionary.presenters.presenters

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.AdapterView
import com.example.dictionary.contracts.ContractTranslation
import com.example.dictionary.models.network.apis.yandex.LangsAvailable
import com.example.dictionary.models.network.apis.yandex.Translation
import com.example.dictionary.models.network.models.TranslationModel
import com.example.dictionary.presenters.adapters.SpinnerAdapter
import com.example.dictionary.presenters.pojos.InfoLanguage
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import java.lang.reflect.Field
import java.util.*
import kotlin.collections.ArrayList
import kotlin.reflect.KVisibility

class TranslationPresenter(private var view: ContractTranslation.View?) : ContractTranslation.Presenter {

    private val TAG = "Logs"
    private val model: ContractTranslation.Model
    private val langs: MutableList<InfoLanguage>
    private val langsSupported: MutableList<InfoLanguage>
    private val langsSearchable: MutableMap<String, InfoLanguage>
    private val singleLangs: SingleObserver<LangsAvailable>
    private val singleTrans: SingleObserver<Translation>
    private val adapterFrom: SpinnerAdapter
    private val adapterTo: SpinnerAdapter

    init {

        /**
         * Инициализируем поля
         */

        langs = ArrayList()
        model = TranslationModel()
        langsSearchable = HashMap()
        langsSupported = ArrayList()
        adapterFrom = SpinnerAdapter((view as Context), android.R.layout.simple_spinner_item, langs)
        adapterTo = SpinnerAdapter((view as Context), android.R.layout.simple_spinner_item, langsSupported)
        adapterFrom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapterTo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        /**
         * Observers
         */

        singleLangs = object : SingleObserver<LangsAvailable> {

            override fun onSubscribe(disposable: Disposable) {
                Log.d(TAG, "Подписались :)")
            }

            override fun onSuccess(langsAvailable: LangsAvailable) {
                Log.d(TAG, "Успешно сходили в инет и дёрнули инфу по доступным языкам :)")
                initListLanguage(langsAvailable)
                adapterFrom.notifyDataSetChanged()
            }

            override fun onError(exception: Throwable) {
                Log.e(TAG, "Не получилось сходить в инет :(")
                Log.e(TAG, "Вот почему: " + exception.message)
            }
        }

        singleTrans = object : SingleObserver<Translation> {

            override fun onSubscribe(disposable: Disposable) {
                Log.d(TAG, "Подписались :)")
            }

            override fun onSuccess(translation: Translation) {
                Log.d(TAG, "Успешно сходили в инет и перевели с одного языка на другой :)")
                view!!.setTranslation(translation.text!![0])
            }

            override fun onError(exception: Throwable) {
                Log.e(TAG, "Не получилось сходить в инет :(")
                Log.e(TAG, "Вот почему: " + exception.message)
            }
        }
    }

    /**
     * Предотвращаем утечку памяти
     */

    override fun detachView() {
        this.view = null
    }

    /**
     * Adapters
     */

    override fun getAdapter(key: String): SpinnerAdapter? {
        return when (key) {
            "adapterFrom" -> adapterFrom
            "adapterTo" -> adapterTo
            else -> null
        }
    }

    override fun viewIsReady() {
        getLanguages()
    }

    /**
     * Основные методы
     */

    override fun translate(word: String, from: String, to: String) {
        model.getTranslation(singleTrans, word, "$from-$to")
    }

    private fun getLanguages() {
        model.getLanguages(singleLangs)
    }

    /**
     * Получаем доступные языки, а после добавляем языки, поддерживаемые для перевода
     */

    private fun initListLanguage(langsAvailable: LangsAvailable) {
        langsAvailable.langs!!::class.members.forEach {
            if (it.visibility == KVisibility.PUBLIC) {
                val info = InfoLanguage(it.name, it.name, ArrayList())
                langsSearchable[it.name] = info
                langs.add(info)
            }
        }
        initListSupportedLanguages(langsAvailable)
    }

    private fun initListSupportedLanguages(langsAvailable: LangsAvailable) {
        for (dir in langsAvailable.dirs!!)
            langsSearchable[dir.substring(0, 2)]!!
                    .supportedLanguages
                    .add(langsSearchable[dir.substring(3)]!!)
    }

    /**
     * Listeners
     */

    override fun itemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        langsSupported.clear()
        langsSupported.addAll((parent.getItemAtPosition(position) as InfoLanguage).supportedLanguages)
        adapterTo.notifyDataSetChanged()
    }
}
