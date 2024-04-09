package ru.practicum.android.diploma.util.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.databinding.SearchItemViewBinding
import ru.practicum.android.diploma.domain.models.VacancyDetailsModel

class FavoritesAdapter(val clickItem: (VacancyDetailsModel) -> Unit) : RecyclerView.Adapter<FavoritesItemViewHolder>() {
    var vacancyList = ArrayList<VacancyDetailsModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return FavoritesItemViewHolder(SearchItemViewBinding.inflate(layoutInflater, parent, false), clickItem, {})
    }

    override fun onBindViewHolder(holder: FavoritesItemViewHolder, position: Int) {
        holder.bind(vacancyList[position])
        holder.itemView.setOnClickListener { clickItem(vacancyList[position]) }
    }

    override fun getItemCount(): Int {
        return vacancyList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(newList: List<VacancyDetailsModel>) {
        vacancyList = newList as ArrayList<VacancyDetailsModel>
        notifyDataSetChanged()
    }

    fun getItemByPosition(position: Int) =
        vacancyList[position]

}
