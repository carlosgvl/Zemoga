package com.carlosvivas.zemoga.di.component

import com.carlosvivas.zemoga.BaseApp
import com.carlosvivas.zemoga.di.module.ApplicationModule
import dagger.Component

@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun inject(application: BaseApp)

}