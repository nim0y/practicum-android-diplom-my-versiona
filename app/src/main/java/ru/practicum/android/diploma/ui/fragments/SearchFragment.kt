package ru.practicum.android.diploma.ui.fragments

import android.os.Bundle
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentSearchBinding
import ru.practicum.android.diploma.domain.models.VacancyModel
import ru.practicum.android.diploma.presentation.SearchViewModel
import ru.practicum.android.diploma.ui.State.SearchScreenState

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private var textWatcher: TextWatcher? = null
    private val viewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun render(state: SearchScreenState){
        when(state){
            is SearchScreenState.Default -> showBlank()
            is SearchScreenState.Loading->showProgress()
            is SearchScreenState.Success->showSearchResult(state.vacancies,state.found)
            is SearchScreenState.NothingFound -> showNoVacancyError()
            is SearchScreenState.Error -> showNoConnectionError()
            is SearchScreenState.LoadNextPage -> showBlank()
        }
    }
    private fun showSearchResult(vacancies:List<VacancyModel>,found:Int){
        with(binding){
            searchRecycleView.isVisible = true
            centerProgressBar.isVisible =false
            bottomProgressBar.isVisible=false
            textUnderSearch.isVisible=false
            searchDefaultPlaceholder.isVisible=false
            noVacancyToShow.isVisible=false
            noConnectionPlaceholder.isVisible=false
        }
        if (vacancies.isEmpty()){
            binding.textUnderSearch.isVisible = true
            binding.textUnderSearch.text = resources.getString(R.string.no_vacancy_list)
            binding.noVacancyToShow.isVisible =true
        }else{
            binding.textUnderSearch.isVisible = true
            binding.textUnderSearch.text = resources.getQuantityString(R.plurals.vacancy_count,found)
            binding.noVacancyToShow.isVisible =false
            binding.searchRecycleView.isVisible = true
        }
    }
    private fun showNoVacancyError(){
        with(binding){
            searchRecycleView.isVisible = false
            centerProgressBar.isVisible =false
            bottomProgressBar.isVisible=false
            textUnderSearch.isVisible=false
            searchDefaultPlaceholder.isVisible=false
            noVacancyToShow.isVisible=true
            noConnectionPlaceholder.isVisible=false
        }
    }
    private fun showNoConnectionError(){
        with(binding){
            searchRecycleView.isVisible = false
            centerProgressBar.isVisible =false
            bottomProgressBar.isVisible=false
            textUnderSearch.isVisible=false
            searchDefaultPlaceholder.isVisible=false
            noVacancyToShow.isVisible=false
            noConnectionPlaceholder.isVisible=true

        }
    }
    private fun showProgress(){
        with(binding){
            searchRecycleView.isVisible = false
            centerProgressBar.isVisible =true
            bottomProgressBar.isVisible=false
            textUnderSearch.isVisible=false
            searchDefaultPlaceholder.isVisible=false
            noVacancyToShow.isVisible=false
            noConnectionPlaceholder.isVisible=false

        }
    }
    private fun showBlank(){
        with(binding){
            searchRecycleView.isVisible = false
            centerProgressBar.isVisible =false
            bottomProgressBar.isVisible=false
            textUnderSearch.isVisible=false
            searchDefaultPlaceholder.isVisible=true
            noVacancyToShow.isVisible=false
            noConnectionPlaceholder.isVisible=false

        }
    }
}
