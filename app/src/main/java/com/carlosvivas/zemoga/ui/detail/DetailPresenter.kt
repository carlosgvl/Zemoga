package com.carlosvivas.zemoga.ui.detail

import com.carlosvivas.zemoga.models.Comments
import com.carlosvivas.zemoga.models.User
import com.carlosvivas.zemoga.network.ApiInterface
import com.carlosvivas.zemoga.util.Prefs
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailPresenter: DetailContract.Presenter {


    private lateinit var view: DetailContract.View
    private val subscriptions = CompositeDisposable()
    private val api: ApiInterface = ApiInterface.create()

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()

    }

    override fun attach(view: DetailContract.View) {
        this.view = view
    }



    override fun loadData(post: Int) {
        var subscribe = api.getCommentByPostId(post).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list: List<Comments>? ->
                view.showProgress(false)
                view.loadDataSuccess(list!!)
            }, { error ->
                view.showProgress(false)
                view.showErrorMessage(error.localizedMessage)
            })

        subscriptions.add(subscribe)
    }

    override fun loadUser(user_id: Int) {
        var subscribe = api.getUser(user_id).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list: User? ->
                view.showProgress(false)
                view.loadUserSuccess(list!!)
            }, { error ->
                view.showProgress(false)
                view.showErrorMessage(error.localizedMessage)
            })

        subscriptions.add(subscribe)
    }



    override fun drawerfavorite(prefs: Prefs, postID: Int ){
        prefs!!.Favorite_post = postID
    }


}