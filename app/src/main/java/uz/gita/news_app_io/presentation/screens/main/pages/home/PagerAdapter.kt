package uz.gita.news_app_io.presentation.screens.main.pages.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.gita.news_app_io.data.enums.Category
import uz.gita.news_app_io.presentation.screens.main.pages.home.pages.BasePage

// Created by Jamshid Isoqov an 10/30/2022
class PagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    private val categoryList = Category.values()
    override fun getItemCount(): Int = categoryList.size
    override fun createFragment(position: Int): Fragment {
        val page = BasePage()
        page.arguments = Bundle().apply { putString("category", categoryList[position].name) }
        return page
    }

}