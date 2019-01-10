package com.presentation.ui.animatedLike

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.recycler.R
import com.recycler.databinding.FAnimatedLikeBinding

class AnimatedLikeFragment : Fragment() {

    companion object {
        fun newInstance() = AnimatedLikeFragment()
    }

    private lateinit var binding: FAnimatedLikeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.f_animated_like, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.setLifecycleOwner(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val v = view.findViewById<AnimatedLikeView>(R.id.likeView)
        v.setOnClickListener {
            v.doLike()
        }

    }
}
