package com.akerimtay.weatherapp.di.component

import com.akerimtay.weatherapp.di.module.RepositoryModule
import com.akerimtay.weatherapp.di.scope.DataScope
import dagger.Subcomponent

@DataScope
@Subcomponent(modules = [(RepositoryModule::class)])
interface DataComponent {
}