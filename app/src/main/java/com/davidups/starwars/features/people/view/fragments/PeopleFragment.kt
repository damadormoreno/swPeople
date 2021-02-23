package com.davidups.starwars.features.people.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.davidups.skell.R
import com.davidups.skell.databinding.FragmentMoviesBinding
import com.davidups.starwars.core.exception.Failure
import com.davidups.starwars.core.extensions.failure
import com.davidups.starwars.core.extensions.observe
import com.davidups.starwars.core.extensions.showInfoAlertDialog
import com.davidups.starwars.core.platform.BaseFragment
import com.davidups.starwars.core.platform.viewBinding.viewBinding
import com.davidups.starwars.features.people.data.models.view.PersonView
import com.davidups.starwars.features.people.view.adapters.PeopleAdapter
import com.davidups.starwars.features.people.view.viewmodels.PeopleViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class PeopleFragment : BaseFragment(R.layout.fragment_movies) {

    private val binding by viewBinding(FragmentMoviesBinding::bind)

    private val peopleViewModel: PeopleViewModel by viewModel()
    private val peopleAdapter: PeopleAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(peopleViewModel) {
            observe(showSpinner, ::handleShowSpinner)
            observe(persons, ::handleMovies)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initListeners()
    }

    private fun initView() {

        binding.rvMovies.apply {
            layoutManager = GridLayoutManager(requireActivity(), 2)
            adapter = peopleAdapter
        }
    }

    private fun initListeners() {
        peopleAdapter.clickListener = { person ->
            findNavController().navigate(R.id.detailFragment)
        }

        peopleAdapter.clickFavListener = { personView ->
            personView.isFavorite = !personView.isFavorite
            lifecycleScope.launch { peopleViewModel.favorite(personView) }
            peopleAdapter.notifyDataSetChanged()
        }

        binding.rvMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = binding.rvMovies.layoutManager as GridLayoutManager
                if (linearLayoutManager.findLastCompletelyVisibleItemPosition() == peopleAdapter.collection.size - 1) {
                    lifecycleScope.launch { peopleViewModel.getNextPage() }
                }
            }
        })
    }

    private fun handleMovies(people: List<PersonView>?) {
        peopleAdapter.collection = people.orEmpty()

    }

    private fun handleShowSpinner(show: Boolean?) {
        showSpinner(show ?: false)
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.CustomError -> showErrorDialog(failure.errorMessage ?: "Error")
            is Failure.NetworkConnection -> showErrorDialog("Connection error")
            is Failure.ServerError -> showErrorDialog("Server error")
            is Failure.NoMorePages ->
                Log.d("PeopleFragment", "No more pages")
            else -> showErrorDialog("Error")
        }

    }

    private fun showErrorDialog(text: String) {
        showInfoAlertDialog {
            setTitle(text)
        }.show()
    }
}
