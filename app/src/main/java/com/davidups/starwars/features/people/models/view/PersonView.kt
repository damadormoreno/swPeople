package com.davidups.starwars.features.people.models.view

import com.davidups.starwars.core.extensions.empty
import java.io.Serializable

data class PersonView(
    val name: String
) {

    companion object {
        fun empty() =
            PersonView(String.empty())
    }
}
