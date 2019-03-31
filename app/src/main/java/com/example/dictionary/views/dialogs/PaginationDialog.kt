package com.example.dictionary.views.dialogs

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import com.example.dictionary.R
import com.example.dictionary.utils.App
import com.example.dictionary.utils.Constants
import kotlinx.android.synthetic.main.dialog_pagination.*
import kotlinx.android.synthetic.main.dialog_pagination.view.*

class PaginationDialog : DialogFragment(), OnClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.dialog_pagination, null)
        view.np_pagination.minValue = Constants.pageSizeMinValue
        view.np_pagination.maxValue = Constants.pageSizeMaxValue
        view.btn_ok.setOnClickListener(this)
        view.btn_cancel.setOnClickListener(this)
        return view
    }

    override fun onResume() {
        super.onResume()
        dialog.window?.setLayout(
            resources.getDimensionPixelSize(R.dimen.dialog_pagination_width),
            resources.getDimensionPixelSize(R.dimen.dialog_pagination_height)
        )
    }

    override fun onClick(view: View) {
        when (view) {
            btn_ok ->
                App
                .instance
                .prefSettings
                .edit()
                .putInt("pagination", np_pagination.value)
                .apply()
        }
        dismiss()
    }
}
