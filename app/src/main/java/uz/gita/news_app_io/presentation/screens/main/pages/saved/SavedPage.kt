package uz.gita.news_app_io.presentation.screens.main.pages.saved

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
import uz.gita.news_app_io.databinding.PageSavedBinding
import uz.gita.news_app_io.presentation.screens.main.pages.home.HomePageDirections

import uz.gita.news_app_io.presentation.screens.main.pages.home.pages.NewsAdapter
import uz.gita.news_app_io.presentation.viewmodels.SavedViewModel
import uz.gita.news_app_io.presentation.viewmodels.impl.SavedViewModelImpl

// Created by Jamshid Isoqov an 10/30/2022
@AndroidEntryPoint
class SavedPage : Fragment(R.layout.page_saved) {

    private val adapter: NewsAdapter by lazy(LazyThreadSafetyMode.NONE) {
        NewsAdapter()
    }

    private val viewModel: SavedViewModel by viewModels<SavedViewModelImpl>()

    private val viewBinding: PageSavedBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewBinding.listSavedNews.adapter = adapter
        viewBinding.listSavedNews.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )

        adapter.setItemClickListener {
            findNavController().navigate(SavedPageDirections.actionSavedPageToReadNewsScreen(it))
        }

        viewModel.savedNewsFlow.onEach {
            adapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }


}