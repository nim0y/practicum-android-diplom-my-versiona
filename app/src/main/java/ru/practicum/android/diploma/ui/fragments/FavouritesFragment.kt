package ru.practicum.android.diploma.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentFavouritesBinding
import ru.practicum.android.diploma.domain.models.VacancyDetailsModel
import ru.practicum.android.diploma.presentation.FavouritesViewModel
import ru.practicum.android.diploma.ui.state.FavouritesScreenState
import ru.practicum.android.diploma.util.adapter.FavoritesAdapter

class FavouritesFragment : Fragment() {
    private var _binding: FragmentFavouritesBinding? = null
    val favoritesAdapter = FavoritesAdapter(::openVacancy)
    private val binding get() = _binding!!
    private val viewModel by viewModel<FavouritesViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvVacancies.adapter = favoritesAdapter
        binding.rvVacancies.layoutManager = LinearLayoutManager(context)
        viewModel.favoritesState.observe(viewLifecycleOwner) {
            render(it)
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.start()
    }

    fun openVacancy(vacancy: VacancyDetailsModel) {
        val navController = findNavController()
        val bundle = Bundle()
        bundle.putString("vacancyId", vacancy.id)
        navController.navigate(R.id.vacancyFragment, bundle)
    }

    fun render(state: FavouritesScreenState) {
        when (state) {
            FavouritesScreenState.Empty -> stateEmpty()
            FavouritesScreenState.Error -> stateError()
            is FavouritesScreenState.ShowContent -> stateShowContent(state.data)
        }
    }

    fun stateEmpty() {
        binding.rvVacancies.isVisible = false
        binding.groupError.isVisible = false
        binding.groupEmpty.isVisible = true
    }

    fun stateError() {
        binding.rvVacancies.isVisible = false
        binding.groupError.isVisible = true
        binding.groupEmpty.isVisible = false
    }

    fun stateShowContent(data: List<VacancyDetailsModel>) {
        favoritesAdapter.setItems(data)
        binding.rvVacancies.isVisible = true
        binding.groupError.isVisible = false
        binding.groupEmpty.isVisible = false
    }
}
