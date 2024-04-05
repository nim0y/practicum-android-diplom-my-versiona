package ru.practicum.android.diploma.di

import org.koin.dsl.bind
import org.koin.dsl.module
import ru.practicum.android.diploma.data.repository.SearchRepositoryImpl
import ru.practicum.android.diploma.domain.api.SearchRepository

val repositoryModule = module {
    factory { SearchRepositoryImpl(get()) } bind SearchRepository::class
}
