package ru.practicum.android.diploma.util.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.databinding.SearchItemViewBinding
import ru.practicum.android.diploma.domain.models.VacancyModel

class Adapter(
    private val onClick: (VacancyModel) -> Unit,
    private val onLongClick: (VacancyModel) -> Unit = {}
) : RecyclerView.Adapter<ViewHolder>() {
    val vacancies = ArrayList<VacancyModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(SearchItemViewBinding.inflate(layoutInflater, parent, false), onClick, onLongClick)
    }

    override fun getItemCount(): Int {
        return vacancies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(vacancies[position])
    }

    fun setVacancies(newList: List<VacancyModel>?) {
        val compare = DiffCallback(vacancies, newList ?: emptyList())
        val diffResult = DiffUtil.calculateDiff(compare)
        vacancies.clear()
        if (!newList.isNullOrEmpty()) {
            vacancies.addAll(newList)
        }
        diffResult.dispatchUpdatesTo(this)
    }
}
