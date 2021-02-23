package com.davidups.starwars.features.people.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.davidups.skell.R
import com.davidups.skell.core.extensions.loadFromUrl
import com.davidups.skell.databinding.FragmentDetailBinding
import com.davidups.starwars.core.platform.BaseFragment
import com.davidups.starwars.core.platform.viewBinding.viewBinding
import com.davidups.starwars.features.people.data.models.view.PersonView

class DetailFragment : BaseFragment(R.layout.fragment_detail) {

    private val binding by viewBinding(FragmentDetailBinding::bind)
    lateinit var person: PersonView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val arguments = arguments
        if (arguments != null) {
            person = arguments.getSerializable("person") as PersonView
            initLayout()
        }
    }

    private fun initLayout() {
        binding.imageDetail.loadFromUrl(person.urlImage)
        binding.tvName.text = person.name
        binding.tvBirthYear.text = "Birth Year: ${person.birth_year}"
        binding.tvGender.text = "Gender: ${person.gender}"
        binding.tvHairColor.text = "Hair color: ${person.hair_color}"
        binding.tvHeight.text = "Height: ${person.height}"
        binding.tvSkinColor.text = "Skin Color: ${person.skin_color}"
    }

}