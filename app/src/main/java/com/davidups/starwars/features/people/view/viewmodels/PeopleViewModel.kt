package com.davidups.starwars.features.people.view.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.davidups.skell.core.extensions.cancelIfActive
import com.davidups.starwars.core.exception.Failure
import com.davidups.starwars.core.interactor.UseCase
import com.davidups.starwars.core.functional.Error
import com.davidups.starwars.core.functional.Success
import com.davidups.starwars.core.functional.map
import com.davidups.starwars.core.platform.BaseViewModel
import com.davidups.starwars.features.people.data.models.data.Person
import com.davidups.starwars.features.people.data.models.view.PeopleView
import com.davidups.starwars.features.people.data.models.view.PersonView
import com.davidups.starwars.features.people.domain.usecases.GetNextPage
import com.davidups.starwars.features.people.domain.usecases.GetPeople
import com.davidups.starwars.features.people.domain.usecases.SetFavorite
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class PeopleViewModel(
    private val getPeople: GetPeople,
    private val setFavorite: SetFavorite,
    private val getNextPage: GetNextPage
) : BaseViewModel() {

    private val _persons: MutableLiveData<List<PersonView>> = MutableLiveData()
    val persons: LiveData<List<PersonView>> = _persons

    private val auxList: MutableList<PersonView> = mutableListOf()
    private var filteredList: MutableList<PersonView> = mutableListOf()

    private var isFiltered: Boolean = false

    var getMoviesJob: Job? = null

    init {
        getMoviesJob.cancelIfActive()
        getMoviesJob = viewModelScope.launch { getPeople() }
    }

    suspend fun getPeople() {

            getPeople(UseCase.None())
                .onStart { handleShowSpinner(true) }
                .onEach { handleShowSpinner(false) }
                .catch { failure ->
                    handleFailure(Failure.CustomError(failure.hashCode(), failure.localizedMessage))
                }
                .collect {
                    it.fold(::handleFailure, ::handlePersons)
                }

    }

    suspend fun getNextPage() {
        getNextPage(UseCase.None())
            .onStart { handleShowSpinner(true) }
            .onEach { handleShowSpinner(false) }
            .catch { failure ->
                handleFailure(Failure.CustomError(failure.hashCode(), failure.localizedMessage))
            }
            .collect {
                it.fold(::handleFailure, ::handlePersons)
            }
    }

     suspend fun favorite(personView: PersonView) {
        val personEntity = personView.toPerson().toPersonEntity()
        viewModelScope.launch {
            setFavorite(SetFavorite.Params(personEntity)).collect {  }
        }
    }

    fun filterFavoriteList(){
        isFiltered = !isFiltered
        if (isFiltered) {
            _persons.value = auxList.filter { it.isFavorite }.toMutableList()
        } else {
            _persons.value = auxList
        }

    }

    private fun handlePersons(persons: List<Person>) {
        auxList.addAll(persons.map { it.toPersonView() })
        _persons.value = auxList
    }
}
