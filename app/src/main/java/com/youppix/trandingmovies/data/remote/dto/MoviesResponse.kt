package com.youppix.trandingmovies.data.remote.dto

import com.youppix.trandingmovies.domain.model.Result

data class MoviesResponse(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)