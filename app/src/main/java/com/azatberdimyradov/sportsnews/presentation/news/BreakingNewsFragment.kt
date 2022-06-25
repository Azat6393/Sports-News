package com.azatberdimyradov.sportsnews.presentation.news

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.berdimyradov.sportsnews.R
import com.azatberdimyradov.sportsnews.Resource
import com.berdimyradov.sportsnews.databinding.FragmentBreakingNewsBinding
import com.azatberdimyradov.sportsnews.presentation.news.adapters.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BreakingNewsFragment : Fragment(R.layout.fragment_breaking_news) {

    private val viewModel: NewsViewModel by viewModels()
    private lateinit var binding: FragmentBreakingNewsBinding
    lateinit var newsAdapter: NewsAdapter

    val TAG = "BreakingNewsFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBreakingNewsBinding.bind(view)
        setUpRecyclerView()

        binding.apply {
            leftButton.setOnClickListener {
                viewModel.pageNumber--
                viewModel.getNewsFromDatabase()
                updateNavigateButton()
            }
            rightButton.setOnClickListener {
                viewModel.pageNumber++
                println(viewModel.pageNumber)
                viewModel.getNewsFromDatabase()
                updateNavigateButton()
            }
        }

        newsAdapter.setOnItemClickListener {
            val action = BreakingNewsFragmentDirections.actionBreakingNewsFragmentToArticleFragment(it.url)
            findNavController().navigate(action)
        }
        observe()
    }

    private fun updateNavigateButton() {
        binding.apply {
            leftButton.isVisible = viewModel.pageNumber > 1
            rightButton.isVisible = true
        }
    }

    private fun observe() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.newsFlow.collect { event ->
                    when(event){
                        is Resource.Success -> {
                            hideProgressBar()
                            event.data?.let {
                                newsAdapter.submitList(it.articles)
                            }
                        }
                        is Resource.Empty -> {}
                        is Resource.Error -> {
                            hideProgressBar()
                        }
                        is Resource.Loading -> {
                            showProgressBar()
                        }
                    }
                }
            }
        }
    }

    private fun setUpRecyclerView() {
        newsAdapter = NewsAdapter()
        binding.rvBreakingNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
        }
    }

    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility = View.INVISIBLE
        updateNavigateButton()
    }

    private fun showProgressBar() {
        binding.paginationProgressBar.visibility = View.VISIBLE
        binding.leftButton.visibility = View.GONE
        binding.rightButton.visibility = View.GONE
    }
}