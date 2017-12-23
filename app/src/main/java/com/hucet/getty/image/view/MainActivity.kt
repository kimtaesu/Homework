package com.hucet.getty.image.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.hucet.getty.image.R
import com.hucet.getty.image.view.adapter.GettyAdapter
import com.hucet.getty.image.viewmodel.GettyViewModel
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    lateinit var viewModel: GettyViewModel
    @Inject lateinit var adapter: GettyAdapter
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(GettyViewModel::class.java)
        initRecyclerView()
        requestFetch()
        initImageLiveData()

    }

    private fun initRecyclerView() {
        recycler.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
            setRecyclerListener {
                if (it is GettyAdapter.GettyImageViewHolder) {
                    it.thumbnail.clearAnimation()
                    Glide.with(this).clear(it.thumbnail)
                }
            }
        }
    }

    private fun requestFetch() {
        viewModel.requestFetch()
    }

    private fun initImageLiveData() {
        viewModel.imagesLiveData().observe(this, Observer {
            it?.run {
                adapter.update(it)
            }
        })
    }

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> =
            fragmentDispatchingAndroidInjector
}
