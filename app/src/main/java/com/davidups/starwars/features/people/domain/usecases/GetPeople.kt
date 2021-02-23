package com.davidups.starwars.features.people.domain.usecases

import com.davidups.starwars.core.exception.Failure
import com.davidups.starwars.core.functional.Either
import com.davidups.starwars.core.interactor.UseCase
import com.davidups.starwars.core.functional.State
import com.davidups.starwars.features.people.data.models.data.Person
import com.davidups.starwars.features.people.domain.repositories.PeopleRepository
import com.davidups.starwars.features.people.data.models.view.PeopleView

class GetPeople(private val peopleRepository: PeopleRepository) : UseCase<Either<Failure, List<Person>>, UseCase.None>() {
    override fun run(params: None?) = peopleRepository.getPeople()
}
