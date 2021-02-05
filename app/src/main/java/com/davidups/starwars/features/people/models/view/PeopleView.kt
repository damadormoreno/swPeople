package com.davidups.starwars.features.people.models.view

import com.davidups.starwars.core.extensions.empty
import java.io.Serializable

data class PeopleView(
    val count: Int?,
    val next: String?,
    val previous: String?,
    val results: MutableList<PersonView>?
) : Serializable {

    companion object {
        fun empty() = PeopleView(Int.empty(), String.empty(), String.empty(), mutableListOf())
    }
}
