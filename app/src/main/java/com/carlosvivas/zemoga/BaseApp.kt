package com.carlosvivas.zemoga

import android.app.Application
import com.carlosvivas.zemoga.di.component.ApplicationComponent
import com.carlosvivas.zemoga.di.component.DaggerActivityComponent
import com.carlosvivas.zemoga.di.component.DaggerApplicationComponent
import com.carlosvivas.zemoga.di.module.ActivityModule
import com.carlosvivas.zemoga.di.module.ApplicationModule

class BaseApp: Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        instance = this
        setup()

        if (BuildConfig.DEBUG) {
        }
    }

    fun setup() {



        component = DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this)).build()
        component.inject(this)
    }



    companion object {
        lateinit var instance: BaseApp private set
    }
}