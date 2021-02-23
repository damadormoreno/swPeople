package com.davidups.starwars.features.people.data.services

import com.davidups.starwars.features.people.data.models.entity.PeopleEntity
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

internal interface PeopleApi {

    companion object {
        private const val PEOPLE = "people"
    }

    @GET(PEOPLE)
    fun getPeople(): Call<PeopleEntity>
}
