package com.presentation.ui.recycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import com.R
import com.databinding.RecyclerFragmentBinding
import com.presentation.ui.TabFragment
import com.presentation.ui.animatedLike.AnimatedLikeView

/**
 * Created by Dmytro Pashko on 1/10/2019.
 */

class RecyclerFragment : TabFragment() {
    companion object {

        fun newInstance() = RecyclerFragment()
    }

    override val tabName = "Recycler Text Optimization"

    private lateinit var viewModel: RecyclerViewModel
    private lateinit var binding: RecyclerFragmentBinding
    private lateinit var adapter: DataRowAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.recycler_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RecyclerViewModel::class.java)
        binding.setLifecycleOwner(this)
        binding.model = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = DataRowAdapter().also {
            binding.list.adapter = it
            ItemTouchHelper(DataRowSwipeHandler(it)).attachToRecyclerView(binding.list)
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.data.observe(this, Observer {
            if (it != null) {
                adapter.update(it)
            }
        })
    }
}
