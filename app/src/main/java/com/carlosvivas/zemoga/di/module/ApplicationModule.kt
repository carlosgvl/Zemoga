package com.carlosvivas.zemoga.di.module

import android.app.Application
import com.carlosvivas.zemoga.BaseApp
import com.carlosvivas.zemoga.di.scope.PerApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val baseApp: BaseApp) {

    @Provides
    @Singleton
    @PerApplication
    fun provideApplication(): Application {
        return baseApp
    }
}