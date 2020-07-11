package com.akerimtay.weatherapp.di.component

import com.akerimtay.weatherapp.di.module.RepositoryModule
import com.akerimtay.weatherapp.di.scope.DataScope
import com.akerimtay.weatherapp.viewmodel.MainViewModel
import dagger.Subcomponent

@DataScope
@Subcomponent(modules = [(RepositoryModule::class)])
interface DataComponent {
    fun inject(viewModel: MainViewModel)
}