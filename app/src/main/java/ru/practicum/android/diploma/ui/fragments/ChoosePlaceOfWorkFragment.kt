package ru.practicum.android.diploma.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentChoosePlaceOfWorkBinding
import ru.practicum.android.diploma.presentation.ChoosePlaceOfWorkViewModel
import ru.practicum.android.diploma.ui.CountryAdapter

class ChoosePlaceOfWorkFragment : Fragment() {
    private var _binding: FragmentChoosePlaceOfWorkBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ChoosePlaceOfWorkViewModel by viewModel()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentChoosePlaceOfWorkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val country = viewModel.fetchCountry()
        if (country == null) {
            binding.countryEmpty.isVisible = true
            binding.countryBottom.isVisible = false
            binding.countryTop.isVisible = false
        } else {
            binding.countryEmpty.isVisible = false
            binding.countryBottom.isVisible = true
            binding.countryTop.isVisible = true
            binding.countryTop.setText(R.string.country)
            binding.countryBottom.text = country.name
        }

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.countryButton.setOnClickListener {
            findNavController().navigate(R.id.choose_country)
        }
    }
}
