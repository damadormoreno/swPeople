package com.davidups.starwars.features.movies.services

import com.davidups.starwars.features.movies.models.entity.MoviesEntity
import okhttp3.Response
import retrofit2.http.GET

internal interface MoviesApi {

    companion object {
        private const val FILMS = "films"
    }

    @GET(FILMS)
    suspend fun getMovies(): Response<MoviesEntity>
}
