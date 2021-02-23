package com.davidups.starwars.features.people.data.models.view

import com.davidups.starwars.core.extensions.randomImage
import com.davidups.starwars.features.people.data.models.data.Person
import java.io.Serializable


data class PersonView(
    val id: Int,
    val name: String,
    val height: String,
    val mass: String,
    val hair_color: String,
    val skin_color: String,
    val eye_color: String,
    val birth_year: String,
    val gender: String,
    var isFavorite: Boolean = false,
    val urlImage: String = String.randomImage()
): Serializable {
    fun toPerson() = Person(
        id,
        name ,
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
