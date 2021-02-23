package com.davidups.starwars.features.people.data.services

import com.davidups.starwars.features.people.data.models.entity.PeopleEntity
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

internal interface PeopleApi {

    companion object {
        private const val PEOPLE = "people"
    }

    @GET(PEOPLE)
    fun getPeople(): Call<PeopleEntity>

    @GET(PEOPLE)
    fun getPeopleByPage(@Query("page") page: Int): Call<PeopleEntity>
}
