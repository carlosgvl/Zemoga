package com.carlosvivas.zemoga.ui.posts

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.carlosvivas.zemoga.R
import com.carlosvivas.zemoga.di.component.DaggerFragmentComponent
import com.carlosvivas.zemoga.di.module.FragmentModule
import com.carlosvivas.zemoga.models.Post
import com.carlosvivas.zemoga.ui.detail.DetailFragment
import com.carlosvivas.zemoga.util.SwipeToDelete
import kotlinx.android.synthetic.main.fragment_post.*
import javax.inject.Inject

class PostFragment: Fragment(), PostContract.View, PostAdapter.onItemClickListener {

    @Inject
    lateinit var presenter: PostContract.Presenter

    private lateinit var rootView: View

    fun newInstance(): PostFragment {
        return PostFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater!!.inflate(R.layout.fragment_post, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        presenter.subscribe()
        initView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }

    override fun onResume() {

        super.onResume()
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    override fun showErrorMessage(error: String) {
        Log.e("Error", error)
    }

    override fun loadDataSuccess(list: List<Post>) {
        var adapter = PostAdapter(activity!!, list.toMutableList(), this)
        recyclerView!!.setLayoutManager(LinearLayoutManager(activity))
        recyclerView!!.setAdapter(adapter)

        val swipeHandler = object : SwipeToDelete(activity!!) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = recyclerView.adapter as PostAdapter
                adapter.removeAt(viewHolder.adapterPosition)
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }


    override fun itemRemoveClick(post: Post) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun itemDetail(postId: Int, postUserId: Int, postBody: String) {
        val fragment = DetailFragment()
        val bundle = Bundle()
        bundle.putInt("Id", postId)
        bundle.putInt("UserId", postUserId)
        bundle.putString("Body", postBody)
        fragment.arguments=bundle

        fragmentManager!!.beginTransaction()
            .add(R.id.linearLayout2, fragment)
            .addToBackStack(null)
            .setCustomAnimations(AnimType.FADE.getAnimPair().first, AnimType.FADE.getAnimPair().second)
            .commit()
    }

    private fun injectDependency() {
        val listComponent = DaggerFragmentComponent.builder()
            .fragmentModule(FragmentModule())
            .build()

        listComponent.inject(this)
    }

    private fun initView() {
        presenter.loadData()
    }

    companion object {
        val TAG: String = "PostFragment"
    }

    enum class AnimType() {
        SLIDE,
        FADE;

        fun getAnimPair(): Pair<Int, Int> {
            when(this) {
                SLIDE -> return Pair(R.anim.slide_left, R.anim.slide_right)
                FADE -> return Pair(R.anim.fade_in, R.anim.fade_out)
            }
            return Pair(R.anim.slide_left, R.anim.slide_right)
        }
    }
}