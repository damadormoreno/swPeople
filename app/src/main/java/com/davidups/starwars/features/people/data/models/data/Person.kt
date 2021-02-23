package com.davidups.starwars.features.people.data.models.data

import com.davidups.starwars.core.extensions.empty
import com.davidups.starwars.features.people.data.models.entity.PersonEntity
import com.davidups.starwars.features.people.data.models.view.PersonView

data class Person(
    val id: Int,
    val name: String?,
    val height: String?,
    val mass: String?,
    val hair_color: String?,
    val skin_color: String?,
    val eye_color: String?,
    val birth_year: String?,
    val gender: String?,
    var isFavorite: Boolean = false
) {

    fun toPersonView() = PersonView(
        id,
        name ?: String.empty(),
        height?: String.empty(),
        mass?: String.empty(),
        hair_color?: String.empty(),
        skin_color?: String.empty(),
        eye_color?: String.empty(),
        birth_year?: String.empty(),
        gender?: String.empty(),
        isFavorite
    )

    fun toPersonEntity() = PersonEntity(
        id,
        name,
        height,
        mass,
        hair_color,
        skin_color,
        eye_color,
        birth_year,
        gender,
        isFavorite
    )
}
