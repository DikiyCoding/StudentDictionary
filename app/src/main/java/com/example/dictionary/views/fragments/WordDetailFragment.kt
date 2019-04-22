package com.example.dictionary.views.fragments

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.dictionary.R
import com.example.dictionary.pojos.InfoTranslation
import com.example.dictionary.presenters.WordDetailPresenter
import com.example.dictionary.utils.App
import com.example.dictionary.utils.Constants
import com.example.dictionary.views.activities.ContainerActivity
import com.example.dictionary.views.interfaces.ViewWordDetail
import kotlinx.android.synthetic.main.fragment_word_detail.view.*

class WordDetailFragment : MvpAppCompatFragment(), ViewWordDetail {

    @InjectPresenter
    lateinit var presenter: WordDetailPresenter

    @ProvidePresenter
    fun provideWordDetailPresenter()
            : WordDetailPresenter = App.appComponent.getWordDetailPresenter()

    private lateinit var item: InfoTranslation
    private lateinit var currentView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_word_detail, null)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentView = view
        init(view)
    }

    override fun showErrorMessage(message: String) {
        val toast: Toast =
            Toast.makeText(App.appComponent.getContext(), message, Toast.LENGTH_LONG)
        toast.setGravity(Gravity.CENTER, 0, Constants.toastXOffset)
        toast.show()
    }

    override fun updateValues(item: InfoTranslation?) {
        currentView.et_trans_from.setText(item?.valueFrom, TextView.BufferType.EDITABLE)
        currentView.et_trans_to.setText(item?.valueTo, TextView.BufferType.EDITABLE)
    }

    private fun init(view: View) {
        setValues(view)
        setListeners(view)
    }

    private fun setValues(view: View) {
        item = (activity as ContainerActivity).getItemDictionary()
        presenter.setCurrentItem(item)
        view.tv_lang_from.text = item.langFrom
        view.tv_lang_to.text = item.langTo
        updateValues(item)
    }

    private fun setListeners(view: View) {
        view.btn_return.setOnClickListener {
            action(it)
        }
        view.btn_save.setOnClickListener {
            action(it)
        }
    }

    /*private fun setUnfocusable() {
        et_trans_from.focusable = EditText.NOT_FOCUSABLE
        et_trans_to.focusable = EditText.NOT_FOCUSABLE
    }*/

    //    @TargetApi(Build.VERSION_CODES.O)
    private fun action(view: View) {
        when (view) {
            /*btn_change_trans_from -> {
                et_trans_to.focusable = EditText.NOT_FOCUSABLE
                et_trans_from.focusable = EditText.FOCUSABLE
            }
            btn_change_trans_to -> {
                et_trans_to.focusable = EditText.FOCUSABLE
                et_trans_from.focusable = EditText.NOT_FOCUSABLE
            }*/
            view.btn_return -> presenter.updateValues()
            view.btn_save -> presenter.handleValues(
                view.et_trans_from.text.toString(),
                view.et_trans_to.text.toString()
            )
        }
    }
}
