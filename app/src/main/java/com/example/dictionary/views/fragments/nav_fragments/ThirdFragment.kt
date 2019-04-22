package com.example.dictionary.views.fragments.nav_fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.dictionary.R
import kotlinx.android.synthetic.main.fragment_page.view.*

class ThirdFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_page, null)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController: NavController = Navigation.findNavController(view)
        view.tv_page.text = getString(R.string.tv_third_page)
        view.btn_page.text = getString(R.string.tv_to_seventh)
        view.btn_page.setOnClickListener { navController.navigate(R.id.third_to_seventh) }
    }
}
