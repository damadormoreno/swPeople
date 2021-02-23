package com.davidups.starwars.features.people.data.services

import com.davidups.starwars.features.people.data.models.entity.PeopleEntity
import retrofit2.Call
import retrofit2.Retrofit

class PeopleService(retrofit: Retrofit) : PeopleApi {

    private val peopleApi by lazy { retrofit.create(PeopleApi::class.java) }

    override fun getPeople():Call<PeopleEntity> = peopleApi.getPeople()
}
