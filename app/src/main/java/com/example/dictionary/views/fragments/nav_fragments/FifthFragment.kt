package com.example.dictionary.views.fragments.nav_fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.dictionary.R
import kotlinx.android.synthetic.main.fragment_page.*
import kotlinx.android.synthetic.main.fragment_page.view.*

class FifthFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_page, null)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController: NavController = Navigation.findNavController(view)
        view.tv_page.text = getString(R.string.tv_fifth_page)
        view.btn_page.text = getString(R.string.tv_to_sixth)
        view.et_view.visibility = View.VISIBLE
        val action: FifthFragmentDirections.FifthToSixth =
            FifthFragmentDirections.fifthToSixth()
        view.btn_page.setOnClickListener {
            val input: String = et_page.text.toString()
            if (input.isNotEmpty()) action.name = input
            navController.navigate(action)
        }
    }
}
