package com.example.dictionary.views.activities

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.Menu
import android.view.MenuItem
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.dictionary.R
import com.example.dictionary.pojos.InfoTranslation
import com.example.dictionary.presenters.ContainerPresenter
import com.example.dictionary.utils.App
import com.example.dictionary.views.dialogs.PaginationDialog
import com.example.dictionary.views.interfaces.ViewContainer
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class ContainerActivity : MvpAppCompatActivity(), ViewContainer {

    @InjectPresenter
    lateinit var presenter: ContainerPresenter

    @ProvidePresenter
    fun provideContainerPresenter()
            : ContainerPresenter = App.appComponent.getContainerPresenter()

    private lateinit var pagination: DialogFragment
    private lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        init()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.settings, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings -> pagination.show(supportFragmentManager, "pagination")
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        App.appComponent.getNavigatorHolder().setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.appComponent.getNavigatorHolder().removeNavigator()
    }

    override fun onBackPressed() =
        presenter.onBackCommandClick()

    private fun init() {
        pagination = PaginationDialog()
        navigator = SupportAppNavigator(this, R.id.container)
        presenter.showFirstScreen()
    }

    fun setItemDictionary(item: InfoTranslation) =
        presenter.setItemDictionary(item)

    fun getItemDictionary(): InfoTranslation =
        presenter.getItemDictionary()
}
