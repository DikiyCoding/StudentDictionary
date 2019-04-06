package com.example.dictionary.views.activities

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.dictionary.R
import com.example.dictionary.pojos.InfoTranslation
import com.example.dictionary.presenters.WordDetailPresenter
import com.example.dictionary.utils.App
import com.example.dictionary.utils.Constants
import com.example.dictionary.views.interfaces.ViewWordDetail
import kotlinx.android.synthetic.main.activity_word_detail.*

class WordDetailActivity : MvpAppCompatActivity(), ViewWordDetail {

    @InjectPresenter
    lateinit var presenter: WordDetailPresenter

    @ProvidePresenter
    fun provideWordDetailPresenter()
            : WordDetailPresenter =  App.appComponent.getWordDetailPresenter()

    private lateinit var item: InfoTranslation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_detail)
        init()
    }

    override fun showErrorMessage(message: String) {
        val toast: Toast =
            Toast.makeText(this, message, Toast.LENGTH_LONG)
        toast.setGravity(Gravity.CENTER, 0, Constants.toastXOffset)
        toast.show()
    }

    override fun updateValues(item: InfoTranslation) {
        et_trans_from.setText(item.valueFrom, TextView.BufferType.EDITABLE)
        et_trans_to.setText(item.valueTo, TextView.BufferType.EDITABLE)
    }

    private fun init() {
        setValues()
    }

    private fun setValues() {
        item = intent.getParcelableExtra("item")
        presenter.setCurrentItem(item)
        tv_lang_from.text = item.langFrom
        tv_lang_to.text = item.langTo
        updateValues(item)
    }

    /*private fun setUnfocusable() {
        et_trans_from.focusable = EditText.NOT_FOCUSABLE
        et_trans_to.focusable = EditText.NOT_FOCUSABLE
    }*/

    @TargetApi(Build.VERSION_CODES.O)
    fun action(view: View) {
        when (view) {
            /*btn_change_trans_from -> {
                et_trans_to.focusable = EditText.NOT_FOCUSABLE
                et_trans_from.focusable = EditText.FOCUSABLE
            }
            btn_change_trans_to -> {
                et_trans_to.focusable = EditText.FOCUSABLE
                et_trans_from.focusable = EditText.NOT_FOCUSABLE
            }*/
            btn_return -> presenter.updateValues()
            btn_save -> presenter.handleValues(
                et_trans_from.text.toString(),
                et_trans_to.text.toString()
            )
        }
    }
}
