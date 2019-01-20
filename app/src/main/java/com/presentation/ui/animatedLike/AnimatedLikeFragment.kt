package com.presentation.ui.animatedLike

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.R
import com.databinding.AnimatedLikeFragmentBinding
import com.presentation.ui.TabFragment

class AnimatedLikeFragment : TabFragment() {

    override val tabName = "Like Animation"

    companion object {
        fun newInstance() = AnimatedLikeFragment()
    }

    private lateinit var binding: AnimatedLikeFragmentBinding
    private lateinit var viewModel: AnimatedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.animated_like_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AnimatedViewModel::class.java)
        binding.setLifecycleOwner(this)
        binding.model = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val v = view.findViewById<AnimatedLikeView>(R.id.likeView)
        v.setOnClickListener {
            v.start()
        }

    }
}
