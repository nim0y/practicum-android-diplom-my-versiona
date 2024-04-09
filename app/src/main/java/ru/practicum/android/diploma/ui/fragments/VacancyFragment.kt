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
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentVacancyBinding
import ru.practicum.android.diploma.presentation.VacancyViewModel

class VacancyFragment : Fragment() {

    private val viewModel: VacancyViewModel by viewModels()
    private var _binding: FragmentVacancyBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVacancyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val vacancyId = arguments?.getString("vacancyId") ?: return

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.favoriteVacanciesState.collect { list ->
                    val image = if (list.map { it.id }.contains(vacancyId)) {
                        R.drawable.ic_favorites_on_20px
                    } else {
                        R.drawable.ic_favorites_off_23px
                    }
                    binding.vacancyFavoriteIcon.setImageResource(image)
                }
            }
        }

        binding.vacancyFavoriteIcon.setOnClickListener {
//            viewModel.onLikeClick() todo pass VacancyDetailedModel
        }
    }
}
