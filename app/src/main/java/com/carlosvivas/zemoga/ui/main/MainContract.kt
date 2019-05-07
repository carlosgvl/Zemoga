package com.carlosvivas.zemoga.ui.main

import com.carlosvivas.zemoga.ui.base.BaseContract

class MainContract {

    interface View: BaseContract.View {
        fun setupViewPager()
    }

    interface Presenter: BaseContract.Presenter<MainContract.View> {
        fun onDrawerOptionAboutClick()
    }
}