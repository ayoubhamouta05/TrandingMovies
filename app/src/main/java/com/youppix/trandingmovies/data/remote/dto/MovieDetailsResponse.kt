package com.youppix.trandingmovies.data.remote.dto

import com.youppix.trandingmovies.domain.model.BelongsToCollection
import com.youppix.trandingmovies.domain.model.Genre
import com.youppix.trandingmovies.domain.model.ProductionCompany
import com.youppix.trandingmovies.domain.model.ProductionCountry
import com.youppix.trandingmovies.domain.model.SpokenLanguage

data class MovieDetailsResponse(
    val adult: Boolean?=false,
    val backdrop_path: String? ="",
    val belongs_to_collection: BelongsToCollection?,
    val budget: Int? = 0,
    val genres: List<Genre>? = emptyList(),
    val homepage: String? = "",
    val id: Int?,
    val imdb_id: String? = "",
    val origin_country: List<String>?  = emptyList(),
    val original_language: String? = "",
    val original_title: String? = "",
    val overview: String? = "",
    val popularity: Double? = 0.0,
    val poster_path: String? = "",
    val production_companies: List<ProductionCompany>? = emptyList(),
    val production_countries: List<ProductionCountry>? = emptyList(),
    val release_date: String? = "",
    val revenue: Int? = 0,
    val runtime: Int? = 0,
    val spoken_languages: List<SpokenLanguage>? = emptyList(),
    val status: String? = "",
    val tagline: String? = "",
    val title: String? = "",
    val video: Boolean? = false,
    val vote_average: Double?=0.0,
    val vote_count: Int?=0
)