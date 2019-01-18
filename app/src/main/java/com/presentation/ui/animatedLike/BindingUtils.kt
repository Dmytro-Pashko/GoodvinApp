package com.presentation.ui.animatedLike

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import com.presentation.utils.dp2px

/**
 * Created by Dmytro Pashko on 1/18/2019.
 */

object BindingUtils {

    @JvmStatic
    @BindingAdapter("android:particles")
    fun setParticleCount(view: AnimatedLikeView, value: ObservableField<Int>) {
        view.stop()
        view.particlesCount = 10 * (value.get() ?: 0)
    }

    @JvmStatic
    @BindingAdapter("android:startParticleMaxDistance")
    fun setStartParticleMaxDistance(view: AnimatedLikeView, value: ObservableField<Int>) {
        view.stop()
        view.startDistanceMax = (25 * (value.get() ?: 0)).dp2px()
    }
}