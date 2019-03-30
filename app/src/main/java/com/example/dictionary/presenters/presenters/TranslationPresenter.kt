package com.example.dictionary.presenters.presenters

import android.util.Log
import android.view.View
import android.widget.AdapterView
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.dictionary.models.models.CacheModel
import com.example.dictionary.models.models.TranslationModel
import com.example.dictionary.models.network.apis.yandex.LangsAvailable
import com.example.dictionary.models.network.apis.yandex.Translation
import com.example.dictionary.presenters.adapters.SpinnerAdapter
import com.example.dictionary.presenters.adapters.TranslationAdapter
import com.example.dictionary.presenters.pojos.InfoLanguage
import com.example.dictionary.presenters.pojos.InfoTranslation
import com.example.dictionary.presenters.utils.App
import com.example.dictionary.presenters.utils.Constants
import com.example.dictionary.presenters.utils.ItemClickCallback
import com.example.dictionary.presenters.utils.LimitedDataBaseArrayList
import com.example.dictionary.views.interfaces.ViewTranslation
import io.reactivex.rxkotlin.subscribeBy
import java.util.*
import kotlin.collections.ArrayList
import kotlin.reflect.KVisibility
import kotlin.reflect.full.memberProperties

@InjectViewState
class TranslationPresenter : MvpPresenter<ViewTranslation>(), ItemClickCallback, AdapterView.OnItemSelectedListener {

    private val modelCache: CacheModel
    private val modelTranslation: TranslationModel
    private val langs: MutableList<InfoLanguage>
    private val translations: MutableList<InfoTranslation>
    private val langsSupported: MutableList<InfoLanguage>
    private val langsSearchable: MutableMap<String, InfoLanguage>
    private val adapterCache: TranslationAdapter
    private val adapterFrom: SpinnerAdapter
    private val adapterTo: SpinnerAdapter

    private lateinit var from: InfoLanguage
    private lateinit var to: InfoLanguage
    private lateinit var textFrom: String

    init {

        /**
         * Инициализируем поля
         */

        langs = ArrayList()
        langsSearchable = HashMap()
        langsSupported = ArrayList()
        modelCache = App.instance.modelCache
        modelTranslation = App.instance.modelTranslation
        translations = LimitedDataBaseArrayList()
        adapterCache = TranslationAdapter(translations, langsSearchable, this)
        adapterFrom = SpinnerAdapter(App.appContext(), android.R.layout.simple_spinner_item, langs)
        adapterTo = SpinnerAdapter(App.appContext(), android.R.layout.simple_spinner_item, langsSupported)
        adapterFrom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapterTo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    }

    /**
     * Listeners
     */

    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        langsSupported.clear()
        langsSupported.addAll((parent.getItemAtPosition(position) as InfoLanguage).supportedLanguages)
        adapterTo.notifyDataSetChanged()
    }

    override fun onItemClick(action: String, position: Int) {
        when (action) {
            "save" -> {
                Log.d("Logs", "Нажали save на $position позиции")
                updateCache(position, translations[position])
            }
            "delete" -> {
                Log.d("Logs", "Нажали delete на $position позиции")
                deleteCache(translations[position])
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>) {}

    /**
     * Adapters
     */

    fun getAdapter(key: String): Any? {
        return when (key) {
            "adapterFrom" -> adapterFrom
            "adapterTo" -> adapterTo
            "adapterCache" -> adapterCache
            else -> null
        }
    }

    /**
     * Точка входа для Presenter
     */

    fun viewIsReady() {
        getLanguages()
        getCache()
    }

    /**
     * Основные методы
     */

    private fun getLanguages() {
        modelTranslation.getLanguages().subscribeBy(onSuccess = {
            initListLanguage(it)
            adapterFrom.notifyDataSetChanged()
        })
    }

    fun translate(word: String, from: InfoLanguage, to: InfoLanguage) {
        this.to = to
        this.from = from
        this.textFrom = word
        modelTranslation.getTranslation(word, "${from.langSign}-${to.langSign}").subscribeBy(onSuccess = {
            viewState.setTranslation(it.text?.get(0).toString())
            setCache(it)
        })
    }

    private fun setCache(translation: Translation) {
        translations.add(
            InfoTranslation(textFrom, translation.text?.get(0).toString(), from.langSign, to.langSign, false)
        )
        adapterCache.notifyDataSetChanged()
    }

    private fun getCache() {
        modelCache.getCache().subscribeBy(onSuccess = {
            translations.addAll(it)
            adapterCache.notifyDataSetChanged()
        })
    }

    private fun updateCache(position: Int, infoTranslation: InfoTranslation) {
        infoTranslation.isSaved = true
        modelCache.updateCache(infoTranslation).subscribeBy(onComplete = { Log.i("Logs", "Элемент обновлён!!!") })
        translations.removeAt(position)
        adapterCache.notifyDataSetChanged()
    }

    private fun deleteCache(infoTranslation: InfoTranslation) {
        translations.remove(infoTranslation)
        adapterCache.notifyDataSetChanged()
    }

    /**
     * Получаем доступные языки, а после добавляем языки, поддерживаемые для перевода
     */

    private fun initListLanguage(langsAvailable: LangsAvailable) {
        langsAvailable.langs::class.memberProperties.forEach {
            if (it.visibility == KVisibility.PUBLIC) {
                val info = InfoLanguage(it.name, it.getter.call(langsAvailable.langs) as String, ArrayList())
                langsSearchable[it.name] = info
                langs.add(info)
            }
        }
        initListSupportedLanguages(langsAvailable)
    }

    private fun initListSupportedLanguages(langsAvailable: LangsAvailable) {
        for (dir in langsAvailable.dirs)
            langsSearchable[dir.substring(Constants.supLangSubStr)]?.let {
                langsSearchable[dir.substring(0, 2)]?.supportedLanguages?.add(it)
            }
    }
}
