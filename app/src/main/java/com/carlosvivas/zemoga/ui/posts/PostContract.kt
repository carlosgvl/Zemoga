package com.carlosvivas.zemoga.ui.posts

import com.carlosvivas.zemoga.models.Post
import com.carlosvivas.zemoga.ui.base.BaseContract

class PostContract {

    interface View: BaseContract.View {
        fun showProgress(show: Boolean)
        fun showErrorMessage(error: String)
        fun loadDataSuccess(list: List<Post>)
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun loadData()

    }
}