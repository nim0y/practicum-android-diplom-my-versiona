package ru.practicum.android.diploma.di

import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.practicum.android.diploma.data.network.api.HeadHuntersApi
import ru.practicum.android.diploma.data.network.client.NetworkClient
import ru.practicum.android.diploma.data.network.client.RetrofitInternetProvider
import ru.practicum.android.diploma.data.network.client.RetrofitNetworkClient

val networkModule = module {

    factory { RetrofitNetworkClient(androidApplication(), get()) } bind NetworkClient::class

    factory { RetrofitInternetProvider.retrofit.create(HeadHuntersApi::class.java) } bind HeadHuntersApi::class
}
