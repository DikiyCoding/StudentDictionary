package com.example.dictionary.views.activities

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.dictionary.R
import com.example.dictionary.presenters.MenuPresenter
import com.example.dictionary.views.dialogs.PaginationDialog
import com.example.dictionary.views.interfaces.ViewMenu
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : MvpAppCompatActivity(), ViewMenu {

    @InjectPresenter
    lateinit var presenter: MenuPresenter

    private lateinit var dictionary: Intent
    private lateinit var translation: Intent
    private lateinit var pagination: DialogFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        init()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.settings, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean  {
        when(item.itemId) {
            R.id.settings -> pagination.show(supportFragmentManager, "pagination")
        }
        return super.onOptionsItemSelected(item)
    }

    private fun init() {
        dictionary = Intent(this, DictionaryActivity::class.java)
        translation = Intent(this, TranslationActivity::class.java)
        pagination = PaginationDialog()
    }

    fun action(view: View) {
        when (view) {
            btn_dictionary -> startActivity(dictionary)
            btn_translation -> startActivity(translation)
        }
    }
}
