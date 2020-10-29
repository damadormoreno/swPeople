package com.davidups.starwars.features.movies.view.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.davidups.skell.core.extensions.cancelIfActive
import com.davidups.skell.core.interactor.UseCase
import com.davidups.starwars.core.functional.Error
import com.davidups.starwars.core.functional.Success
import com.davidups.starwars.core.platform.BaseViewModel
import com.davidups.starwars.features.movies.models.view.MoviesView
import com.davidups.starwars.features.movies.usecases.GetMovies
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MoviesViewModel(
    private val getMovies: GetMovies
) : BaseViewModel() {

    var movies = MutableLiveData<MoviesView>()
    var getMoviesJob: Job? = null

    fun getMovies() {
        getMoviesJob.cancelIfActive()
        getMoviesJob = viewModelScope.launch {
            getMovies(UseCase.None())
                .onStart { handleShowSpinner(true) }
                .onEach { handleShowSpinner(false) }
                .catch { failure -> handleFailure(failure) }
                .collect { result ->
                    when (result) {
                        is Success<MoviesView> -> {
                            movies.value = result.data
                        }
                        is Error -> {
                        }
                    }
                }
        }
    }
}
