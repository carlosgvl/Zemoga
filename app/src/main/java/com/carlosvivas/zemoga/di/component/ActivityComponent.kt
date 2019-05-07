package com.carlosvivas.zemoga.di.component

import com.carlosvivas.zemoga.di.module.ActivityModule
import com.carlosvivas.zemoga.ui.main.MainActivity
import dagger.Component

@Component(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)

}