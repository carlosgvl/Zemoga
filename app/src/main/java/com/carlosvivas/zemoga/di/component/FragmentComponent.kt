package com.carlosvivas.zemoga.di.component

import com.carlosvivas.zemoga.di.module.FragmentModule
import com.carlosvivas.zemoga.ui.detail.DetailFragment
import com.carlosvivas.zemoga.ui.posts.PostFragment
import dagger.Component

@Component(modules = arrayOf(FragmentModule::class))
interface FragmentComponent {

    fun inject(detailFragment: DetailFragment)

    fun inject(postFragment: PostFragment)

}