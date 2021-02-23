package com.davidups.starwars.features.people.domain.usecases

import com.davidups.starwars.core.exception.Failure
import com.davidups.starwars.core.functional.Either
import com.davidups.starwars.core.interactor.UseCase
import com.davidups.starwars.features.people.data.models.data.Person
import com.davidups.starwars.features.people.domain.repositories.PeopleRepository
import kotlinx.coroutines.flow.Flow

class GetNextPage(private val peopleRepository: PeopleRepository): UseCase<Either<Failure, List<Person>>, UseCase.None>() {
    override fun run(params: None?): Flow<Either<Failure, List<Person>>> = peopleRepository.getMorePeople()

}