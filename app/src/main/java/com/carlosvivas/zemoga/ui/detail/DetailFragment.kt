package com.carlosvivas.zemoga.ui.detail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager

import android.util.Log
import android.view.*
import android.widget.TextView
import com.carlosvivas.zemoga.R
import com.carlosvivas.zemoga.di.module.FragmentModule
import com.carlosvivas.zemoga.di.component.DaggerFragmentComponent
import com.carlosvivas.zemoga.models.Comments
import com.carlosvivas.zemoga.models.User
import com.carlosvivas.zemoga.util.Prefs
import kotlinx.android.synthetic.main.fragment_detail.*
import javax.inject.Inject

class DetailFragment: Fragment(), DetailContract.View {

    @Inject
    lateinit var presenter: DetailContract.Presenter

    private lateinit var rootView: View
    private var PostId: Int =0
    private  var UserId: Int = 0
    private  var Body: String = ""
    private lateinit var tv_name: TextView
    private lateinit var tv_email: TextView
    private lateinit var tv_phone: TextView
    private lateinit var tv_website: TextView
    private lateinit var tv_description: TextView
    var prefs: Prefs? = null

    fun newInstance(): DetailFragment {
        return DetailFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater!!.inflate(R.layout.fragment_detail, container, false)
        setHasOptionsMenu(true)

        prefs = Prefs(activity!!)

        tv_name = rootView.findViewById(R.id.tv_name)
        tv_email = rootView.findViewById(R.id.tv_email)
        tv_phone = rootView.findViewById(R.id.tv_phone)
        tv_website = rootView.findViewById(R.id.tv_website)
        tv_description = rootView.findViewById(R.id.tv_description)

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)


        if (arguments!!.getInt("Id")!! != null){
            PostId =arguments!!.getInt("Id")
            UserId =arguments!!.getInt("UserId")
            Body =arguments!!.getString("Body")
        }
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

    private fun injectDependency() {
        val aboutComponent = DaggerFragmentComponent.builder()
            .fragmentModule(FragmentModule())
            .build()

        aboutComponent.inject(this)
    }

    private fun initView() {
        presenter.loadData(PostId)
        presenter.loadUser(UserId)
    }

    companion object {
        val TAG: String = "DetailFragment"
    }

    override fun loadDataSuccess(list: List<Comments>) {
        var adapter = DetailAdapter(activity!!, list.toMutableList(), this)
        recyclerView!!.setLayoutManager(LinearLayoutManager(activity))
        recyclerView!!.setAdapter(adapter)

    }

    override fun loadUserSuccess(list: User) {

        tv_name.text=list.name
        tv_email.text=list.email
        tv_phone.text=list.phone
        tv_website.text=list.website
        tv_description.text= Body

    }



    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.menu_favorite, menu)
        val recargeItem = menu!!.findItem(R.id.nav_recarge)
        recargeItem.setVisible(false)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        item!!.itemId
        when(item!!.itemId) {
            R.id.nav_start -> {
                item.setIcon(R.drawable.ic__star)
                presenter.drawerfavorite(prefs!!,PostId)
                return true
            }
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> {

            }
        }

        return super.onOptionsItemSelected(item)
    }


    fun onBackPressed() {

        val count = fragmentManager?.backStackEntryCount
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)


        if (count == 0) {
            fragmentManager?.popBackStack()
        } else {
            fragmentManager?.popBackStack()
        }

    }
}