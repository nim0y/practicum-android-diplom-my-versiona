package ru.practicum.android.diploma.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
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

        viewModel.fetchCountry()

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.countryState.collect { country ->
                    if (country == null) {
                        binding.countryEmpty.isVisible = true
                        binding.countryBottom.isVisible = false
                        binding.countryTop.isVisible = false
                        binding.countryNext.isVisible = true
                        binding.countryRemove.isVisible = false
                        binding.chooseButton.isVisible = false
                    } else {
                        binding.countryEmpty.isVisible = false
                        binding.countryBottom.isVisible = true
                        binding.countryTop.isVisible = true
                        binding.countryBottom.text = country.name
                        binding.countryNext.isVisible = false
                        binding.countryRemove.isVisible = true
                        binding.chooseButton.isVisible = true
                    }
                }
            }
        }

        binding.countryRemove.setOnClickListener { viewModel.removeCountry() }

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.countryClick.setOnClickListener {
            findNavController().navigate(R.id.choose_country)
        }
    }
}
