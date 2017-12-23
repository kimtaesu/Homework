package com.hucet.getty.image.view.adapter

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.hucet.getty.image.BR
import com.hucet.getty.image.R
import com.hucet.getty.image.di.scope.PerActivity
import com.hucet.getty.image.model.GettyImage
import javax.inject.Inject

/**
 * Created by taesu on 2017-12-23.
 */
@PerActivity
class GettyAdapter @Inject constructor(private val bindingComponent: GettyDataBindingComponent) : RecyclerView.Adapter<GettyAdapter.GettyImageViewHolder>() {
    private val items = arrayListOf<GettyImage>()
    override fun getItemCount(): Int = items.size

    fun update(newItems: List<GettyImage>) {
        val start = itemCount
        items.addAll(newItems)
        notifyItemRangeInserted(start, itemCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): GettyImageViewHolder {

        val binding: ViewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent?.context),
                R.layout.list_item_getty, parent, false, bindingComponent)
        return GettyImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GettyImageViewHolder?, position: Int) {
        holder?.bind(items[position])
    }


    inner class GettyImageViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        val thumbnail = binding.root.findViewById<ImageView>(R.id.thumbnail)
        fun bind(item: GettyImage) {
            binding.setVariable(BR.getty, item)
            binding.executePendingBindings()
        }
    }
}