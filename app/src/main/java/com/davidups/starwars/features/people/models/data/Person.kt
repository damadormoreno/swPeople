package com.davidups.starwars.features.people.models.data

import com.davidups.starwars.core.extensions.empty
import com.davidups.starwars.features.people.models.view.PersonView

data class Person(
    val name: String?,
    val height: String?,
    val mass: String?,
    val hair_color: String?,
    val skin_color: String?,
    val eye_color: String?,
    val birth_year: String?,
    val gender: String?
) {

    fun toPersonView() = PersonView(name ?: String.empty())
}
