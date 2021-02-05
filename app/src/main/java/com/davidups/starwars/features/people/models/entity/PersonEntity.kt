package com.davidups.starwars.features.people.models.entity

import com.davidups.starwars.core.extensions.empty
import com.davidups.starwars.features.people.models.data.Person

data class PersonEntity(
    val name: String?,
    val height: String?,
    val mass: String?,
    val hair_color: String?,
    val skin_color: String?,
    val eye_color: String?,
    val birth_year: String?,
    val gender: String?
) {

    companion object {
        fun empty() =
            PersonEntity(
                String.empty(),
                String.empty(),
                String.empty(),
                String.empty(),
                String.empty(),
                String.empty(),
                String.empty(),
                String.empty()
            )
    }

    fun toPerson() =
        Person(name, height, mass, hair_color, skin_color, eye_color, birth_year, gender)
}
