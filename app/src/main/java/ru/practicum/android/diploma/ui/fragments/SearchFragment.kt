package ru.practicum.android.diploma.ui.fragments

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentSearchBinding
import ru.practicum.android.diploma.domain.models.VacancyModel
import ru.practicum.android.diploma.presentation.SearchViewModel
import ru.practicum.android.diploma.ui.state.SearchScreenState
import ru.practicum.android.diploma.util.ErrorVariant
import ru.practicum.android.diploma.util.adapter.Adapter

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<SearchViewModel>()
    private val adapter = Adapter(onClick = { actionOnClick(it.id) })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.searchState.observe(viewLifecycleOwner) {
            render(it)
        }

        binding.searchRecycleView.adapter = adapter

        binding.searchQuery.doOnTextChanged { query, _, _, _ ->
            if (query.isNullOrEmpty()) {
                binding.searchIconLoupe.isVisible = true
                binding.clearCrossIc.isVisible = false
            } else {
                binding.searchIconLoupe.isVisible = false
                binding.clearCrossIc.isVisible = true
            }

            if (binding.searchQuery.hasFocus() && query.toString().isNotEmpty()) {
                showBlank()
            }
            viewModel.onSearchQueryChange(query.toString())
        }

        binding.searchQuery.setOnEditorActionListener { v, actionId, event ->
            val isEnterKeyPressed = actionId == EditorInfo.IME_ACTION_DONE ||
                event?.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_ENTER
            if (isEnterKeyPressed) {
                hideKeyboard(v)
                true
            } else {
                false
            }
        }

        binding.searchQuery.requestFocus()
        binding.clearCrossIc.setOnClickListener {
            viewModel.onSearchQueryChange(null)
            clearSearch()
        }
    }

    private fun clearSearch() {
        binding.searchQuery.setText("")
        val view = requireActivity().currentFocus
        if (view != null) {
            hideKeyboard(view)
        }
        showBlank()
    }

    private fun hideKeyboard(view: View) {
        val imm = requireActivity().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun render(state: SearchScreenState) {
        when (state) {
            is SearchScreenState.Default -> showBlank()
            is SearchScreenState.Loading -> showProgress()
            is SearchScreenState.Success -> showSearchResult(state.vacancies, state.found)
            is SearchScreenState.NothingFound -> showSearchResult(state.vacancies, state.found)
            is SearchScreenState.Error -> showNoConnectionError(state.errorVariant)
            is SearchScreenState.LoadNextPage -> showBlank()
        }
    }

    private fun showSearchResult(vacancies: List<VacancyModel>, found: Int) {
        adapter.setVacancies(vacancies)
        with(binding) {
            searchRecycleView.isVisible = true
            centerProgressBar.isVisible = false
            bottomProgressBar.isVisible = false
            textUnderSearch.isVisible = false
            searchDefaultPlaceholder.isVisible = false
            noVacancyToShow.isVisible = false
            noConnectionPlaceholder.isVisible = false
        }
        if (vacancies.isEmpty()) {
            showNoVacancyError()
        } else {
            binding.textUnderSearch.isVisible = true
            binding.textUnderSearch.text = resources.getQuantityString(R.plurals.vacancy_count, found, found)
            binding.noVacancyToShow.isVisible = false
            binding.searchRecycleView.isVisible = true
        }
    }

    private fun showNoVacancyError() {
        with(binding) {
            searchRecycleView.isVisible = true
            centerProgressBar.isVisible = false
            bottomProgressBar.isVisible = false
            textUnderSearch.isVisible = false
            searchDefaultPlaceholder.isVisible = false
            noVacancyToShow.isVisible = false
            noConnectionPlaceholder.isVisible = false
            binding.textUnderSearch.isVisible = false
            binding.noVacancyToShow.isVisible = true
            binding.noVacancyToShowText.isVisible = true
        }
    }

    private fun showNoConnectionError(errorVariant: ErrorVariant) {
        with(binding) {
            searchRecycleView.isVisible = false
            centerProgressBar.isVisible = false
            bottomProgressBar.isVisible = false
            textUnderSearch.isVisible = false
            searchDefaultPlaceholder.isVisible = false
            noVacancyToShow.isVisible = false
            noConnectionPlaceholder.isVisible = false
            noConnectionText.isVisible = false
            noVacancyToShowText.isVisible = false
        }
        when (errorVariant) {
            ErrorVariant.NO_CONNECTION -> {
                binding.noConnectionPlaceholder.isVisible = true
                binding.noConnectionText.isVisible = true
            }

            else -> {
                binding.noVacancyToShowText.isVisible = true
                binding.noVacancyToShow.isVisible = true
            }

        }
    }

    private fun showProgress() {
        with(binding) {
            searchRecycleView.isVisible = false
            centerProgressBar.isVisible = true
            bottomProgressBar.isVisible = false
            textUnderSearch.isVisible = false
            searchDefaultPlaceholder.isVisible = false
            noVacancyToShow.isVisible = false
            noConnectionPlaceholder.isVisible = false
        }
    }

    private fun showBlank() {
        with(binding) {
            searchRecycleView.isVisible = false
            centerProgressBar.isVisible = false
            bottomProgressBar.isVisible = false
            textUnderSearch.isVisible = false
            searchDefaultPlaceholder.isVisible = true
            noVacancyToShow.isVisible = false
            noConnectionPlaceholder.isVisible = false
            noConnectionText.isVisible = false
            noVacancyToShowText.isVisible = false
        }
    }

    private fun actionOnClick(id: String) {
        if (!viewModel.isClickable) return
        viewModel.actionOnClick()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        hideKeyboard(binding.searchQuery)
    }
}
