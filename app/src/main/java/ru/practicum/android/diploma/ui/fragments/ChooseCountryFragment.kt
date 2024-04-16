package ru.practicum.android.diploma.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.databinding.FragmentChooseCountryBinding
import ru.practicum.android.diploma.presentation.ChooseCountryViewModel
import ru.practicum.android.diploma.ui.CountryAdapter
import ru.practicum.android.diploma.ui.state.ChooseCountryState

class ChooseCountryFragment : Fragment() {
    private var _binding: FragmentChooseCountryBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<ChooseCountryViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentChooseCountryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        val adapter = CountryAdapter {
            viewModel.onItemClick(it)
            findNavController().popBackStack()
        }
        binding.recyclerView.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    when (it) {
                        is ChooseCountryState.Success -> {
                            adapter.submitData(it.areas)
                        }

                        is ChooseCountryState.Error -> {}
                    }
                }
            }
        }
    }
}
