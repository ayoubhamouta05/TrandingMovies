package com.youppix.trandingmovies.presentation.fragments.trending

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.youppix.trandingmovies.R
import com.youppix.trandingmovies.databinding.FragmentTrendingMoviesBinding
import com.youppix.trandingmovies.presentation.adapters.TrendingMoviesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class TrendingMoviesFragment : Fragment() {

    private lateinit var binding: FragmentTrendingMoviesBinding
    private val viewModel by viewModels<TrendingMoviesViewModel>()


    private val trendingMoviesAdapter by lazy {
        TrendingMoviesAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTrendingMoviesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRv()

        if (viewModel.trendingMovies.value == null) {
            viewModel.getMovies(page = 1)
        }


        viewModel.loading.observe(viewLifecycleOwner) {
            if (it) {
                loading()
            }
        }

        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            if (!errorMessage.isNullOrEmpty())
                onError(errorMessage)
        }

        binding.errorContainer.setOnClickListener {
            onRefresh()
        }


        viewModel.trendingMovies.observe(viewLifecycleOwner) {

            trendingMoviesAdapter.differ.submitList(it.results) {
                showRv()
                setupPagination(it.page, it.total_pages)
            }

        }

        trendingMoviesAdapter.setOnShowDetailsClickListener {
            val movieId = Bundle().apply {
                putInt("movieId" , it.id)
            }
            findNavController().navigate(R.id.action_TrendingMoviesFragment_to_DetailsMovieFragment , movieId)
        }

        binding.nextPageBtn.setOnClickListener {

            viewModel.toNextPage()
            loading()
        }

        binding.previousPageBtn.setOnClickListener {

            viewModel.toPreviousPage()
            loading()
        }


    }

    private fun setupPagination(currentPage: Int, totalPages: Int) {
        binding.apply {
            if (currentPage <= 1) {
                previousPageBtn.visibility = View.GONE
            } else {
                previousPageBtn.visibility = View.VISIBLE
            }

            if (currentPage == totalPages) {
                nextPageBtn.visibility = View.GONE
            } else {
                nextPageBtn.visibility = View.VISIBLE
            }

            pageInfo.text = "$currentPage of $totalPages"
        }

    }

    private fun setupRv() {
        binding.trendingMoviesRv.apply {
            adapter = trendingMoviesAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun showRv() {
        binding.shimmerLayout.stopShimmer()
        binding.shimmerLayout.visibility = View.GONE
        binding.trendingMoviesRv.visibility = View.VISIBLE
        binding.pageInfoContainer.visibility = View.VISIBLE
        binding.errorContainer.visibility = View.GONE
    }

    private fun loading() {
        binding.shimmerLayout.startShimmer()
        binding.shimmerLayout.visibility = View.VISIBLE
        binding.trendingMoviesRv.visibility = View.GONE
        binding.pageInfoContainer.visibility = View.GONE
        binding.errorContainer.visibility = View.GONE

    }

    private fun onError(errorMessage: String) {
        lifecycleScope.launch {
            delay(300)
            binding.shimmerLayout.stopShimmer()
            binding.shimmerLayout.visibility = View.GONE
            binding.trendingMoviesRv.visibility = View.GONE
            binding.pageInfoContainer.visibility = View.GONE
            binding.errorContainer.visibility = View.VISIBLE
            binding.errorMessage.text = "$errorMessage \n Click To Refresh"
        }

    }

    private fun onRefresh() {
        viewModel.getMovies(viewModel.page.value ?: 1)
    }


}