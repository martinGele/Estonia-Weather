package com.martin.weatherestonia.di

import com.martin.weatherestonia.viewmodel.MainScreenViewModel
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [ApiModule::class, AppModule::class])
interface ViewModelComponent {

    fun inject(viewModel: MainScreenViewModel)
}