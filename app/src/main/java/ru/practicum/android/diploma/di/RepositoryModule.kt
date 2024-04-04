package ru.practicum.android.diploma.di

import org.koin.dsl.bind
import org.koin.dsl.module
import ru.practicum.android.diploma.data.repository.MainRepositoryImpl
import ru.practicum.android.diploma.domain.api.MainRepository

val repositoryModule = module {
    factory { MainRepositoryImpl(get()) } bind MainRepository:: class
}
