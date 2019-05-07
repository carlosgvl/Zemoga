package com.carlosvivas.zemoga.ui.detail

import com.carlosvivas.zemoga.models.Comments
import com.carlosvivas.zemoga.models.User
import com.carlosvivas.zemoga.ui.base.BaseContract
import com.carlosvivas.zemoga.util.Prefs

class DetailContract {

    interface View: BaseContract.View {
        fun showProgress(show: Boolean)

        fun showErrorMessage(error: String)
        fun loadDataSuccess(list: List<Comments>)
        fun loadUserSuccess(list: User)
    }

    interface Presenter: BaseContract.Presenter<View> {

        fun loadData(toInt: Int)
        fun loadUser(user: Int)
        fun drawerfavorite(prefs: Prefs, postID: Int )
    }
}