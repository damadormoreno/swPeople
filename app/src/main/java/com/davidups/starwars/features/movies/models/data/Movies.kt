package com.davidups.starwars.features.movies.models.data

import com.davidups.starwars.features.movies.models.view.MoviesView

data class Movies(
    val count: Int?,
    val next: String?,
    val previous: String?,
    val results: MutableList<Movie>?
) {

    fun toMoviesView() =
        MoviesView(count, next, previous, results?.map { it.toMovieView() }?.toMutableList())
}
