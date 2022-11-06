package uz.gita.news_app_io.data.prefs

import android.content.Context
import android.content.SharedPreferences
import uz.gita.news_app_io.utils.SharedPreference
import javax.inject.Inject

// Created by Jamshid Isoqov an 10/30/2022
class MySharedPref @Inject constructor(context: Context, sharedPreferences: SharedPreferences) :
    SharedPreference(context, sharedPreferences) {


    var name: String by Strings("")

    var image: String by Strings("")



}