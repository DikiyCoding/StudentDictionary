package com.example.dictionary.presenters.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.dictionary.R
import com.example.dictionary.presenters.pojos.InfoLanguage
import com.example.dictionary.presenters.pojos.InfoTranslation
import com.example.dictionary.presenters.utils.ItemClickCallback
import kotlinx.android.synthetic.main.item_translation.view.*

class TranslationAdapter(
    private val infoTrans: MutableList<InfoTranslation>,
    private val infoLangs: MutableMap<String, InfoLanguage>,
    private val callback: ItemClickCallback
) : RecyclerView.Adapter<TranslationAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_translation, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val info = this.infoTrans[position]
        /*holder.tvLangFrom.text = infoLangs[info.langFrom]!!.name
        holder.tvLangTo.text = infoLangs[info.langTo]!!.name*/
        holder.tvLangFrom.text = info.langFrom
        holder.tvLangTo.text = info.langTo
        holder.tvValueFrom.text = info.valueFrom
        holder.tvValueTo.text = info.valueTo
    }

    override fun getItemCount(): Int =
        infoTrans.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvLangFrom: TextView = view.tv_lang_from
        val tvLangTo: TextView = view.tv_lang_to
        val tvValueFrom: TextView = view.tv_value_from
        val tvValueTo: TextView = view.tv_value_to

        init {
            view.btn_save.setOnClickListener { callback.onItemClick("save", adapterPosition) }
            view.btn_delete.setOnClickListener { callback.onItemClick("delete", adapterPosition) }
        }
    }
}
