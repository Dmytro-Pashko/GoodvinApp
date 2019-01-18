package com.presentation.ui.recycler

import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.R
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.domain.model.DataRow
import com.presentation.utils.ContextProvider.Instance.appContext
import com.presentation.utils.appInflater
import com.databinding.DataRowItemBinding
import com.presentation.utils.GlideApp

/**
 * Created by Dmytro Pashko on 1/10/2019.
 */

class DataRowAdapter : RecyclerView.Adapter<DataRowViewHolder>() {

    private val list: MutableList<DataRow> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataRowViewHolder {
        val binding = DataBindingUtil.inflate<DataRowItemBinding>(appInflater, R.layout.data_row_item, parent, false)
        return DataRowViewHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: DataRowViewHolder, position: Int) = holder.bind(list[position])

    fun update(it: List<DataRow>) {
        list.clear()
        list.addAll(it)
        notifyDataSetChanged()
    }

    fun removeItemAt(index: Int) {
        list.removeAt(index)
        notifyItemRemoved(index)
    }
}

class DataRowViewHolder(private val binding: DataRowItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: DataRow) {
        binding.item = item

        GlideApp.with(appContext)
            .load("http://picsum.photos/1000/1000/?random")
            .placeholder(R.drawable.ic_image_placegolder)
            .fitCenter()
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(binding.image)

        binding.executePendingBindings()

    }
}
