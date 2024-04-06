package ru.practicum.android.diploma.util.adapter

object ConvertCurrency {

    fun formatValueSalary(salaryFrom: Int?, salaryTo: Int?): String {
        return when {
            salaryFrom != null && (salaryTo == null || salaryTo == 0) -> "от ${toSalaryString(salaryFrom)}"
            (salaryFrom == 0 || salaryFrom == null) && salaryTo != null && salaryTo != 0 -> "до ${
                toSalaryString(salaryTo)
            }"

            salaryFrom != null && salaryFrom != 0 && salaryTo != null && salaryTo != 0 -> "от ${
                toSalaryString(salaryFrom)
            } до ${toSalaryString(salaryTo)}"

            else -> {
                "Зарплата не указана"
            }
        }
    }

    const val cycleSize = 3

    fun toSalaryString(salary: Int): String {
        val salarySize = salary.toString().count() - 1
        var result = ""
        var cycle = 0
        for (i in salarySize downTo 0) {
            cycle = cycle + 1
            if (cycle == cycleSize) {
                cycle = 0
                result = " " + salary.toString()[i] + result
            } else {
                result = salary.toString()[i] + result
            }
        }
        return result.trim()
    }

    fun converterSalaryToString(salaryFrom: Int?, salaryTo: Int?, currency: String?): String {
        val formattedSalary = formatValueSalary(salaryFrom, salaryTo)
        return when (currency) {
            "USD" -> "$formattedSalary €"
            "EUR" -> "$formattedSalary $"
            "RUR" -> "$formattedSalary ₽"
            "AZN" -> "$formattedSalary ₼"
            "BYR" -> "$formattedSalary Br"
            "GEL" -> "$formattedSalary ₾"
            "KGS" -> "$formattedSalary с"
            "KZT" -> "$formattedSalary ₸"
            "UAH" -> "$formattedSalary ₴"
            "UZS" -> "$formattedSalary Soʻm"
            else -> formattedSalary
        }
    }
}
