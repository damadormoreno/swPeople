package com.davidups.starwars.features.people.services

import com.davidups.starwars.features.people.models.entity.PeopleEntity
import okhttp3.Response
import retrofit2.http.GET

internal interface PeopleApi {

    companion object {
        private const val PEOPLE = "people"
    }

    @GET(PEOPLE)
    suspend fun getPeople(): Response<PeopleEntity>
}
