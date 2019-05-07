package com.carlosvivas.zemoga.di.module

import com.carlosvivas.zemoga.network.ApiInterface
import com.carlosvivas.zemoga.ui.detail.DetailContract
import com.carlosvivas.zemoga.ui.detail.DetailPresenter
import com.carlosvivas.zemoga.ui.posts.PostContract
import com.carlosvivas.zemoga.ui.posts.PostPresenter
import dagger.Module
import dagger.Provides

@Module
class FragmentModule {

    @Provides
    fun provideAboutPresenter(): DetailContract.Presenter {
        return DetailPresenter()
    }

    @Provides
    fun provideListPresenter(): PostContract.Presenter {
        return PostPresenter()
    }

    @Provides
    fun provideApiService(): ApiInterface {
        return ApiInterface.create()
    }
}