package com.recycler.ui.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.recycler.R
import com.recycler.databinding.DataRowItemBinding
import com.recycler.model.DataRow
import com.utils.ContextProvider.Instance.appContext

class DataRowAdapter : RecyclerView.Adapter<DataRowViewHolder>() {

    private val list: MutableList<DataRow> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataRowViewHolder {
        val inflater = LayoutInflater.from(appContext)
        val binding = DataBindingUtil.inflate<DataRowItemBinding>(inflater, R.layout.data_row_item, parent, false)
        return DataRowViewHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: DataRowViewHolder, position: Int) = holder.bind(list[position])

    fun update(it: List<DataRow>) {
        list.clear()
        list.addAll(it)
        notifyDataSetChanged()
    }
}

class DataRowViewHolder(private val binding: DataRowItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: DataRow) {
        binding.item = item
        binding.executePendingBindings()
    }
}