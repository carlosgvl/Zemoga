package com.carlosvivas.zemoga.ui.main

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.carlosvivas.zemoga.R
import com.carlosvivas.zemoga.di.component.DaggerActivityComponent
import com.carlosvivas.zemoga.di.module.ActivityModule
import com.carlosvivas.zemoga.ui.detail.DetailFragment
import com.carlosvivas.zemoga.ui.favorite.FavoriteFragment
import com.carlosvivas.zemoga.ui.posts.PostFragment
import javax.inject.Inject

class MainActivity: AppCompatActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainContract.Presenter
    private lateinit var viewpager: ViewPager
    private lateinit var tabs: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

        setupViewPager()

        injectDependency()

        presenter.attach(this)

    }

    override fun onResume() {
        super.onResume()
    }

    private fun initViews() {
        tabs = findViewById(R.id.tabs)
        viewpager = findViewById(R.id.viewpager)
    }

    override fun setupViewPager() {

        val adapter = MainPageAdapter(getSupportFragmentManager())

        adapter.addFragment(PostFragment(), "ALL")
        adapter.addFragment( FavoriteFragment(), "FAVORITES")

        viewpager!!.adapter = adapter
        tabs!!.setupWithViewPager(viewpager)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val homeItem = menu!!.findItem( android.R.id.home)
        if(homeItem!=null){
            homeItem.setVisible(false)}
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {


        when(item!!.itemId) {
            R.id.nav_recarge -> {
                presenter.onDrawerOptionAboutClick()
                return true
            }
            else -> {
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager
        val fragment = fragmentManager.findFragmentByTag(DetailFragment.TAG)

        if (fragment == null) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }
    }

    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .build()

        activityComponent.inject(this)
    }




}