package com.carlosvivas.zemoga.di.module

import android.app.Activity
import com.carlosvivas.zemoga.ui.main.MainContract
import com.carlosvivas.zemoga.ui.main.MainPresenter
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private var activity: Activity) {

    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    fun providePresenter(): MainContract.Presenter {
        return MainPresenter()
    }

}
