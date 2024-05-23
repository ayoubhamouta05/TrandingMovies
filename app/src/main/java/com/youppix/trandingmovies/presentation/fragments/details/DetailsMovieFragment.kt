package com.youppix.trandingmovies.presentation.fragments.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.youppix.trandingmovies.R
import com.youppix.trandingmovies.data.remote.dto.MovieDetailsResponse
import com.youppix.trandingmovies.databinding.FragmentDetailsMovieBinding
import com.youppix.trandingmovies.domain.model.Genre
import com.youppix.trandingmovies.domain.model.ProductionCompany
import com.youppix.trandingmovies.domain.model.SpokenLanguage
import com.youppix.trandingmovies.presentation.activities.MainActivity
import com.youppix.trandingmovies.presentation.adapters.ProductionAdapter
import com.youppix.trandingmovies.util.Constant
import com.youppix.trandingmovies.util.Constant.IMG_BASE_URL
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailsMovieFragment : Fragment() {

    private lateinit var binding: FragmentDetailsMovieBinding

    private val viewModel by viewModels<DetailsMovieViewModel>()

    private val args by navArgs<DetailsMovieFragmentArgs>()

    private val productionAdapter by lazy {
        ProductionAdapter()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsMovieBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imgContainer.setBackgroundResource(R.drawable.trending_img_shape)
        binding.detailsContainer.setBackgroundResource(R.drawable.details_background)
        val movieId = args.movieId
        Log.d("DetailsMovieFragment", movieId.toString())
        viewModel.getMovieDetails(movieId)

        viewModel.movieDetails.observe(viewLifecycleOwner) {
            setupUi(it)
        }

        binding.backBtn.setOnClickListener {
            findNavController().navigate(R.id.TrendingMoviesFragment)
        }

    }

    private fun setupUi(movieDetails: MovieDetailsResponse) {
        binding.apply {
            Glide.with(root).load("$IMG_BASE_URL${movieDetails.poster_path}").into(backdrop)
            titleTextView.text = movieDetails.original_title
            releaseDate.text = movieDetails.release_date
            genresTv.text = getAllGenres(movieDetails.genres)
            linkBtn.setOnClickListener {
                if (movieDetails.homepage.isNotEmpty()) {
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(movieDetails.homepage))
                    startActivity(browserIntent)
                }

            }
            ratingBar.apply {
                rating = (movieDetails.vote_average / 2).toFloat()
            }
            voteAverage.text = "${movieDetails.vote_average}/10"
            voteCount.text = "(${movieDetails.vote_count})"

            overviewText.text = movieDetails.overview

            originalLanguage.text =   movieDetails.original_language

            originCountries.text =   movieDetails.origin_country.joinToString("/")

            setupProductionRv(movieDetails.production_companies)

            spokenLanguages.text = getAllSpokenLanguages(movieDetails.spoken_languages)

            collectionName.text = movieDetails.belongs_to_collection.name

            Glide.with(root).load("$IMG_BASE_URL${movieDetails.belongs_to_collection.poster_path}").into(collectionImg)



        }
    }

    private fun getAllGenres(genres: List<Genre>): String {
        val builder = StringBuilder()
        for (i in genres) {
            builder.append(i.name)
            builder.append("/")
        }
        return builder.toString().dropLast(1)
    }

    private fun getAllSpokenLanguages(languages: List<SpokenLanguage>): String {
        val builder = StringBuilder()
        for (i in languages) {
            builder.append(i.name)
            builder.append("/")
        }
        return builder.toString().dropLast(1)
    }

    private fun setupProductionRv(list : List<ProductionCompany>) {
        binding.productionRv.apply {
            adapter = productionAdapter
            layoutManager =
                LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.HORIZONTAL, false
                )

        }
        productionAdapter.differ.submitList(list)
    }


}