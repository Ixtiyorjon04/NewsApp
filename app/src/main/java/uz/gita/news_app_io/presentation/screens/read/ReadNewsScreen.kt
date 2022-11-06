package uz.gita.news_app_io.presentation.screens.read

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.news_app_io.R
import uz.gita.news_app_io.databinding.ScreenReadBinding
import uz.gita.news_app_io.presentation.viewmodels.ReadViewModel
import uz.gita.news_app_io.presentation.viewmodels.impl.ReadViewModelImpl
import uz.gita.news_app_io.utils.hideProgress
import uz.gita.news_app_io.utils.showProgress

// Created by Jamshid Isoqov an 10/30/2022
@AndroidEntryPoint
class ReadNewsScreen : Fragment(R.layout.screen_read) {

    private val viewBinding: ScreenReadBinding by viewBinding()

    private val args: ReadNewsScreenArgs by navArgs()

    private val viewModel: ReadViewModel by viewModels<ReadViewModelImpl>()

    private lateinit var tts: TextToSpeech

    private var isSelected = false

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.imageBack.setOnClickListener {
            findNavController().navigateUp()
        }

        isSelected = args.news.saved == 1

        loadSaved()

        viewBinding.imageSaved.setOnClickListener {
            isSelected = !isSelected
            viewModel.updateNews(args.news.copy(saved = if (isSelected) 1 else 0))
            loadSaved()
        }
        showProgress()

        viewBinding.webViewNews.apply {
            settings.apply {
                domStorageEnabled = true
                loadsImagesAutomatically = true
                mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                javaScriptEnabled = true
            }
            webViewClient = object : WebViewClient() {

                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    hideProgress()
                }
            }
            webChromeClient = WebChromeClient()
        }



        viewBinding.webViewNews.loadUrl(args.news.newsUrl)

        //text to speech
        tts = TextToSpeech(requireContext()) {

        }
    }

    private fun loadSaved() {
        if (!isSelected) {
            viewBinding.imageSaved.setImageResource(R.drawable.ic_bookmark)
        } else {
            viewBinding.imageSaved.setImageResource(R.drawable.ic_selected_bookmark)
        }
    }

}