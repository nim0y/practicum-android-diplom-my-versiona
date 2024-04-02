package ru.practicum.android.diploma.util.adapter

import android.content.Context
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.domain.models.VacancyModel

    fun convertSalary(item: VacancyModel,context: Context):String{
        return when {
            (item.salary?.currency == null) -> context.getString(R.string.no_salary_info)

            ((item.salary.to == null) && (item.salary.from == null)) -> context.getString(R.string.no_salary_info)

            (item.salary.to == null) -> "${context.getString(R.string.from)} ${item.salary.from} ${item.salary.currency}"

            (item.salary.from == null) -> "${context.getString(R.string.to)} ${item.salary.to} ${item.salary.currency}"

            else -> "${context.getString(R.string.from)} ${item.salary.from} " + "${
                context.getString(
                    R.string.to
                )
            } ${item.salary.to} ${item.salary.currency}"
        }
    }
