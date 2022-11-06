package uz.gita.news_app_io.presentation.screens.main.pages.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import uz.gita.news_app_io.R
import uz.gita.news_app_io.data.enums.Category
import uz.gita.news_app_io.databinding.PageHomeBinding

// Created by Jamshid Isoqov an 10/30/2022
class HomePage : Fragment(R.layout.page_home) {

    private val categoryList = Category.values()

    private val viewBinding: PageHomeBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewBinding.apply {
              savedMenu.setOnClickListener { findNavController().navigate(HomePageDirections.actionHomePageToSavedPage())
              }
            pagerHome.adapter = PagerAdapter(requireActivity())

            TabLayoutMediator(tabLayoutCategories, pagerHome) { tab, pos ->
                tab.text = categoryList[pos].name
            }.attach()

        }
    }


}