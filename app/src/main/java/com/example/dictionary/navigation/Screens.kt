package com.example.dictionary.navigation

import android.support.v4.app.Fragment
import com.example.dictionary.views.fragments.DictionaryFragment
import com.example.dictionary.views.fragments.MenuFragment
import com.example.dictionary.views.fragments.TranslationFragment
import com.example.dictionary.views.fragments.WordDetailFragment
import com.example.dictionary.views.fragments.nav_fragments.HomeFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {

    object DictionaryScreen : SupportAppScreen() {
        override fun getFragment(): Fragment = DictionaryFragment()
    }

    object MenuScreen : SupportAppScreen() {
        override fun getFragment(): Fragment = MenuFragment()
    }

    object TranslationScreen : SupportAppScreen() {
        override fun getFragment(): Fragment = TranslationFragment()
    }

    object WordDetailScreen : SupportAppScreen() {
        override fun getFragment(): Fragment = WordDetailFragment()
    }

    object HomeScreen : SupportAppScreen() {
        override fun getFragment(): Fragment = HomeFragment()
    }
}
