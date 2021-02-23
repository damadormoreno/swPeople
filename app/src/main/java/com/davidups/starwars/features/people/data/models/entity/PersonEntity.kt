package com.davidups.starwars.features.people.data.models.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.davidups.starwars.core.extensions.empty
import com.davidups.starwars.features.people.data.models.data.Person

@Entity
data class PersonEntity(
    @PrimaryKey(autoGenerate = true)
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

    companion object {
        fun empty() =
            PersonEntity(
                0,
                String.empty(),
                String.empty(),
                String.empty(),
                String.empty(),
                String.empty(),
                String.empty(),
                String.empty(),
                String.empty(),
                false
            )
    }

    fun toPerson() =
        Person(id, name, height, mass, hair_color, skin_color, eye_color, birth_year, gender, isFavorite)
}
