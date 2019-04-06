package com.example.dictionary.di.components

/*import com.example.dictionary.views.activities.DictionaryActivity
import com.example.dictionary.views.activities.MenuActivity
import com.example.dictionary.views.activities.TranslationActivity
import com.example.dictionary.views.activities.WordDetailActivity*/
import android.content.SharedPreferences
import com.example.dictionary.di.modules.*
import com.example.dictionary.presenters.DictionaryPresenter
import com.example.dictionary.presenters.MenuPresenter
import com.example.dictionary.presenters.TranslationPresenter
import com.example.dictionary.presenters.WordDetailPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ModuleApp::class,
        ModuleModel::class,
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

    fun getSharedPreferences(): SharedPreferences

    /*fun inject(dictionaryModel: DictionaryModel)
    fun inject(menuModel: MenuModel)
    fun inject(translationModel: TranslationModel)
    fun inject(wordDetailModel: WordDetailModel)
    fun inject(paginationDialog: PaginationDialog)*/
}
