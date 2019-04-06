package com.example.dictionary.views.adapters

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.dictionary.R
import com.example.dictionary.pojos.InfoTranslation
import kotlinx.android.synthetic.main.item_translation.view.*

class DictionaryAdapter(
    diffUtilCallback: DiffUtil.ItemCallback<InfoTranslation>,
    private val callback: (Int) -> Unit
) : PagedListAdapter<InfoTranslation, DictionaryAdapter.ViewHolder>(diffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_translation, parent, false)
        view.btn_save.visibility = View.GONE
        view.btn_delete.visibility = View.GONE
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val info = getItem(position)
        holder.tvLangFrom.text = info?.langFrom
        holder.tvLangTo.text = info?.langTo
        holder.tvValueFrom.text = info?.valueFrom
        holder.tvValueTo.text = info?.valueTo
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvLangFrom: TextView = view.tv_lang_from
        val tvLangTo: TextView = view.tv_lang_to
        val tvValueFrom: TextView = view.tv_value_from
        val tvValueTo: TextView = view.tv_value_to

        init {
            view.setOnClickListener { callback.invoke(adapterPosition) }
        }
    }
}
