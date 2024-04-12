package ru.practicum.android.diploma.di

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.practicum.android.diploma.data.storage.FiltersLocalStorage

const val FILTERS_PREFS = "FILTERS_PREFS"

val sharedPreferencesModule = module {

    single {
        FiltersLocalStorage(androidContext().getSharedPreferences(FILTERS_PREFS, Context.MODE_PRIVATE))
    }
}
