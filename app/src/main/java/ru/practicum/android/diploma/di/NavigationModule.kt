package ru.practicum.android.diploma.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.practicum.android.diploma.data.sharing.ExternalNavigator
import ru.practicum.android.diploma.data.sharing.impl.ExternalNavigatorImpl

val navigationModule = module {

    single<ExternalNavigator> {
        ExternalNavigatorImpl(context = androidContext())
    }
}
