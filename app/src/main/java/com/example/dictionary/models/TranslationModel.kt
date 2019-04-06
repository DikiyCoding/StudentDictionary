package com.example.dictionary.models

import android.util.Log
import android.view.View
import android.widget.AdapterView
import com.example.dictionary.data.TranslationData
import com.example.dictionary.network.apis.yandex.LangsAvailable
import com.example.dictionary.network.apis.yandex.Translation
import com.example.dictionary.pojos.InfoLanguage
import com.example.dictionary.pojos.InfoTranslation
import com.example.dictionary.repository.CacheRepository
import com.example.dictionary.repository.TranslationRepository
import com.example.dictionary.utils.CallbackTranslation
import com.example.dictionary.utils.Constants
import io.reactivex.rxkotlin.subscribeBy
import kotlin.reflect.KVisibility
import kotlin.reflect.full.memberProperties

class TranslationModel(
    private val repositoryCache: CacheRepository,
    private val dataTranslation: TranslationData,
    private val repositoryTranslation: TranslationRepository
) {

    /*@Inject
    lateinit var repositoryCache: CacheRepository

    @Inject
    lateinit var dataTranslation: TranslationData

    @Inject
    lateinit var repositoryTranslation: TranslationRepository

    @Inject
    lateinit var callback: CallbackTranslation*/

    lateinit var callback: CallbackTranslation

    private lateinit var from: InfoLanguage
    private lateinit var to: InfoLanguage
    private lateinit var textFrom: String

    /**
     * Получаем ссылки на списки с данными
     */

    fun getListLangs() = dataTranslation.langs

    fun getListLangsSup() = dataTranslation.langsSupported

    fun getListLangsSearch() = dataTranslation.langsSearchable

    fun getListTransl() = dataTranslation.translations

    /**
     * Получаем доступные языки, а после добавляем языки, поддерживаемые для перевода
     */

    private fun initListLanguage(langsAvailable: LangsAvailable) {
        langsAvailable.langs::class.memberProperties.forEach {
            if (it.visibility == KVisibility.PUBLIC) {
                val info = InfoLanguage(
                    it.name,
                    it.getter.call(langsAvailable.langs) as String,
                    ArrayList()
                )
                dataTranslation.langsSearchable[it.name] = info
                dataTranslation.langs.add(info)
            }
        }
        initListSupportedLanguages(langsAvailable)
    }

    private fun initListSupportedLanguages(langsAvailable: LangsAvailable) {
        for (dir in langsAvailable.dirs)
            dataTranslation.langsSearchable[dir.substring(Constants.supLangSubStr)]?.let {
                dataTranslation.langsSearchable[dir.substring(0, 2)]?.supportedLanguages?.add(it)
            }
    }

    fun getLanguages() {
        repositoryTranslation
            .getLanguages()
            .subscribeBy(onSuccess = {
                initListLanguage(it)
                callback.langsFromChanged()
            })
    }

    /**
     * Переводим с одного языка на другой
     */

    fun translate(word: String, from: InfoLanguage, to: InfoLanguage) {
        this.to = to
        this.from = from
        this.textFrom = word
        repositoryTranslation
            .getTranslation(word, "${from.langSign}-${to.langSign}")
            .subscribeBy(onSuccess = {
                callback.translationChanged(it.text?.get(0).toString())
                setCache(it)
            })
    }

    /**
     * Работаем с базой данных
     */

    private fun setCache(translation: Translation) {
        dataTranslation.translations.add(
            InfoTranslation(
                textFrom,
                translation.text?.get(0).toString(),
                from.langSign,
                to.langSign,
                false
            )
        )
        callback.cacheChanged()
    }

    fun getCache() {
        repositoryCache.getCache().subscribeBy(onSuccess = {
            dataTranslation
                .translations
                .addAll(it)
            callback.cacheChanged()
        })
    }

    fun updateCache(position: Int) {
        val transition: InfoTranslation =
            dataTranslation.translations[position]
        transition.isSaved = true
        repositoryCache
            .updateCache(transition)
            .subscribeBy(onComplete = { Log.i("Logs", "Элемент обновлён!!!") })
        dataTranslation
            .translations
            .removeAt(position)
        callback.cacheChanged()
    }

    fun deleteCache(position: Int) {
        dataTranslation
            .translations
            .remove(
                dataTranslation
                    .translations[position]
            )
        callback.cacheChanged()
    }

    /**
     * Листенеры
     */

    fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        dataTranslation
            .langsSupported
            .clear()
        dataTranslation
            .langsSupported
            .addAll((parent.getItemAtPosition(position) as InfoLanguage).supportedLanguages)
        callback.langsToChanged()
    }
}
