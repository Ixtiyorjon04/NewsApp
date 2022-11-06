package uz.gita.news_app_io.presentation.screens.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.gita.news_app_io.presentation.screens.main.pages.home.HomePage
import uz.gita.news_app_io.presentation.screens.main.pages.profile.ProfilePage
import uz.gita.news_app_io.presentation.screens.main.pages.saved.SavedPage

// Created by Jamshid Isoqov an 10/30/2022
class MainPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> HomePage()
        1 -> SavedPage()
        else -> ProfilePage()
    }
}