package com.example.dictionary.views.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckedTextView
import android.widget.TextView

import com.example.dictionary.pojos.InfoLanguage

class SpinnerAdapter(
    context: Context,
    resource: Int,
    private val list: List<InfoLanguage>
) : ArrayAdapter<InfoLanguage>(context, resource, list) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getView(position, convertView, parent)
        (view as TextView).text = list[position].name
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getDropDownView(position, convertView, parent)
        (view as CheckedTextView).text = list[position].name
        return view
    }
}
