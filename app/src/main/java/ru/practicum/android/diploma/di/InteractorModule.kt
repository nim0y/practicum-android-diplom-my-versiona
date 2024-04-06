package ru.practicum.android.diploma.di

import org.koin.dsl.module
import ru.practicum.android.diploma.domain.sharing.SharingInteractor
import ru.practicum.android.diploma.domain.sharing.impl.SharingInteractorImpl

val interactorModule = module {

    single<SharingInteractor> {
        SharingInteractorImpl(externalNavigator = get())
    }
}
