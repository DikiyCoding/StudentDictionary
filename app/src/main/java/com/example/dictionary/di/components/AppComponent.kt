package com.example.dictionary.di.components

import android.content.Context
import android.content.SharedPreferences
import com.example.dictionary.di.modules.*
import com.example.dictionary.presenters.*
import dagger.Component
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ModuleApp::class,
        ModuleModel::class,
        ModuleNavigation::class,
        ModuleNetwork::class,
        ModulePagedList::class,
        ModulePresenter::class,
        ModuleRepository::class,
        ModuleService::class
    ]
)
interface AppComponent {
    /*fun inject(dictionaryActivity: DictionaryActivity)
    fun inject(menuActivity: MenuActivity)
    fun inject(translationActivity: TranslationActivity)
    fun inject(wordDetailActivity: WordDetailActivity)*/

    fun getDictionaryPresenter(): DictionaryPresenter
    fun getMenuPresenter(): MenuPresenter
    fun getTranslationPresenter(): TranslationPresenter
    fun getWordDetailPresenter(): WordDetailPresenter
    fun getContainerPresenter(): ContainerPresenter

    fun getNavigatorHolder(): NavigatorHolder
    fun getSharedPreferences(): SharedPreferences
    fun getRouter(): Router
    fun getContext(): Context

    /*fun inject(dictionaryModel: DictionaryModel)
    fun inject(menuModel: MenuModel)
    fun inject(translationModel: TranslationModel)
    fun inject(wordDetailModel: WordDetailModel)
    fun inject(paginationDialog: PaginationDialog)*/
}
