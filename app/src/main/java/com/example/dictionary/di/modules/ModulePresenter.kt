package com.example.dictionary.di.modules

import com.example.dictionary.models.*
import com.example.dictionary.presenters.*
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Router

@Module
class ModulePresenter {

    @Provides
    fun provideDictionaryPresenter(model: DictionaryModel, router: Router)
            : DictionaryPresenter = DictionaryPresenter(model, router)

    @Provides
    fun provideMenuPresenter(model: MenuModel, router: Router)
            : MenuPresenter = MenuPresenter(model, router)

    @Provides
    fun provideTranslationPresenter(model: TranslationModel, router: Router)
            : TranslationPresenter = TranslationPresenter(model, router)

    @Provides
    fun provideWordDetailPresenter(model: WordDetailModel, router: Router)
            : WordDetailPresenter = WordDetailPresenter(model, router)

    @Provides
    fun provideContainerPresenter(model: ContainerModel, router: Router)
            : ContainerPresenter = ContainerPresenter(model, router)
}
