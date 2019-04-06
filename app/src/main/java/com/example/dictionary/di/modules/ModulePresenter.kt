package com.example.dictionary.di.modules

import com.example.dictionary.models.DictionaryModel
import com.example.dictionary.models.MenuModel
import com.example.dictionary.models.TranslationModel
import com.example.dictionary.models.WordDetailModel
import com.example.dictionary.presenters.DictionaryPresenter
import com.example.dictionary.presenters.MenuPresenter
import com.example.dictionary.presenters.TranslationPresenter
import com.example.dictionary.presenters.WordDetailPresenter
import dagger.Module
import dagger.Provides

@Module
class ModulePresenter {

    @Provides
    fun provideDictionaryPresenter(model: DictionaryModel)
            : DictionaryPresenter = DictionaryPresenter(model)

    @Provides
    fun provideMenuPresenter(model: MenuModel)
            : MenuPresenter = MenuPresenter(model)

    @Provides
    fun provideTranslationPresenter(model: TranslationModel)
            : TranslationPresenter = TranslationPresenter(model)

    @Provides
    fun provideWordDetailPresenter(model: WordDetailModel)
            : WordDetailPresenter = WordDetailPresenter(model)
}
