package com.example.dictionary.views.fragments.nav_fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dictionary.R
import kotlinx.android.synthetic.main.fragment_page.view.*

class PageFragment : Fragment() {

    private var pageNumber: Int = 0
    private lateinit var text: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageNumber = (arguments as Bundle).getInt(ARGUMENT_PAGE_NUMBER)
        /*text = resources.getStringArray(R.array.tvs_page)*/
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view: View = inflater.inflate(R.layout.fragment_page, null)
        when (pageNumber) {
            /*0, 1 -> view.tv_page.text = text[pageNumber]*/
            2 -> {
                view.tv_page.visibility = View.GONE
                view.et_page.visibility = View.VISIBLE
            }
        }
        return view
    }

    companion object {

        internal const val ARGUMENT_PAGE_NUMBER = "page_number"

        fun newInstance(page: Int): PageFragment {
            val pageFragment = PageFragment()
            val arguments = Bundle()
            arguments.putInt(ARGUMENT_PAGE_NUMBER, page)
            pageFragment.arguments = arguments
            return pageFragment
        }
    }
}
