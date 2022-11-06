package uz.gita.news_app_io.presentation.screens.main.pages.home.pages

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.news_app_io.R
import uz.gita.news_app_io.databinding.PageMainBinding
import uz.gita.news_app_io.presentation.screens.main.pages.home.HomePageDirections

import uz.gita.news_app_io.presentation.viewmodels.NewsViewModel
import uz.gita.news_app_io.presentation.viewmodels.impl.NewsViewModelImpl
import uz.gita.news_app_io.utils.gone
import uz.gita.news_app_io.utils.showErrorDialog
import uz.gita.news_app_io.utils.showMessageDialog
import uz.gita.news_app_io.utils.visible

// Created by Jamshid Isoqov an 10/30/2022
@AndroidEntryPoint
class BasePage : Fragment(R.layout.page_main) {

    private val adapter: NewsAdapter by lazy(LazyThreadSafetyMode.NONE) {
        NewsAdapter()
    }

    private val viewModel: NewsViewModel by viewModels<NewsViewModelImpl>()

    private val viewBinding: PageMainBinding by viewBinding()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val args = arguments?.getString("category")

        viewBinding.listNews.apply {
            this.adapter = this@BasePage.adapter
            addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        }

        viewModel.errorSharedFlow.onEach {
            showErrorDialog(it) {}
        }.launchIn(lifecycleScope)

        viewModel.messageSharedFlow.onEach {
            showMessageDialog(it)
        }.launchIn(lifecycleScope)

        viewModel.loadingProgressBar.onEach {
            if (it) {
                viewBinding.shimmerLayout.showShimmer(true)
                viewBinding.shimmerLayout.visible()
            } else {
                viewBinding.shimmerLayout.stopShimmer()
                viewBinding.shimmerLayout.hideShimmer()
                viewBinding.shimmerLayout.gone()
            }
        }.launchIn(lifecycleScope)

        viewModel.newsSharedFlow.onEach {
            adapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        adapter.setItemClickListener {
            findNavController().navigate(HomePageDirections.actionHomePageToReadNewsScreen(it))
        }

        viewModel.getNewsByCategory(args!!)

    }


}