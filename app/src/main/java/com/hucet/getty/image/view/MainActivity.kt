package com.hucet.getty.image.view

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.hucet.getty.image.R
import com.hucet.getty.image.glide.GlideApp
import com.hucet.getty.image.view.adapter.GettyAdapter
import com.hucet.getty.image.viewmodel.GettyViewModel
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject lateinit var viewModel: GettyViewModel
    @Inject lateinit var adapter: GettyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
                    GlideApp.with(this).clear(it.thumbnail)
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

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector
    }
}
