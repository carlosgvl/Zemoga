package com.carlosvivas.zemoga.ui.posts

import com.carlosvivas.zemoga.models.Post
import com.carlosvivas.zemoga.network.ApiInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PostPresenter: PostContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private val api: ApiInterface = ApiInterface.create()
    private lateinit var view: PostContract.View

    override fun subscribe() {
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: PostContract.View) {
        this.view = view
    }

    override fun loadData() {
        var subscribe = api.getPostList().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list: List<Post>? ->
                view.showProgress(false)
                view.loadDataSuccess(list!!)
            }, { error ->
                view.showProgress(false)
                view.showErrorMessage(error.localizedMessage)
            })

        subscriptions.add(subscribe)
    }


}