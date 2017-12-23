package com.hucet.getty.image.view.adapter

import android.databinding.BindingAdapter
import android.databinding.DataBindingComponent
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.hucet.getty.image.di.scope.PerActivity
import com.hucet.getty.image.view.MainActivity
import javax.inject.Inject

/**
 * Created by taesu on 2017-12-23.
 */


@PerActivity
class GettyDataBindingComponent @Inject constructor(
        private val activity: MainActivity) : DataBindingComponent {
    override fun getGettyDataBindingComponent(): GettyDataBindingComponent = this

    @BindingAdapter("imageUrl")
    fun bindImage(imageView: ImageView, url: String?) {
        Glide.with(activity).load(url).into(imageView)
    }
}