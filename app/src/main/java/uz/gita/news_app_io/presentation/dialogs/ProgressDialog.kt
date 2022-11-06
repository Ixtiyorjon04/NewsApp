package uz.gita.news_app_io.presentation.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import uz.gita.news_app_io.databinding.DialogProgressBinding
import uz.gita.news_app_io.utils.config

// Created by Jamshid Isoqov an 10/12/2022
class ProgressDialog(ctx: Context) : Dialog(ctx) {

    private lateinit var binging: DialogProgressBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binging = DialogProgressBinding.inflate(layoutInflater)
        config(binging)
        setCancelable(false)
    }

}